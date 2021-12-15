package com.shopify.api.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.shopify.api.common.BadShopifyRequest;
import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.common.Credential;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ShopifyClient {
	private ResteasyWebTarget target;

	@Provider
	public static class FunkyPathFixer implements ClientRequestFilter {
		public void filter(ClientRequestContext req) {
			try {
				//this is required because there are cases where the path will eval to "/admin/collects/.json" and should be "/admin/collects.json" for Shopify
				//and because of the bug in RestEasy: https://issues.jboss.org/browse/RESTEASY-677 where @Encoded is being ignored
				req.setUri(new URI(req.getUri().toString().replace("/.", ".").replace(".json%3F", ".json?"))); 
			} catch (URISyntaxException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Provider
	public static class LinkHeaderSplitter implements ClientResponseFilter {
		@Override
		public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
			MultivaluedMap<String, String> headers = responseContext.getHeaders();
			if(headers.containsKey("Link")){
				// so shopify combines their Link headers with a comma (which is allowed)
				// but resteasy doesn't understand this, and so we have to split them into separate
				// "Link" header entries for RestEasy to find them correctly.
				// see: org.jboss.resteasy.specimpl.AbstractBuiltResponse.LinkHeaders.addLinkObjects
				headers.replaceAll((key, values) -> {
					if("Link".equalsIgnoreCase(key)){
						return values.stream().flatMap(originalLink -> {
							if(originalLink.contains(",")) {
								// split it and put it back in
								return Stream.of(
									Iterables.toArray(
										Splitter.on(",")
											.omitEmptyStrings()
											.trimResults()
											.split(originalLink),
										String.class
									)
								);
							} else {
								return Stream.of(originalLink);
							}
						}).collect(Collectors.toList());
					}

					return values; // leave it untouched
				});

				if(headers.get("Link").size()==1){
					String originalLink = headers.getFirst("Link");
					headers.remove("Link");

					// split it and put it back in
					Arrays.stream(originalLink.split(",")).sequential().forEach(splitLink -> {
						headers.add("Link", splitLink);
					});
				}
			}
		}
	}

	//this is the container for all the serialize/deserialize configs
	public static class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	    private ObjectMapper objectMapper;

	    @SneakyThrows
		public JacksonContextResolver() {

	        this.objectMapper = new ObjectMapper()

				//mapper for joda time
				.registerModule(new JodaModule())

				//this keeps the future compatibility open
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				
				//this is what makes those goofy "first level" object names like: {"custom_collection":{"title":"Test 123"}}
				.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
				.configure(SerializationFeature.WRAP_ROOT_VALUE, true)
				
				//this keeps our output small
				.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
				.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false)
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
				.setSerializationInclusion(JsonInclude.Include.NON_NULL)

				//this is the CRAZY shit I have to do to support returning "{"count":184}" as an int
				.setAnnotationIntrospector(new JacksonAnnotationIntrospector(){
					@Override
					public PropertyName findRootName(AnnotatedClass ac) {
						if(ac.getGenericType().equals(int.class)) return PropertyName.construct("count", null);
						return super.findRootName(ac);
					}
				})
				
				//this is so I can have nice java camel-case, but map to the underscore names in Shopify
				.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	    }
	    public ObjectMapper getContext(Class<?> objectType) {
			return objectMapper;
	    }
	}
	
	//to ACTUALLY throttle calls
	@Provider
	public static class ApiCallThrottler implements ClientRequestFilter {
		public void filter(ClientRequestContext req) {
			if(ApiCallThrottleFeedback.atLimit()){
				//wait here for the bucket to leak a request
				try {
					log.warn("Slowing WAY down, we are AT our limits...");
					Thread.sleep(5000);
				} catch (InterruptedException e) {} //okay so we just go now!
			}
			if(ApiCallThrottleFeedback.nearLimit()){
				//wait here for the bucket to leak a request
				try {
					log.info("Slowing down, we are near our limits...");
					Thread.sleep(1000);
				} catch (InterruptedException e) {} //okay so we just go now!
			}
		}
	}
	
	//to watch the throttle feedback from previous calls
	@Provider
	public static class ApiCallThrottleFeedback implements ClientResponseFilter {

		public static volatile int currentCallCount = 0;
		public static volatile int totalCallLimit = 40;
		
		public static boolean atLimit(){
			return currentCallCount+3>=totalCallLimit;
		}
		
		public static boolean nearLimit(){
			return currentCallCount+10>=totalCallLimit;
		}
		
		@Override
		public void filter(ClientRequestContext req, ClientResponseContext resp) throws IOException {
			
			String callLimit = resp.getHeaderString("X-Shopify-Shop-Api-Call-Limit");
			
			if(resp.getStatus()==429){
				//woah! hold your horses!
				throw new RuntimeException("Exceeded Shopify Call Speed Limit! "+callLimit);
			}
			
			if(resp.getStatus()==422){
				//woah! bad request!
				throw new BadShopifyRequest("Shopify Request Rejected! "+IOUtils.toString(resp.getEntityStream()));
			}
			
			if(callLimit==null) return; //nothing to see here
			
			//set the values we got back from the server
			currentCallCount = Integer.parseInt(callLimit.split("/")[0]);
			totalCallLimit = Integer.parseInt(callLimit.split("/")[1]);
		}
		
	}

	public ShopifyClient(Credential creds) {
		createTarget(creds.getShopName(), creds.getApiKey(), creds.getPassword());
	}
	
	private void createTarget(String shopname, String username, String password){

		ResteasyClientBuilder clientBuilder = new ResteasyClientBuilder()
				
			//map all the various filters we need to comply to Shopify wonky-land
			.register(new JacksonContextResolver())
			.register(new BasicAuthentication(username, password))
			.register(FunkyPathFixer.class)
			.register(LinkHeaderSplitter.class)
			.register(ApiCallThrottler.class)
			.register(ApiCallThrottleFeedback.class)

			.establishConnectionTimeout(5000, TimeUnit.MILLISECONDS)
			.socketTimeout(10000, TimeUnit.MILLISECONDS)
			;

		target = clientBuilder.build().target("https://"+shopname+".myshopify.com/");
		
	}

	public <T extends BaseShopifyService> T constructInterface(Class<T> interfaze){
        return target.proxy(interfaze);
	}

}

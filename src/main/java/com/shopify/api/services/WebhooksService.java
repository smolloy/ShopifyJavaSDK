//Webhook Webhooks webhook webhooks webhooks

package com.shopify.api.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.Webhook;


@Path("/admin/webhooks")
@Consumes("application/json") @Produces("application/json")
public interface WebhooksService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Webhook.class)
	@JsonRootName("webhooks")
	class WebhookList extends ArrayList<Webhook> {}
	
    @GET @Path(".json")
    WebhookList getWebhooks();

    @GET @Path(".json?{query}")
    WebhookList getWebhooks(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Webhook getWebhook(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Webhook getWebhook(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Webhook createWebhook(Webhook webhook);

    @PUT @Path("{id}.json")
    Webhook updateWebhook(@PathParam("id") long id, Webhook webhook);

    @DELETE @Path("{id}.json")
    void deleteWebhook(@PathParam("id") long id);
}

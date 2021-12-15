//Country Countries country countries countries

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


import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopify.api.common.BaseShopifyService;
import com.shopify.api.resources.Country;


@Path("/admin/countries")
@Consumes("application/json") @Produces("application/json")
public interface CountriesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Country.class)
	@JsonRootName("countries")
	public static class CountryList extends ArrayList<Country> {}
	
    @GET @Path(".json")
    CountryList getCountries();

    @GET @Path(".json?{query}")
    CountryList getCountries(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Country getCountry(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Country getCountry(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Country createCountry(Country country);

    @PUT @Path("{id}.json")
    Country updateCountry(@PathParam("id") long id, Country country);

    @DELETE @Path("{id}.json")
    void deleteCountry(@PathParam("id") long id);
}

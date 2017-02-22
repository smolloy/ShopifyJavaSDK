//Province Provinces province provinces provinces

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
import com.shopify.api.resources.Province;


@Path("/admin/provinces")
@Consumes("application/json") @Produces("application/json")
public interface ProvincesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Province.class)
	@JsonRootName("provinces")
	class ProvinceList extends ArrayList<Province> {}
	
    @GET @Path(".json")
    ProvinceList getProvinces();

    @GET @Path(".json?{query}")
    ProvinceList getProvinces(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Province getProvince(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Province getProvince(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Province createProvince(Province province);

    @PUT @Path("{id}.json")
    Province updateProvince(@PathParam("id") long id, Province province);

    @DELETE @Path("{id}.json")
    void deleteProvince(@PathParam("id") long id);
}

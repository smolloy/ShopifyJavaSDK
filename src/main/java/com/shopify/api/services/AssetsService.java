//Asset Assets asset assets assets

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
import com.shopify.api.resources.Asset;


@Path("/admin/assets")
@Consumes("application/json") @Produces("application/json")
public interface AssetsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Asset.class)
	@JsonRootName("assets")
	class AssetList extends ArrayList<Asset> {}
	
    @GET @Path(".json")
    AssetList getAssets();

    @GET @Path(".json?{query}")
    AssetList getAssets(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Asset getAsset(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Asset getAsset(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Asset createAsset(Asset asset);

    @PUT @Path("{id}.json")
    Asset updateAsset(@PathParam("id") long id, Asset asset);

    @DELETE @Path("{id}.json")
    void deleteAsset(@PathParam("id") long id);
}

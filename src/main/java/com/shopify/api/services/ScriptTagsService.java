//ScriptTag ScriptTags scriptTag scriptTags script_tags

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
import com.shopify.api.resources.ScriptTag;


@Path("/admin/script_tags")
@Consumes("application/json") @Produces("application/json")
public interface ScriptTagsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=ScriptTag.class)
	@JsonRootName("script_tags")
	class ScriptTagList extends ArrayList<ScriptTag> {}
	
    @GET @Path(".json")
    ScriptTagList getScriptTags();

    @GET @Path(".json?{query}")
    ScriptTagList getScriptTags(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    ScriptTag getScriptTag(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    ScriptTag getScriptTag(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    ScriptTag createScriptTag(ScriptTag scriptTag);

    @PUT @Path("{id}.json")
    ScriptTag updateScriptTag(@PathParam("id") long id, ScriptTag scriptTag);

    @DELETE @Path("{id}.json")
    void deleteScriptTag(@PathParam("id") long id);
}

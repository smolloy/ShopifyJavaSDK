//Theme Themes theme themes themes

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
import com.shopify.api.resources.Theme;


@Path("/admin/themes")
@Consumes("application/json") @Produces("application/json")
public interface ThemesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Theme.class)
	@JsonRootName("themes")
	public static class ThemeList extends ArrayList<Theme> {}
	
    @GET @Path(".json")
    ThemeList getThemes();

    @GET @Path(".json?{query}")
    ThemeList getThemes(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Theme getTheme(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Theme getTheme(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Theme createTheme(Theme theme);

    @PUT @Path("{id}.json")
    Theme updateTheme(@PathParam("id") long id, Theme theme);

    @DELETE @Path("{id}.json")
    void deleteTheme(@PathParam("id") long id);
}

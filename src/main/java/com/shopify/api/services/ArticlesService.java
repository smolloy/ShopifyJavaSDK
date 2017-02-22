//Article Articles article articles articles

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
import com.shopify.api.resources.Article;


@Path("/admin/articles")
@Consumes("application/json") @Produces("application/json")
public interface ArticlesService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Article.class)
	@JsonRootName("articles")
	class ArticleList extends ArrayList<Article> {}
	
    @GET @Path(".json")
    ArticleList getArticles();

    @GET @Path(".json?{query}")
    ArticleList getArticles(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Article getArticle(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Article getArticle(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Article createArticle(Article article);

    @PUT @Path("{id}.json")
    Article updateArticle(@PathParam("id") long id, Article article);

    @DELETE @Path("{id}.json")
    void deleteArticle(@PathParam("id") long id);
}

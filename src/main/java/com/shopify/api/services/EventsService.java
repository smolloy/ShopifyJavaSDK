//Event Events event events events

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
import com.shopify.api.resources.Event;


@Path("/admin/events")
@Consumes("application/json") @Produces("application/json")
public interface EventsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Event.class)
	@JsonRootName("events")
	class EventList extends ArrayList<Event> {}
	
    @GET @Path(".json")
    EventList getEvents();

    @GET @Path(".json?{query}")
    EventList getEvents(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Event getEvent(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Event getEvent(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Event createEvent(Event event);

    @PUT @Path("{id}.json")
    Event updateEvent(@PathParam("id") long id, Event event);

    @DELETE @Path("{id}.json")
    void deleteEvent(@PathParam("id") long id);
}

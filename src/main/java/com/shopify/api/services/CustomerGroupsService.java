//CustomerGroup CustomerGroups customerGroup customerGroups customer_groups

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
import com.shopify.api.resources.CustomerGroup;


@Path("/admin/customer_groups")
@Consumes("application/json") @Produces("application/json")
public interface CustomerGroupsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=CustomerGroup.class)
	@JsonRootName("customer_groups")
	public static class CustomerGroupList extends ArrayList<CustomerGroup> {}
	
    @GET @Path(".json")
    CustomerGroupList getCustomerGroups();

    @GET @Path(".json?{query}")
    CustomerGroupList getCustomerGroups(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    CustomerGroup getCustomerGroup(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    CustomerGroup getCustomerGroup(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    CustomerGroup createCustomerGroup(CustomerGroup customerGroup);

    @PUT @Path("{id}.json")
    CustomerGroup updateCustomerGroup(@PathParam("id") long id, CustomerGroup customerGroup);

    @DELETE @Path("{id}.json")
    void deleteCustomerGroup(@PathParam("id") long id);
}

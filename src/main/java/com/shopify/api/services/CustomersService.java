//Customer Customers customer customers customers

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
import com.shopify.api.resources.Customer;


@Path("/admin/customers")
@Consumes("application/json") @Produces("application/json")
public interface CustomersService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Customer.class)
	@JsonRootName("customers")
	class CustomerList extends ArrayList<Customer> {}
	
    @GET @Path(".json")
    CustomerList getCustomers();

    @GET @Path(".json?{query}")
    CustomerList getCustomers(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Customer getCustomer(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Customer getCustomer(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Customer createCustomer(Customer customer);

    @PUT @Path("{id}.json")
    Customer updateCustomer(@PathParam("id") long id, Customer customer);

    @DELETE @Path("{id}.json")
    void deleteCustomer(@PathParam("id") long id);
}

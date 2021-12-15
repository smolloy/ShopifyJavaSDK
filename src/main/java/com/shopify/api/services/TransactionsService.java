//Transaction Transactions transaction transactions transactions

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
import com.shopify.api.resources.Transaction;


@Path("/admin/transactions")
@Consumes("application/json") @Produces("application/json")
public interface TransactionsService extends BaseShopifyService {

	@SuppressWarnings("serial")
	@JsonDeserialize(contentAs=Transaction.class)
	@JsonRootName("transactions")
	public static class TransactionList extends ArrayList<Transaction> {}
	
    @GET @Path(".json")
    TransactionList getTransactions();

    @GET @Path(".json?{query}")
    TransactionList getTransactions(@Encoded @PathParam("query") String queryParams);
    
    @GET @Path("{id}.json")
    Transaction getTransaction(@PathParam("id") long id);
    
    @GET @Path("{id}.json?{query}")
    Transaction getTransaction(@PathParam("id") long id, @Encoded @PathParam("query") String queryParams);
    
    @GET @Path("count.json")
    int getCount();
    
    @GET @Path("count.json?{query}")
    int getCount(@Encoded @PathParam("query") String queryParams);

    @POST @Path(".json")
    Transaction createTransaction(Transaction transaction);

    @PUT @Path("{id}.json")
    Transaction updateTransaction(@PathParam("id") long id, Transaction transaction);

    @DELETE @Path("{id}.json")
    void deleteTransaction(@PathParam("id") long id);
}

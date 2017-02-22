# ShopifyJavaSDK
This is a simple, resteasy-based java client for the Shopify API

## This client will self-rate-limit based on Shopify responses!
It is able to slow down the calls per second in response to the X-Shopify-Shop-Api-Call-Limit header in the responses, so that you can use this client in a multi-threaded environment and it will not exceed the call rates allowed by Shopify - even if there are API calls originating from other applications or processes.

## Client uses Resteasy client framework
The client uses the rest-easy "proxy" client - the services are defined as interfaces with JAX-RS annotation, and are runtime-interpreted into a function you can call and returns a strongly-typed object.  See more about this here: https://docs.jboss.org/resteasy/docs/3.1.0.Final/userguide/html/RESTEasy_Client_Framework.html#d4e2297

# How do I use this thing??
To use the client, simply create a client like this in your code:
```java
com.shopify.api.common.Credential creds = new Credential("your-api-key", "your-shared-secret", "your-shopname", "your-password");
com.shopify.api.client.ShopifyClient client = new com.shopify.api.client.ShopifyClient(creds);
```
Then, you can call any Shopify service by making a "service implementation" like this:
```java
com.shopify.api.services.CustomCollectionsService collectionsSvc = client.constructInterface(CustomCollectionsService.class);
```
And then any of the API functions can be called like this:
```java
List<com.shopify.api.resources.CustomCollection> collections = collectionsSvc.getCustomCollections();
```

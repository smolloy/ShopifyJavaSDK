package com.shopify.api.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import com.shopify.api.resources.Product;
import com.shopify.api.services.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.shopify.api.client.ShopifyClient;
import com.shopify.api.common.Credential;
import com.shopify.api.resources.CustomCollection;
import com.shopify.api.services.CustomCollectionsService;

@Slf4j
public class ServiceTests {

	private ShopifyClient client;
	
	@Before
	public void setupClient() {
		client = new ShopifyClient(Credential.fromDefaultProperties());
	}
	
	@Test
	public void testGetCollections() throws IOException{
		List<CustomCollection> collections = client.constructInterface(CustomCollectionsService.class)
				.getCustomCollections().list();
		log.info("collections size is {}", collections.size());
		Assert.assertTrue(!collections.isEmpty());
	}

	@Test
	public void testGetUnpublishedProducts() {
		ProductsService.ProductList products = client.constructInterface(ProductsService.class)
				.getProducts(
						new ProductsService.Args()
								.setLimit(10)
								.setPublishedStatus(Product.PublishedStatus.unpublished)
				).list();
		log.info("product publishedAts are {}",
				products.stream().map(Product::getPublishedAt).collect(Collectors.toList())
		);
		Assert.assertTrue(!products.isEmpty());
	}

	@Test
	public void testGetPublishedProducts() {
		ProductsService.ProductList products = client.constructInterface(ProductsService.class)
				.getProducts(
						new ProductsService.Args()
								.setLimit(10)
								.setPublishedStatus(Product.PublishedStatus.published)
				).list();
		log.info("product publishedAts are {}",
				products.stream().map(Product::getPublishedAt).collect(Collectors.toList())
		);
		Assert.assertTrue(!products.isEmpty());
	}

	@Test
	public void testGetPaginatedCollections() throws IOException{
		CustomCollectionsService svc = client.constructInterface(CustomCollectionsService.class);

		int allCollectionsCount = svc.getCount();

		log.info("Expecting {} collections", allCollectionsCount);

		List<CustomCollection> collections = new ArrayList<CustomCollection>();
		int page = 0;
		int totalCollectionsFoundViaPages = 0;
		CustomCollectionsService.PaginatedResponse customCollections = svc.getCustomCollections(
				new CustomCollectionsService.Args().setLimit(100)
		);
		while(true) {
			CustomCollectionsService.CustomCollectionList collectionsList = customCollections.list();

			collections.addAll(collectionsList);

			log.info("Page {} of collections has {} entries", page, collectionsList.size());
			log.info("Page {} of collections -> next page is {}", page, customCollections.nextPage());
			log.info("Page {} of collections -> previous page is {}", page, customCollections.previousPage());

			totalCollectionsFoundViaPages += collectionsList.size();

			if(totalCollectionsFoundViaPages > allCollectionsCount)
				log.error("Found {} from pages, but was only expecting {}", totalCollectionsFoundViaPages, allCollectionsCount);

			if(customCollections.nextPage() == null) break;
			customCollections = customCollections.getNextPage();
			page++;
		}

		Assert.assertTrue(totalCollectionsFoundViaPages == allCollectionsCount);
	}
	
	@Test
	public void testGetTwoCollections() throws IOException{
		CustomCollectionsService svc = client.constructInterface(CustomCollectionsService.class);
		
		CustomCollection collection1 = svc.getCustomCollection(13532841);
		CustomCollection collection2 = svc.getCustomCollection(108747973);

		log.info("Collection 1 is {}", collection1);
		log.info("Collection 2 is {}", collection2);

		Assert.assertTrue(collection2.getId()!=collection1.getId());
	}
	
	@Test
	public void testCreateCollection() throws IOException{
			log.info("Creating new collection...");
			CustomCollection collection = client.constructInterface(CustomCollectionsService.class).createCustomCollection(new CustomCollection().setTitle("Test 123"));
			Assert.assertTrue(collection.getId()>0);
			log.info("Collection is {}", collection);
			log.info("Deleting collection...");
			client.constructInterface(CustomCollectionsService.class).deleteCustomCollection(collection.getId());
			log.info("Deleted collection");
	}

	@Test
	public void testDeleteMissingCollection() throws IOException{
		try {
			log.info("Deleting collection...");
			client.constructInterface(CustomCollectionsService.class).deleteCustomCollection(324234234434234L);
		} catch (NotFoundException e) {
			log.info("Got the NOT FOUND exception we wanted");
		}
	}
	
	@Test
	public void testCountCollections() throws IOException{
		try {
			int count = client.constructInterface(CustomCollectionsService.class).getCount();
			log.info("Count of collections is {}", count);
			Assert.assertTrue(count>0);
		} catch (BadRequestException e) {
			throw new RuntimeException(e.getResponse().readEntity(String.class));
		}
	}
	
	@Test
	public void testSearchCollections() throws IOException{
		try {
			int count = client.constructInterface(CustomCollectionsService.class).getCount("status=active");
			log.info("Count of search is {}", count);
			Assert.assertTrue(count>0);
		} catch (BadRequestException e) {
			throw new RuntimeException(e.getResponse().readEntity(String.class));
		}
	}
}

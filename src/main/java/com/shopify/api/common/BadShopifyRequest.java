package com.shopify.api.common;

public class BadShopifyRequest extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BadShopifyRequest(String message) {
		super(message);
	}

}

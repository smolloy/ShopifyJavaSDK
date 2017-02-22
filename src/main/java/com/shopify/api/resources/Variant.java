package com.shopify.api.resources;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;

import com.shopify.api.common.AbstractShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("variant")
public class Variant extends AbstractShopifyResource {
    private long productId;
    private String compareAtPrice;
    private String fulfillmentService;
    private int grams;
    private String inventoryManagement;
    private String inventoryPolicy;
    private int inventoryQuantity;
    private String option1;
    private String option2;
    private String option3;
    private long position;
    private BigDecimal price;
    private boolean requiresShipping;
    private String sku;
    private boolean taxable;
    private String title;
    private String barcode;
}

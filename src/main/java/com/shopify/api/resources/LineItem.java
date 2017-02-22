package com.shopify.api.resources;

import com.shopify.api.common.AbstractShopifyResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("line_item")
public class LineItem extends AbstractShopifyResource {
    private String fulfillmentService;
    private String fulfillmentStatus;
    private Long grams;
    private String name;
    private String price;
    private Long productId;
    private int quantity;
    private boolean requiresShipping;
    private String sku;
    private String title;
    private Long variantId;
    private String variantTitle;
    private String vendor;
}

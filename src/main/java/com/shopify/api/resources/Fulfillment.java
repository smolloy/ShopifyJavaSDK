package com.shopify.api.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


import com.shopify.api.common.ShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("fulfillment")
public class Fulfillment extends ShopifyResource {
    private List<LineItem> lineItems;
    private Long orderId;
    private Receipt receipt;
    private String status;
    private String trackingCompany;
    private String trackingNumber;
}

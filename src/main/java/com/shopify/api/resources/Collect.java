package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;

import com.shopify.api.common.ShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("collect")
public class Collect extends ShopifyResource {
    private Long collectionId;
    private Boolean featured;
    private Long position;
    private Long productId;
    private String sortValue;
}

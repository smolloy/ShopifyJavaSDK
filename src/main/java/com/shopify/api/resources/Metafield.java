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
@JsonRootName("metafield")
public class Metafield extends ShopifyResource {
    private String description;
    private String key;
    private String namespace;
    private String value;
    private String valueType;
}

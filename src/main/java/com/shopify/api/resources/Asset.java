package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.experimental.Accessors;
import com.shopify.api.common.ShopifyResource;
import lombok.Setter;

@Getter
@Setter
@Accessors(chain = true)
@JsonRootName("asset")
public class Asset extends ShopifyResource {
    private String key;
    private String publicUrl;
    private String value;
}

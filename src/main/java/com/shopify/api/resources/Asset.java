package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shopify.api.common.AbstractShopifyResource;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.Setter;

@Getter
@Setter
@Accessors(chain = true)
@JsonRootName("asset")
public class Asset extends AbstractShopifyResource {
    private String key;
    private String publicUrl;
    private String value;
}

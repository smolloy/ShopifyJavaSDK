package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.AbstractShopifyResource;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("blog")
public class Blog extends AbstractShopifyResource {
    private String commentable;
    private String feedburner;
    private String feedburnerLocation;
    private String handle;
    private String templateSuffix;
    private String title;
}

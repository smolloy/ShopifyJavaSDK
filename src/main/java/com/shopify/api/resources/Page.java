package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.ShopifyResource;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("page")
public class Page extends ShopifyResource {
    private String author;
    private String bodyHtml;
    private String handle;
    private String publishedAt;
    private int shopId;
    private String templateSuffix;
    private String title;
}

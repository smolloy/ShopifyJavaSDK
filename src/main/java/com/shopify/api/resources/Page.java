package com.shopify.api.resources;

import com.shopify.api.common.AbstractShopifyResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("page")
public class Page extends AbstractShopifyResource {
    private String author;
    private String bodyHtml;
    private String handle;
    private String publishedAt;
    private int shopId;
    private String templateSuffix;
    private String title;
}

package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shopify.api.common.AbstractShopifyResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("article")
public class Article extends AbstractShopifyResource {
    private String author;
    private int blogId;
    private String bodyHtml;
    private String publishedAt;
    private String summaryHtml;
    private String tags;
    private String title;
    private int userId;
}

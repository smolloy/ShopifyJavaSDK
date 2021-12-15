package com.shopify.api.resources;

import lombok.*;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import com.shopify.api.common.ShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("custom_collection")
@Slf4j
public class CustomCollection extends ShopifyResource {
    private String bodyHtml;
    private String handle;
    private String publishedScope;
    private DateTime publishedAt;
    private String sortOrder;
    private String templateSuffix;
    private String title;

    public boolean isPublished() {
        // log.info("published scope for collection {} is {}", id, publishedScope);
        return "web".equalsIgnoreCase(publishedScope) && publishedAt != null;
    }

    public CustomCollection setPublished(boolean publish) {
        return setPublishedScope(publish ? "web" : "none").setPublishedAt(publish ? DateTime.now() : null);
    }
}

package com.shopify.api.resources;

import com.shopify.api.common.AbstractShopifyResource;
import lombok.*;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.DateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("custom_collection")
public class CustomCollection extends AbstractShopifyResource {
    private String bodyHtml;
    private String handle;
    private String publishedScope;
    private DateTime publishedAt;
    private String sortOrder;
    private String templateSuffix;
    private String title;

    public boolean isPublished() {
        return "global".equalsIgnoreCase(publishedScope) && publishedAt != null;
    }

    public CustomCollection setPublished(boolean publish) {
        return setPublishedScope(publish ? "global" : "none").setPublishedAt(publish ? DateTime.now() : null);
    }
}

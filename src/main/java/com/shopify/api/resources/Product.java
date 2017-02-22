package com.shopify.api.resources;

import java.util.List;

import com.shopify.api.common.AbstractShopifyResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.DateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("product")
public class Product extends AbstractShopifyResource {
    private String bodyHtml;
    private String handle;
    private Image image;
    private List<Image> images;
    private List<Option> options;
    private String productType;
    //private Boolean published;
    private DateTime publishedAt;
    private String tags;
    private String templateSuffix;
    private String title;
    private List<Variant> variants;
    private String vendor;
    private String publishedScope;

    public boolean isPublished() {
        return "global".equalsIgnoreCase(publishedScope) && publishedAt != null;
    }

    public Product setPublished(boolean publish) {
        return this.setPublishedScope(publish ? "global" : "none").setPublishedAt(publish ? DateTime.now() : null);
    }
}

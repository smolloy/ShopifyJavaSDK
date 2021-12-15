package com.shopify.api.resources;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.DateTime;

import com.shopify.api.common.ShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("product")
public class Product extends ShopifyResource {

    public enum ProductStatus {
        active, archived, draft, any
    }

    public enum PublishedScope {
        web, global
    }

    public enum PublishedStatus {
        published, unpublished, any
    }

    private String bodyHtml;
    private String handle;
    private Image image;
    private List<Image> images;
    private List<Option> options;
    private String productType;
    private ProductStatus status;
    private DateTime publishedAt; // null here means it will be considered PublishedStatus=unpublished
    private String tags;
    private String templateSuffix;
    private String title;
    private List<Variant> variants;
    private String vendor;
    private PublishedScope publishedScope;

    public boolean isPublished() {
        return status == ProductStatus.active && publishedAt != null;
    }

    public Product setPublished(boolean publish) {
        return this.setStatus(publish ? ProductStatus.active : ProductStatus.archived).setPublishedAt(publish ? DateTime.now() : null);
    }
}

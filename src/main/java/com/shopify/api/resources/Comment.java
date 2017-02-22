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
@JsonRootName("comment")
public class Comment extends AbstractShopifyResource {
    private int articleId;
    private String author;
    private int blogId;
    private String body;
    private String bodyHtml;
    private String email;
    private String ip;
    private String publishedAt;
    private String status;
    private String userAgent;
}

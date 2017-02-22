package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.AbstractShopifyResource;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("transaction")
public class Transaction extends AbstractShopifyResource {
    private String amount;
    private String authorization;
    private String kind;
    private int orderId;
    private Receipt receipt;
    private String status;
}

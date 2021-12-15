package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.ShopifyResource;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("customer")
public class Customer extends ShopifyResource {
    private boolean acceptsMarketing;
    private List<Address> addresses;
    private String email;
    private String firstName;
    private String lastName;
    private String note;
    private int ordersCount;
    private String tags;
    private String totalSpent;
}

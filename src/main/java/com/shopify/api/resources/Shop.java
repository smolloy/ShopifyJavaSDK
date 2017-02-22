package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


import com.shopify.api.common.AbstractShopifyResource;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("shop")
public class Shop extends AbstractShopifyResource {
    private String address1;
    private String city;
    private String country;
    private String currency;
    private String domain;
    private String email;
    private String moneyFormat;
    private String moneyWithCurrencyFormat;
    private String myshopifyDomain;
    private String name;
    private String phone;
    private String planName;
    private String province;
    @JsonProperty("public")
    private boolean isPublic;
    private String shopOwner;
    private String source;
    private String taxShipping;
    private String taxesIncluded;
    private String timezone;
    private String zip;
}

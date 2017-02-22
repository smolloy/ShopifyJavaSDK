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
@JsonRootName("billing_address")
public class BillingAddress extends AbstractShopifyResource {
    private String address1;
    private String address2;
    private String city;
    private String company;
    private String country;
    private String countryCode;
    private String firstName;
    private String lastName;
    private String latitude;
    private String longitude;
    private String name;
    private String phone;
    private String province;
    private String provinceCode;
    private String zip;
}

package com.shopify.api.resources;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shopify.api.common.AbstractShopifyResource;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("address")
public class Address extends AbstractShopifyResource {
    private String address1;
    private String address2;
    private String city;
    private String company;
    private String country;
    private String countryCode;
    private String firstName;
    private String lastName;
    private String name;
    private String phone;
    private String province;
    private String provinceCode;
    private String zip;
}

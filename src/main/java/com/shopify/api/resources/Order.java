package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.AbstractShopifyResource;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("order")
public class Order extends AbstractShopifyResource {
    private BillingAddress billingAddress;
    private String browserIp;
    private boolean buyerAcceptsMarketing;
    private String cancelReason;
    private String cancelledAt;
    private String closedAt;
    private String currency;
    private Customer customer;
    private String email;
    private String financialStatus;
    private String fulfillmentStatus;
    private String gateway;
    private String landingSite;
    private String landingSiteRef;
    private List<LineItem> lineItems;
    private String name;
    private String note;
    private List<NoteAttribute> noteAttributes;
    private int number;
    private int orderNumber;
    private PaymentDetails paymentDetails;
    private String referringSite;
    private ShippingAddress shippingAddress;
    private List<ShippingLine> shippingLines;
    private String subtotalPrice;
    private List<TaxLine> taxLines;
    private boolean taxesIncluded;
    private String token;
    private String totalDiscounts;
    private String totalLineItemsPrice;
    private String totalPrice;
    private String totalTax;
    private int totalWeight;
}

package com.shopify.api.resources;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.shopify.api.common.ShopifyResource;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@JsonRootName("note_attribute")
public class NoteAttribute extends ShopifyResource {
    private String name;
    private String value;
}

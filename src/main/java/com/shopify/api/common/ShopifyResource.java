package com.shopify.api.common;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.Accessors;

import org.joda.time.DateTime;

import com.shopify.api.resources.Metafield;

@Getter
@Setter
@EqualsAndHashCode()
@ToString(callSuper = true)
@Accessors(chain = true)
public abstract class ShopifyResource {
	protected Long id;
	protected DateTime createdAt;
	protected DateTime updatedAt;
	protected List<Metafield> metafields = new ArrayList<Metafield>();
}

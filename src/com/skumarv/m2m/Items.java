package com.skumarv.m2m;

import java.util.HashSet;
import java.util.Set;

public class Items {
	
	private long item_id;
	
	private String description;
	
	private double price;
	
	private Set<Cart> carts = new HashSet<Cart>();

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}
	
}

package com.skumarv.m2m;

import java.util.HashSet;
import java.util.Set;

public class Cart {
	private long cart_id;
	
	private double total;
	
	private Set<Items> items = new HashSet<Items>();


	public long getCart_id() {
		return cart_id;
	}

	public void setCart_id(long cart_id) {
		this.cart_id = cart_id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
}

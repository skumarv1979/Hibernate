package com.skumarv.o2m;

import java.util.Set;

public class Cart {
	private long cart_id;
	private double total;
	private String name;
	private Set<Items> items;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Items> getItems() {
		return items;
	}
	public void setItems(Set<Items> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", total=" + total + ", name="
				+ name + ", items=" + items + "]";
	}
	
}

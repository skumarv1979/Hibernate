package com.skumarv.o2o;

public class Customer {
	private long id;
	private String name;
	private String email;
	private String address;
	private Txn txn2;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Txn getTxn2() {
		return txn2;
	}
	public void setTxn2(Txn txn2) {
		this.txn2 = txn2;
	}
	
}

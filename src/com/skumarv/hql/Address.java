package com.skumarv.hql;

public class Address {
	private long id;
	private String addessLine;
	private String zipcode;
	private String city;
	private Employee employee;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddessLine() {
		return addessLine;
	}
	public void setAddessLine(String addessLine) {
		this.addessLine = addessLine;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", addessLine=" + addessLine
				+ ", zipcode=" + zipcode + ", city=" + city + "]";
	}
	
}

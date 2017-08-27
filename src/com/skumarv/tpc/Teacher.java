package com.skumarv.tpc;

public class Teacher extends Person {
	private double salary;
	private String subjectExpert;
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getSubjectExpert() {
		return subjectExpert;
	}
	public void setSubjectExpert(String subjectExpert) {
		this.subjectExpert = subjectExpert;
	}
}

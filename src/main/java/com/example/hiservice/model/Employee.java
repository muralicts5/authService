package com.example.hiservice.model;

import java.util.Date;



public class Employee {

	public Employee(int employeeId, String name, Date date, float salary, String email, String designation) {
		this.employeeId=employeeId;
		this.name=name;
		this.dob=date;
		this.salary=salary;
		this.email=email;
		this.designation=designation;

		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employee() {
		
	}
	private int employeeId;
	
	private String name;
	
	private Date dob;
	
	private float salary;
	
	private String email;
	
	private String designation;
	
}

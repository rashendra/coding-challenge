package com.coding.challenge.dto;

import java.util.List;

public class EmployeeRepresentationDTO 
{
	private String employeeName;
	
	private List<EmployeeRepresentationDTO>  subs;
	

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<EmployeeRepresentationDTO> getSubs() {
		return subs;
	}

	public void setSubs(List<EmployeeRepresentationDTO> subs) {
		this.subs = subs;
	}
	
	
	
	
}

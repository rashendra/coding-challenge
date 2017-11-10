package com.coding.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonPropertyOrder
({     
    "errorMessage",
    "responseStatus",
    "employeeRepresentationDTO"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT) 
@JsonTypeName(value ="viewEmployeeHierarchyResponse")
public class ViewEmployeeHierarchyResponse 
{

	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("responseStatus")
	private String responseStatus;
	
	@JsonProperty("employeeRepresentationDTO")
	private EmployeeRepresentationDTO employeeRepresentationDTO;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public EmployeeRepresentationDTO getEmployeeRepresentationDTO() {
		return employeeRepresentationDTO;
	}

	public void setEmployeeRepresentationDTO(EmployeeRepresentationDTO employeeRepresentationDTO) {
		this.employeeRepresentationDTO = employeeRepresentationDTO;
	}
	
	
	
}

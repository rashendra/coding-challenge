package com.coding.challenge.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonPropertyOrder({ "limitHierarchy", })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonTypeName(value = "viewEmployeeHierarchyRequest")
public class ViewEmployeeHierarchyRequest {

	private String limitHierarchy;

	public String getLimitHierarchy() {
		return limitHierarchy;
	}

	public void setLimitHierarchy(String limitHierarchy) {
		this.limitHierarchy = limitHierarchy;
	}

	

}

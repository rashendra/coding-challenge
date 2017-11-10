package com.coding.challenge.service;

import com.coding.challenge.api.EmployeeHierarchyValidationException;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface FetchOrganizationHierarchyService.
 */
public interface FetchOrganizationHierarchyService 
{
	
	/**
	 * Fetch organizational hierarchy.
	 *
	 * @param request the request
	 * @return the view employee hierarchy response
	 * @throws EmployeeHierarchyValidationException the employee hierarchy validation exception
	 */
	public ViewEmployeeHierarchyResponse fetchOrganizationalHierarchy(ViewEmployeeHierarchyRequest request) throws EmployeeHierarchyValidationException;
	
	/**
	 * Initialize.
	 *
	 * @throws EmployeeHierarchyValidationException the employee hierarchy validation exception
	 */
	public void initialize() throws EmployeeHierarchyValidationException;

}

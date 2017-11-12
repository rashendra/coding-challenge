package com.coding.challenge.service;

import java.util.List;

import com.coding.challenge.api.EmployeeHierarchyValidationException;
import com.coding.challenge.domain.EmployeeDetails;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface FetchOrganizationHierarchyService.
 */
public interface EmployeeDetailsService {

	/**
	 * Fetch organizational hierarchy.
	 *
	 * @param request
	 *            the request
	 * @return the view employee hierarchy response
	 * @throws EmployeeHierarchyValidationException
	 *             the employee hierarchy validation exception
	 */
	public ViewEmployeeHierarchyResponse fetchOrganizationalHierarchy(ViewEmployeeHierarchyRequest request)
			throws EmployeeHierarchyValidationException;

	/**
	 * Initialize.
	 *
	 * @throws EmployeeHierarchyValidationException
	 *             the employee hierarchy validation exception
	 */
	public void initialize() throws EmployeeHierarchyValidationException;
	
	
	/**
	 * fetchAll.
	 *
	 * @return the list
	 * @throws EmployeeHierarchyValidationException             the employee hierarchy validation exception
	 */
	public List<EmployeeDetails>   fetchAll() throws EmployeeHierarchyValidationException;
	

	
	/**
	 * Creates the.
	 *
	 * @param employeeDetails the employee details
	 * @return the employee details
	 */
	public EmployeeDetails create(EmployeeDetails employeeDetails);
	
	/**
	 * Clear db.
	 */
	public void clearDb();
	
	
	

}

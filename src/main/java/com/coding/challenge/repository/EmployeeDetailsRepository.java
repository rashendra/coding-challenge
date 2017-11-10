package com.coding.challenge.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.coding.challenge.domain.EmployeeDetails;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmployeeDetailsRepository.
 */
public interface EmployeeDetailsRepository 
{
	
	/**
	 * Find all.
	 *
	 * @return the sets the
	 */
	Set<EmployeeDetails>  			findAll();
	
	/**
	 * Find all by manager id.
	 *
	 * @param managerId the manager id
	 * @return the list
	 */
	List<EmployeeDetails>  		 	findAllByManagerId(Long managerId);
	
	/**
	 * Find by employee id.
	 *
	 * @param employeeId the employee id
	 * @return the optional
	 */
	Optional<EmployeeDetails>   		findByEmployeeId(Long employeeId);
	
	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the long
	 */
	Long save(EmployeeDetails entity);
	
	
}

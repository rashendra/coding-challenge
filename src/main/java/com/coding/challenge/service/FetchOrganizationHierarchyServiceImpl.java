package com.coding.challenge.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.challenge.api.EmployeeHierarchyValidationException;
import com.coding.challenge.api.RestAPIController;
import com.coding.challenge.domain.EmployeeDetails;
import com.coding.challenge.dto.EmployeeRepresentationDTO;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;
import com.coding.challenge.repository.EmployeeDetailsRepository;

@Service("fetchOrganizationHierarchyService")
public class FetchOrganizationHierarchyServiceImpl implements FetchOrganizationHierarchyService
{
	private static Logger 	LOGGER = Logger.getLogger(FetchOrganizationHierarchyServiceImpl.class);
	
	
	@Autowired
	EmployeeDetailsRepository employeeHierarchyRepository;
	
	public ViewEmployeeHierarchyResponse  fetchOrganizationalHierarchy(ViewEmployeeHierarchyRequest request) throws EmployeeHierarchyValidationException
	{
//		initialize() ;
		try
		{
			ViewEmployeeHierarchyResponse   response 					= new ViewEmployeeHierarchyResponse();
			List<EmployeeDetails> 			manager 						= employeeHierarchyRepository.findAllByManagerId(null);// get the one which does not have an manager
			if(manager == null || manager.size() != 1)
			{
				LOGGER.error("Invalid Employee Hierarchy !!");
				throw new EmployeeHierarchyValidationException();
			}
			EmployeeRepresentationDTO  		employeeRepresentationDTO 	= populateEmployeeHirarchy(manager.get(0));
			response.setEmployeeRepresentationDTO(employeeRepresentationDTO);
			response.setErrorMessage(null);
			response.setResponseStatus("Success");
			return response;
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
			LOGGER.error("Error in fetchOrganizationalHierarchy"+ e.getMessage());
			throw new EmployeeHierarchyValidationException();
		}
	}
	
	
	private EmployeeRepresentationDTO  populateEmployeeHirarchy(EmployeeDetails employee)
	{
		EmployeeRepresentationDTO        masterEmpRepDTO            = new EmployeeRepresentationDTO();
		masterEmpRepDTO.setEmployeeName(employee.getEmployeeName());
		List<EmployeeDetails> 			 subEmployees 				= employeeHierarchyRepository.findAllByManagerId(employee.getEmployeeId());
		List<EmployeeRepresentationDTO>  employeeRepresentationList	= new ArrayList<EmployeeRepresentationDTO>();
		
		for(EmployeeDetails emHierarchy :subEmployees)
		{	
			EmployeeRepresentationDTO employeeRepresentationDTO = populateEmployeeHirarchy(emHierarchy);
			employeeRepresentationList.add(employeeRepresentationDTO);
		}
		
		masterEmpRepDTO.setSubs(employeeRepresentationList);
		return masterEmpRepDTO;
	}
	
	
	
	private  EmployeeDetails   getEmployeeDetails(Long empId, String name,Long managerId)
	{
	    		return new EmployeeDetails(empId,name,managerId);
	}


	@Override
	public void initialize() throws EmployeeHierarchyValidationException 
	{
		LOGGER.info("Initializing the employees ::");
		EmployeeDetails employeeDetails1  = getEmployeeDetails(100l, "Alan", 150l);
		employeeHierarchyRepository.save(employeeDetails1);
		
		EmployeeDetails employeeDetails2  = getEmployeeDetails(220l, "Martin", 100l);
		employeeHierarchyRepository.save(employeeDetails2);
		
		EmployeeDetails employeeDetails3  = getEmployeeDetails(150l, "Jamie", null);
		employeeHierarchyRepository.save(employeeDetails3);
		
		EmployeeDetails employeeDetails4  = getEmployeeDetails(275l, "Alex", 100l);
		employeeHierarchyRepository.save(employeeDetails4);
		
		EmployeeDetails employeeDetails7  = getEmployeeDetails(276l, "Roshan", 100l);
		employeeHierarchyRepository.save(employeeDetails7);
		
		EmployeeDetails employeeDetails5  = getEmployeeDetails(400l, "Steve", 150l);
		employeeHierarchyRepository.save(employeeDetails5);
		
		EmployeeDetails employeeDetails6  = getEmployeeDetails(190l, "David", 400l);
		employeeHierarchyRepository.save(employeeDetails6);
		
	}

}

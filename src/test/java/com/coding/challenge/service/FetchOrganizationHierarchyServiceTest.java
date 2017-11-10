package com.coding.challenge.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.coding.challenge.api.EmployeeHierarchyValidationException;
import com.coding.challenge.domain.EmployeeDetails;
import com.coding.challenge.dto.EmployeeRepresentationDTO;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;
import com.coding.challenge.repository.EmployeeDetailsRepository;
import com.coding.challenge.repository.EmployeeDetailsRepositoryImpl;
import static com.coding.challenge.TestDataObjectMother.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FetchOrganizationHierarchyServiceTest 
{

	  private static Logger 	LOGGER = Logger.getLogger(FetchOrganizationHierarchyServiceTest.class);
		 
	  @InjectMocks
	  private FetchOrganizationHierarchyService fetchOrganizationHierarchyService;
	  
	  @Mock
	  EmployeeDetailsRepository employeeHierarchyRepository;
	  
	  
	  @Before
	  public void setUp()
	  {
		  fetchOrganizationHierarchyService = new FetchOrganizationHierarchyServiceImpl();
		  employeeHierarchyRepository       = new EmployeeDetailsRepositoryImpl();
	      MockitoAnnotations.initMocks(this);
	  }
	  
	  
	  @Test 
	  public void shouldCreateEmployeeHierarchy()
	  {
		  Set<EmployeeDetails> employeeDetailsList = getEmployeeDetailsList();
		  Mockito.when(employeeHierarchyRepository.findAll()).thenReturn(employeeDetailsList);
		  
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(null)).thenReturn(employeeDetailsList.stream().filter(e->e.getManagerId() == null).collect(Collectors.toList()));
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(150l)).thenReturn(employeeDetailsList.stream().filter(e->e.getManagerId() != null && e.getManagerId().equals(150l))
				  .sorted(Comparator.comparing(EmployeeDetails::getEmployeeId)).collect(Collectors.toList()));
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(100l)).thenReturn(employeeDetailsList.stream().filter(e->e.getManagerId() != null && e.getManagerId().equals(100l))
				  .sorted(Comparator.comparing(EmployeeDetails::getEmployeeId)).collect(Collectors.toList()));
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(400l)).thenReturn(employeeDetailsList.stream().filter(e->e.getManagerId() != null && e.getManagerId().equals(400l))
				  .sorted(Comparator.comparing(EmployeeDetails::getEmployeeId)).collect(Collectors.toList()));
		  
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(220l)).thenReturn(new ArrayList<EmployeeDetails>());
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(275l)).thenReturn(new ArrayList<EmployeeDetails>());
		  Mockito.when(employeeHierarchyRepository.findAllByManagerId(190l)).thenReturn(new ArrayList<EmployeeDetails>());
		 
		  try 
		  {
			ViewEmployeeHierarchyRequest request = new ViewEmployeeHierarchyRequest();  
			  EmployeeRepresentationDTO fetchOrganizationalHierarchy = 	  fetchOrganizationHierarchyService.fetchOrganizationalHierarchy(request).getEmployeeRepresentationDTO();
			  System.out.println(fetchOrganizationalHierarchy.getEmployeeName());
			  List<EmployeeRepresentationDTO> firstLevelSubs = fetchOrganizationalHierarchy.getSubs();
			  for(EmployeeRepresentationDTO employeeRepresentation :firstLevelSubs)
			  {
				  System.out.println("		"+employeeRepresentation.getEmployeeName());
				  List<EmployeeRepresentationDTO> secondLevel = employeeRepresentation.getSubs();
				  
				  for(EmployeeRepresentationDTO employeeRepresentationSecondLeve :secondLevel)
				  {
					  System.out.println("				"+employeeRepresentationSecondLeve.getEmployeeName());
				  }
			  }
		  } 
		  catch (EmployeeHierarchyValidationException e1) 
		  {
			e1.printStackTrace();
		  }
		  
		  
		  
		  
		  
	  }
	  
	  
	  
}

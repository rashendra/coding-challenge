package com.coding.challenge.service;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
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
import com.coding.challenge.repository.EmployeeDetailsRepository;
import com.coding.challenge.repository.EmployeeDetailsRepositoryImpl;
import static com.coding.challenge.TestDataObjectMother.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeDetailsServiceTest 
{

	  private static Logger 	LOGGER = Logger.getLogger(EmployeeDetailsServiceTest.class);
		 
	  @InjectMocks
	  private EmployeeDetailsService employeeDetailsService;
	  
	  @Mock
	  EmployeeDetailsRepository employeeHierarchyRepository;
	  
	  
	  @Before
	  public void setUp()
	  {
		  employeeDetailsService = new EmployeeDetailsServiceImpl();
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
			  EmployeeRepresentationDTO fetchOrganizationalHierarchy = 	  employeeDetailsService.fetchOrganizationalHierarchy(request).getEmployeeRepresentationDTO();
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
	  
	  @Test
	  public void ShouldCreateEmployeeDetailsForGivenEmployeeDetails()
	  {
		  //given
		  EmployeeDetails employeeDetails = getEmployeeDetails(null, "Rashendra", 100l);
		  Mockito.when(employeeHierarchyRepository.save(employeeDetails)).thenReturn(1L);
		  
		  //when 
		  EmployeeDetails savedEmp = employeeDetailsService.create(employeeDetails);
		  //then
		  Assertions.assertThat(savedEmp.getEmployeeId().equals(1L)).isTrue();  
		  
	  }
	  
	  @Test
	  public void shouldDeleteAllTheRecordsWhenClearDB()
	  {
		  //Given
		  Mockito.when(employeeHierarchyRepository.findAll()).thenReturn(new HashSet<>());
		  EmployeeDetails employeeDetails = getEmployeeDetails(null, "Rashendra", 100l);
		  Mockito.when(employeeHierarchyRepository.save(employeeDetails)).thenReturn(1L);
		  
		  //when 
		  employeeDetailsService.clearDb();
		  
		  //then 
		  Assertions.assertThat(employeeHierarchyRepository.findAll().size() == 0).isTrue();  
		  
			 
	  }
	  
	  
}

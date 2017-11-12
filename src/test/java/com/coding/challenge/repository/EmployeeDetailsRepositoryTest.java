package com.coding.challenge.repository;

import static com.coding.challenge.TestDataObjectMother.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.challenge.domain.EmployeeDetails;

public class EmployeeDetailsRepositoryTest 
{
	
	private EmployeeDetailsRepository employeeDetailsRepository;
	
	@Before
	public void setup()
	{
		employeeDetailsRepository  = new EmployeeDetailsRepositoryImpl();
	}
	
	private void initalizeEmployees()
	{
		EmployeeDetails employeeDetails1  = getEmployeeDetails(100l, "Alan", 150l);
		employeeDetailsRepository.save(employeeDetails1);
		
		EmployeeDetails employeeDetails2  = getEmployeeDetails(220l, "Martin", 100l);
		employeeDetailsRepository.save(employeeDetails2);
		
		EmployeeDetails employeeDetails3  = getEmployeeDetails(150l, "Jamie", null);
		employeeDetailsRepository.save(employeeDetails3);
		
		EmployeeDetails employeeDetails4  = getEmployeeDetails(275l, "Alex", 100l);
		employeeDetailsRepository.save(employeeDetails4);
		
		EmployeeDetails employeeDetails5  = getEmployeeDetails(400l, "Steve", 150l);
		employeeDetailsRepository.save(employeeDetails5);
		
		EmployeeDetails employeeDetails6  = getEmployeeDetails(190l, "David", 400l);
		employeeDetailsRepository.save(employeeDetails6);
	}
	
	
	@Test
    public void shouldSaveGivenValidEmployee(){
        //given
		
        final EmployeeDetails employeeDetails = getEmployeeDetails(1l, "Rashendra", 100l);

        //when
        final Long id = employeeDetailsRepository.save(employeeDetails);

        //then
        Assertions.assertThat(id).isNotNull();

    }
	
	@Test
	public void shouldFindByValidMangerId()
	{
		//Given
		  final EmployeeDetails employeeDetails = getEmployeeDetails(2l, "Rashendra", 151l);
		  employeeDetailsRepository.save(employeeDetails);
		
		// when 
		List<EmployeeDetails> employeeByMangerId = employeeDetailsRepository.findAllByManagerId(151l);
		
		System.out.println(employeeByMangerId.size());
		//then 
		Assert.assertTrue(employeeByMangerId !=null && employeeByMangerId.size() == 1);
		
	}
	
	@Test
	public void shouldFindByNullMangerId()
	{
		//Given
		  final EmployeeDetails employeeDetails = getEmployeeDetails(3l, "Rashendra", null);
		  employeeDetailsRepository.save(employeeDetails);
		
		// when 
		List<EmployeeDetails> employeeByMangerId = employeeDetailsRepository.findAllByManagerId(null);
		
		//then 
		Assert.assertTrue(employeeByMangerId !=null && employeeByMangerId.size() > 0);
		
	}
	
	
	@Test 
	public void shouldFindByEmployeeId()
	{
        //given
        final EmployeeDetails employeeDetails = getEmployeeDetails(15l, "Rashendra", 100l);
        employeeDetailsRepository.save(employeeDetails);
        
        //when
       Optional<EmployeeDetails> findByEmployeeId = employeeDetailsRepository.findByEmployeeId(15l);
        
        //then 
        Assertions.assertThat(findByEmployeeId.get() != null && findByEmployeeId.get().getEmployeeId() == 15l);
	}
	
	
	@Test 
	public void shouldReturnAllEmployees()
	{
		//given 
		initalizeEmployees();
		//when 
		Set<EmployeeDetails> findAll = employeeDetailsRepository.findAll();
		//then 
		Assertions.assertThat(findAll != null && findAll.size() == 6).isTrue();
		
	}
	
	
	@Test 
	public void shouldSaveEmployeeDetails()
	{
		//given
		  EmployeeDetails employeeDetails = getEmployeeDetails(15l, "Rashendra", 100l);
		
		//when 
	    employeeDetailsRepository.save(employeeDetails);
		  
		//then 
	    Assertions.assertThat(employeeDetailsRepository.findByEmployeeId(15l).isPresent()).isTrue();  
	}
	
	
}

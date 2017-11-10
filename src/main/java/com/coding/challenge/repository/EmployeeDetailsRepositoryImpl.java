package com.coding.challenge.repository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.LongSupplier;
import java.util.stream.Collectors;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.coding.challenge.api.RestAPIController;
import com.coding.challenge.domain.EmployeeDetails;

@Repository("employeeDetailsRepository")
public class EmployeeDetailsRepositoryImpl  implements EmployeeDetailsRepository
{

	private static Logger 	LOGGER = Logger.getLogger(EmployeeDetailsRepositoryImpl.class);
	
	private Set<EmployeeDetails> 	inMemoryDatabase = new HashSet<EmployeeDetails>();
	
	
	@Override
	public Set<EmployeeDetails> findAll() 
	{
		return inMemoryDatabase;
	}

	/*
	 * TODO please check the employee id
	 * */
	@Override
	public Optional<EmployeeDetails> findByEmployeeId(Long employeeId) 
	{
		LOGGER.info("findByEmployeeId method");
		 List<EmployeeDetails> employeeList = inMemoryDatabase.stream().filter(e->employeeId.equals(e.getEmployeeId())).collect(Collectors.toList());
		 if(employeeList != null && employeeList.size() >0)
		 {
			 return  Optional.of(employeeList.get(0));
		 }
		 return Optional.empty();
	}

	@Override
	public List<EmployeeDetails> findAllByManagerId(Long managerId) 
	{
		LOGGER.info("Size of the in memory database :: "+inMemoryDatabase.size());
		System.out.println(inMemoryDatabase.size());
		
		if(managerId != null) {
			return  inMemoryDatabase.stream().filter(e-> (e.getManagerId() != null && managerId.equals(e.getManagerId()))).sorted(Comparator.comparing(EmployeeDetails::getEmployeeId)).collect(Collectors.toList());
		}else {
			return  inMemoryDatabase.stream().filter(e-> e.getManagerId() == null).sorted(Comparator.comparing(EmployeeDetails::getEmployeeId)).collect(Collectors.toList());
		}
		
	}

	@Override
	public Long save(EmployeeDetails entity) 
	{
		if(generateLongPredicate().test(entity.getEmployeeId()))
		{
			entity.setEmployeeId(generateIdSupplier().getAsLong());
		}
	    inMemoryDatabase.add(entity);
	    return entity.getEmployeeId();
	}
	
	
	private LongSupplier generateIdSupplier()
	{
		 	LongSupplier generateId = () -> Long.valueOf(inMemoryDatabase.size() + 1);
		 	return generateId;
	}
	
	private  Predicate<Long> generateLongPredicate()
	{
			Predicate<Long> predicate = e -> e == null || e == 0;
		 	return predicate;
	}
	
	public void clearDb()
	{
		 inMemoryDatabase.clear();
	}

	

}

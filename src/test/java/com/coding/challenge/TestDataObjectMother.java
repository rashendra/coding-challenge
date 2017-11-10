package com.coding.challenge;


import java.util.HashSet;
import java.util.Set;

import com.coding.challenge.domain.EmployeeDetails;

public class TestDataObjectMother {


    public static final String	USERNAME     	 = "rashendra";
    
    
  
    
    
    public static EmployeeDetails   getEmployeeDetails(Long empId, String name,Long managerId)
    {
    		return new EmployeeDetails(empId,name,managerId);
    }
    
    public static Set<EmployeeDetails>   getEmployeeDetailsList()
    {
    		Set<EmployeeDetails>  emplyeeList = new HashSet<>();
    		EmployeeDetails employeeDetails1  = getEmployeeDetails(100l, "Alan", 150l);
		
		EmployeeDetails employeeDetails2  = getEmployeeDetails(220l, "Martin", 100l);
		
		EmployeeDetails employeeDetails3  = getEmployeeDetails(150l, "Jamie", null);
		
		EmployeeDetails employeeDetails4  = getEmployeeDetails(275l, "Alex", 100l);
		
		EmployeeDetails employeeDetails5  = getEmployeeDetails(400l, "Steve", 150l);
		
		EmployeeDetails employeeDetails6  = getEmployeeDetails(190l, "David", 400l);
		
		emplyeeList.add(employeeDetails1);
		emplyeeList.add(employeeDetails2);
		emplyeeList.add(employeeDetails3);
		emplyeeList.add(employeeDetails4);
		emplyeeList.add(employeeDetails5);
		emplyeeList.add(employeeDetails6);
		
		return emplyeeList;
		
    }
    
    

}


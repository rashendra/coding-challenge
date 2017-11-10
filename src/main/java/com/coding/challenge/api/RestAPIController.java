package com.coding.challenge.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;
import com.coding.challenge.service.FetchOrganizationHierarchyService;

import static com.coding.challenge.api.ApplicationConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 *  This class deals with API operations for Suburb Information.
 */
@RestController
@RequestMapping(value = EMP_HEIRARCHY_API_V0, produces = APPLICATION_JSON_VALUE)
public class RestAPIController {

	private static Logger 	LOGGER = Logger.getLogger(RestAPIController.class);
	
	
	@Autowired
	FetchOrganizationHierarchyService fetchOrganizationHierarchyService;
	

	@RequestMapping(value = FETCH , method= RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
    public ViewEmployeeHierarchyResponse fetch(@Valid @RequestBody ViewEmployeeHierarchyRequest request)
    {
		ViewEmployeeHierarchyResponse response = null;
		LOGGER.info("Executing the fetch method ");
	    	try 
	    	{
	    		response = fetchOrganizationHierarchyService.fetchOrganizationalHierarchy(request);
		} 
	    	catch (EmployeeHierarchyValidationException e) 
	    	{
	    		LOGGER.error("Error in the fetch method: ");
	    		e.printStackTrace();
	    		populateErrorResponse(response);
		}
	    	
	    	return response;
    }
	
	@RequestMapping(value = INITIALIZE , method= RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
    public void initialize()
    {
		try 
		{
			fetchOrganizationHierarchyService.initialize();
			
		} catch (EmployeeHierarchyValidationException e) 
		{
			LOGGER.error("Error in the fetch method: ");
		}
	    	
    }
	
	private void populateErrorResponse(ViewEmployeeHierarchyResponse response)
	{
		LOGGER.info("populateErrorResponse: ");
		response  = new ViewEmployeeHierarchyResponse();
		response.setErrorMessage("Error in API ");
		response.setResponseStatus("Failure");
	}
	
	@RequestMapping(value = RETRIEVE_VIEW_EMP_HIERARCHY_REQ , method= RequestMethod.GET)
	@ResponseBody
	public ViewEmployeeHierarchyRequest  retrieveManageContactsRequest()
	{
		LOGGER.info("executing retrieveManageContactsRequest: ");
		ViewEmployeeHierarchyRequest request = new ViewEmployeeHierarchyRequest();
		request.setLimitHeirarchy("10");
		return request;
	}
	
  
}

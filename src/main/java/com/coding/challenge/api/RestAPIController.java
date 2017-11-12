package com.coding.challenge.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.domain.EmployeeDetails;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;
import com.coding.challenge.dto.ViewEmployeeHierarchyResponse;
import com.coding.challenge.service.EmployeeDetailsService;


import static com.coding.challenge.api.ApplicationConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

/**
 * This class deals with API operations for Suburb Information.
 */
@RestController
@RequestMapping(value = EMP_DETAILS_API_V0, produces = APPLICATION_JSON_VALUE)
public class RestAPIController {

	private static Logger LOGGER = Logger.getLogger(RestAPIController.class);

	@Autowired
	EmployeeDetailsService employeeDetailsService;

	@RequestMapping(value = FETCH_EMP_HIERARCHY, method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ViewEmployeeHierarchyResponse fetch(@Valid @RequestBody ViewEmployeeHierarchyRequest request) {
		ViewEmployeeHierarchyResponse response = null;
		LOGGER.info("Executing the fetch method ");
		try {
			response = employeeDetailsService.fetchOrganizationalHierarchy(request);
		} catch (EmployeeHierarchyValidationException e) {
			LOGGER.error("Error in the fetch method: ");
			e.printStackTrace();
			populateErrorResponse(response);
		}

		return response;
	}
	
	
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeDetails>> fetchAll()
			throws URISyntaxException, EmployeeHierarchyValidationException {
		List<EmployeeDetails> fetchAll = employeeDetailsService.fetchAll();
		return new ResponseEntity<>(fetchAll, HttpStatus.OK);
	}
	
	 @PostMapping(CREATE)
	 public ResponseEntity<EmployeeDetails> create(@Valid @RequestBody EmployeeDetails employeeDetails) throws URISyntaxException {
	       
		 LOGGER.info("Executing the create EMployee method ::");
		 LOGGER.info("Employee Name :"+employeeDetails.getEmployeeName());
		 LOGGER.info("Employee manager Id :"+employeeDetails.getManagerId());
		 
		 EmployeeDetails result = employeeDetailsService.create(employeeDetails);
	     return new ResponseEntity<>(result, HttpStatus.CREATED);
	  }

	@RequestMapping(value = INITIALIZE, method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void initialize() {
		try {
			employeeDetailsService.initialize();

		} catch (EmployeeHierarchyValidationException e) {
			LOGGER.error("Error in the fetch method: ");
		}

	}
	
	@RequestMapping(value = CLEAR_DB, method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void clearDb() {
		try {
			employeeDetailsService.initialize();

		} catch (EmployeeHierarchyValidationException e) {
			LOGGER.error("Error in the fetch method: ");
		}

	}

	private void populateErrorResponse(ViewEmployeeHierarchyResponse response) {
		LOGGER.info("populateErrorResponse: ");
		response = new ViewEmployeeHierarchyResponse();
		response.setErrorMessage("Error in API ");
		response.setResponseStatus("Failure");
	}

	@RequestMapping(value = RETRIEVE_VIEW_EMP_HIERARCHY_REQ, method = RequestMethod.GET)
	@ResponseBody
	public ViewEmployeeHierarchyRequest retrieveManageContactsRequest() {
		LOGGER.info("executing retrieveManageContactsRequest: ");
		ViewEmployeeHierarchyRequest request = new ViewEmployeeHierarchyRequest();
		request.setLimitHeirarchy("10");
		return request;
	}

}

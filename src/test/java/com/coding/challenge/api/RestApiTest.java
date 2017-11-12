package com.coding.challenge.api;

import io.restassured.RestAssured;

import static com.coding.challenge.api.ApplicationConstants.*;
import static com.coding.challenge.api.ApplicationConstants.CREATE;
import static com.coding.challenge.api.ApplicationConstants.INITIALIZE;
import static com.coding.challenge.api.ApplicationConstants.FETCH_ALL;
import static com.coding.challenge.api.ApplicationConstants.FETCH_EMP_HIERARCHY;
import static com.coding.challenge.TestDataObjectMother.getEmployeeDetails;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

import org.junit.Before;
import org.junit.Test;

import com.coding.challenge.BaseIntegrationTest;
import com.coding.challenge.dto.ViewEmployeeHierarchyRequest;

public class RestApiTest extends BaseIntegrationTest {


	@Before
	public void setUp() {
		employeeDetailsRepository.deleteAll();
	}

	@Test
	public void shouldCreateNewEmployeeDetailsForGivenEmployeeNameAndManagerId() {
		RestAssured.given().port(port).contentType(JSON).header(getValidAuthHeader())
				.body(getEmployeeDetails(null, "Rashendra", 10L)).when().post(EMP_DETAILS_API_V0 + CREATE).then()
				.statusCode(SC_CREATED);
	}

	@Test
	public void shouldInitializePreloadedEmployees() {
		RestAssured
		.given()
			.port(port)
			.contentType(JSON)
		.when()
			.get(EMP_DETAILS_API_V0 + INITIALIZE)
		.then()
				.statusCode(SC_CREATED);

	}
	
	@Test
	public void shouldReturnStatusOkWhenDataIsCleared() {
		RestAssured
		.given()
			.port(port)
			.contentType(JSON)
		.when()
			.get(EMP_DETAILS_API_V0 + CLEAR_DB)
		.then()
				.statusCode(SC_OK);

	}
	
	@Test
	public void shouldFetchAllEmployeeDetails() {
		RestAssured
		.given()
			.port(port)
			.contentType(JSON)
		.when()
			.get(EMP_DETAILS_API_V0 + FETCH_ALL)
		.then()
				.statusCode(SC_OK);

	}
	
	@Test 
	public void shouldFetchEmployeeHierarchy()
	{
		RestAssured
			.given()
				.port(port)
				.contentType(JSON)
				.header(getValidAuthHeader())
				.body(new ViewEmployeeHierarchyRequest())
			.when().post(EMP_DETAILS_API_V0 + FETCH_EMP_HIERARCHY)
			.then()
				.statusCode(SC_OK);
	}
	
	

}

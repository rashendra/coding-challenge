package com.coding.challenge.domain;

import static com.coding.challenge.TestDataObjectMother.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EmployeeDetailsTest {

	@Test
	public void shouldReturnTrueIfEmployeeDetailsIsMeaningfullyEqual() {
		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(100l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isTrue();
	}
	

	
	@Test
	public void shouldReturnTrueIfEmployeeDetailsIsMeaningfullyEqual1() {
		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(100l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isTrue();
	}
	@Test
	public void shouldReturnFalseIfEmployeeDetailsIsNotMeaningfullyEqual() {

		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(101l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isFalse();
	}

	@Test
	public void shouldReturnFalseIfEmployeeDetailsIsNotMeaningfullyEqual1() {

		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(101l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isFalse();
	}
}

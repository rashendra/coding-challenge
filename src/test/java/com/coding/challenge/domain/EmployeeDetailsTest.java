package com.coding.challenge.domain;

import static com.coding.challenge.TestDataObjectMother.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class EmployeeDetailsTest {

	@Test
	public void shouldReturnTrueIfContactIsMeaningfullyEqual() {
		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(100l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isTrue();
	}

	@Test
	public void shouldReturnFalseIfContactIsNotMeaningfullyEqual() {

		// given
		EmployeeDetails emplyeeDetails = getEmployeeDetails(101l, "Alan", 150l);
		EmployeeDetails emplyeeDetailsTwo = new EmployeeDetails(100l, "Alan", 150l);

		// when
		boolean isEqual = emplyeeDetails.equals(emplyeeDetailsTwo);
		// then
		Assertions.assertThat(isEqual).isFalse();
	}
}

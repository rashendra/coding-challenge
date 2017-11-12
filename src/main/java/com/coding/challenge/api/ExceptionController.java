package com.coding.challenge.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.coding.challenge.domain.ApiErrorMessage;

import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * This class intercepts with the exceptions and return HTTP Error code 400 to
 * users.
 */
@ControllerAdvice
public class ExceptionController {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ Exception.class, EmployeeHierarchyValidationException.class })
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public ApiErrorMessage handleValidationException(MethodArgumentNotValidException error) {
		BindingResult result = error.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();

		final ApiErrorMessage apiError = new ApiErrorMessage(fieldErrors.iterator().next().getDefaultMessage(), "Er02");
		return apiError;
	}

}

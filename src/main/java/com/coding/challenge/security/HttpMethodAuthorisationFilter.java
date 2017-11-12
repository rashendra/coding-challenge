package com.coding.challenge.security;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

/**
 * This filter will invoke for every incoming request and responsible for
 * validating valid API-KEY is provided in Http header for POST, PATCH, DELETE
 * and PUT requests. If the request does not contains a valid API Key, HTTP
 * status 403 will be returned.
 */
// public class HttpMethodAuthorisationFilter extends ApiAuthorisationFilter{
public class HttpMethodAuthorisationFilter extends ApiAuthorisationFilter {

	public static final String API_KEY = "API-KEY";

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	protected static final String SECRET = "xJ9a34fo";

	protected static final String UNAUTHORISED = "You are not authorised to access this function";

	private static Logger LOGGER = Logger.getLogger(HttpMethodAuthorisationFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		LOGGER.info(" Authenticating the any POST request ");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (isSecureHttpMethod(request) && notContainsValidApiKeyIn(request)) {

			((HttpServletResponse) response).sendError(SC_FORBIDDEN, UNAUTHORISED);
		} else {

			chain.doFilter(request, response);
		}
	}

	/*
	 * This method can be used if we get the credentials from the request ,
	 * using an authenticationManager
	 */
	private boolean authentivateRequest(HttpServletRequest httpServletRequest) {
		//
		String userName = httpServletRequest.getHeader(USERNAME);
		String password = httpServletRequest.getHeader(PASSWORD);
		return Boolean.TRUE;
	}

	private boolean isSecureHttpMethod(ServletRequest request) {
		HttpMethod methodInvoking = HttpMethod.valueOf(((HttpServletRequest) request).getMethod());
		return getSecureHttpMethods().contains(methodInvoking);
	}

	private boolean notContainsValidApiKeyIn(ServletRequest request) {
		return !SECRET.equals(((HttpServletRequest) request).getHeader(API_KEY));
	}

	private Set<HttpMethod> getSecureHttpMethods() {
		return new HashSet<>(Arrays.asList(HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.POST, HttpMethod.PUT));
	}

}

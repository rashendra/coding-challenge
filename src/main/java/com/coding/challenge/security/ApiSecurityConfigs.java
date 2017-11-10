package com.coding.challenge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 *  API HttpMethodAuthorisationFilter is registered in this configuration class.
 */
@Configuration
public class ApiSecurityConfigs extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
       http.addFilterBefore(getHttpMethodAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
       http.authorizeRequests().antMatchers("/api/*").permitAll().and().csrf().disable();
 
    }

    /**
     * Gets the http method authorisation filter.
     *
     * @return the http method authorisation filter
     */
    private Filter getHttpMethodAuthorisationFilter() 
    {
            return new HttpMethodAuthorisationFilter();
    }
}
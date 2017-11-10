package com.coding.challenge.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.coding.challenge.security.HttpMethodAuthorisationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.coding.challenge.security.HttpMethodAuthorisationFilter.SECRET;
import static org.mockito.Mockito.when;

public class HttpMethodAuthorisationFilterTest {

    private HttpMethodAuthorisationFilter methodAuthorisationFilter;

    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private FilterChain chain = Mockito.mock(FilterChain.class);

    @Before
    public void setUp(){
        methodAuthorisationFilter = new HttpMethodAuthorisationFilter();
    }

    @Test
    public void shouldCallDoFilterIfTheHttpMethodIsPostAndHasValidApiKey() throws IOException, ServletException {
        //given
        when(request.getMethod()).thenReturn("POST");
        when(request.getHeader(Mockito.anyString())).thenReturn(SECRET);

        //when
        methodAuthorisationFilter.doFilter(request, response, chain);

        //then
        Mockito.verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldCallDoFilterIfTheHttpMethodIsGet() throws IOException, ServletException {
        //given
        when(request.getMethod()).thenReturn("GET");

        //when
        methodAuthorisationFilter.doFilter(request, response, chain);

        //then
        Mockito.verify(chain).doFilter(request, response);
    }

    @Test
    public void shouldReturn_SC_FORBIDDEN_IfTheHttpMethodIsPostAndHasInvalidApiKeySecret() throws IOException, ServletException {
        //given
        when(request.getMethod()).thenReturn("POST");
        when(request.getHeader(Mockito.anyString())).thenReturn("Invalid secret");

        //when
        methodAuthorisationFilter.doFilter(request, response, chain);

        //then
        Mockito.verify(chain, Mockito.times(0)).doFilter(request, response);
        Mockito.verify(response, Mockito.times(1)).sendError(Mockito.anyInt(), Mockito.anyString());
    }
}

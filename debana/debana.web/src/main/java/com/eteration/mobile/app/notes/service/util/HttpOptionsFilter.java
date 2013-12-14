package com.eteration.mobile.app.notes.service.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class HttpOptionsFilter
 */
public class HttpOptionsFilter implements Filter {

  
    public HttpOptionsFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		   HttpServletRequest httpRequest = (HttpServletRequest) request; 
		   HttpServletResponse httpResponse = (HttpServletResponse) response;    
	       if(httpRequest.getMethod().equalsIgnoreCase("OPTIONS")){
	    	   
	    	   httpResponse.addHeader("Access-Control-Allow-Origin", "*");
	    	   httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
	    	   httpResponse.addHeader("Content-Type", "application/json");
	    	   String returnMethod = httpRequest.getHeader("access-control-request-headers");
	    	   
	   		if (returnMethod!=null && !"".equals(returnMethod)) {
	   			httpResponse.addHeader("Access-Control-Allow-Headers", returnMethod);
	   		}
	   		
	       }else{
	    	   chain.doFilter(request, response);
	       }
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

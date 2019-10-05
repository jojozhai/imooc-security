/**
 * 
 */
package com.imooc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author jojo
 *
 */
public class GatewayAuditLogFilter extends OncePerRequestFilter {

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("1. add log for "+user);
		
		filterChain.doFilter(request, response);
		
		if(StringUtils.isBlank((String) request.getAttribute("logUpdated"))) {
			System.out.println("3. update log to success");
		}
		
	}

}

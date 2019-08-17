/**
 * 
 */
package com.imooc.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author jojo
 *
 */
@Component
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {
	
	private RateLimiter rateLimiter = RateLimiter.create(1);

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println(1);
		
		if(rateLimiter.tryAcquire()) {
			filterChain.doFilter(request, response);
		}else {
			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			response.getWriter().write("too many request!!!");
			response.getWriter().flush();
			return;
		}
		
	}

}

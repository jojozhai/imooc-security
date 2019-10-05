/**
 * 
 */
package com.imooc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author jojo
 *
 */
@Component
public class GatewayAccessDeniedHandler extends OAuth2AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
			throws IOException, ServletException {
		
		System.out.println("2. update log to 403");
		
		request.setAttribute("logUpdated", "yes");
		
		super.handle(request, response, authException);
	}
	
}

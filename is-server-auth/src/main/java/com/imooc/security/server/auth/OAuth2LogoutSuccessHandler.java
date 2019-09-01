/**
 * 
 */
package com.imooc.security.server.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author jojo
 *
 */
@Component
public class OAuth2LogoutSuccessHandler implements LogoutSuccessHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String redirectUri = request.getParameter("redirect_uri");
		if(!StringUtils.isEmpty(redirectUri)) {
			response.sendRedirect(redirectUri);
		}
	}

}

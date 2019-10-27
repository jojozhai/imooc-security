/**
 * 
 */
package com.imooc.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.user.User;
import com.imooc.security.user.UserRepository;
import com.lambdaworks.crypto.SCryptUtil;

/**
 * @author jojo
 *
 */
//@Component
//@Order(2)
public class BasicAuthecationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println(2);

		String authHeader = request.getHeader("Authorization");
		
		if(StringUtils.isNotBlank(authHeader)) {
			
			String token64 = StringUtils.substringAfter(authHeader, "Basic ");
			String token = new String(Base64Utils.decodeFromString(token64));
			String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");
			
			String username = items[0];
			String password = items[1];
			
			User user = userRepository.findByUsername(username);
			
			if(user != null && SCryptUtil.check(password, user.getPassword())) {
				request.getSession().setAttribute("user", user.buildInfo());
				request.getSession().setAttribute("temp", "yes");
			}
		}
		
		try {
			
			filterChain.doFilter(request, response);
			
		} finally {
			
			HttpSession session = request.getSession();
			if(session.getAttribute("temp") != null) {
				session.invalidate();
			}
			
		}
		
		
	}

}

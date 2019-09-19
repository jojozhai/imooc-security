/**
 * 
 */
package com.imooc.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author jojo
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	/* (non-Javadoc)
	 * @see com.imooc.security.PermissionService#hasPermission(javax.servlet.http.HttpServletRequest, org.springframework.security.core.Authentication)
	 */
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		System.out.println("2 authorize");
		System.out.println(request.getRequestURI());
		System.out.println(ReflectionToStringBuilder.toString(authentication));
		return RandomUtils.nextInt() % 2 == 0;
	}

}

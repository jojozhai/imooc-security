/**
 * 
 */
package com.imooc.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

/**
 * @author jojo
 *
 */
public interface PermissionService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}

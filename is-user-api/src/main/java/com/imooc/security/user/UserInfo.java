/**
 * 
 */
package com.imooc.security.user;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * @author jojo
 *
 */
@Data
public class UserInfo {
	
	private Long id;
	
	private String name;
	
	@NotBlank(message = "用户名不能为空")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;

	private String permissions;

	public boolean hasPermission(String method) {
		boolean result = false;
		if(StringUtils.equalsIgnoreCase("get", method)) {
			result = StringUtils.contains(permissions, "r");
		}else {
			result = StringUtils.contains(permissions, "w");
		}
		return result;
	}
}

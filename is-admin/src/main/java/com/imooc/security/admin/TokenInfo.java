/**
 * 
 */
package com.imooc.security.admin;

import lombok.Data;

/**
 * @author jojo
 *
 */
@Data
public class TokenInfo {

	private String access_token;
	private String refresh_token;
	private String token_type;
	private String expires_in;
	private String scope;
	
}

/**
 * 
 */
package com.imooc.security.admin;

import java.time.LocalDateTime;

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
	private Long expires_in;
	private String scope;
	
	private LocalDateTime expireTime;
	
	public void init() {
		expireTime = LocalDateTime.now().plusSeconds(expires_in);
	}

	public boolean isExpired() {
		return expireTime.isBefore(LocalDateTime.now());
	}
	
}

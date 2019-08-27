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
	
	public TokenInfo init() {
		this.expireTime = LocalDateTime.now().plusSeconds(expires_in - 3);
		return this;
	}
	
	public boolean isExpired() {
		return this.expireTime.isBefore(LocalDateTime.now());
	}
}

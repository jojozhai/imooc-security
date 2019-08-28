/**
 * 
 */
package com.imooc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jojo
 *
 */
@SpringBootApplication
@RestController
public class OAuth2AuthServer {
	
	/**
	 * 
	 * @author jojo
	 * 2019年8月18日
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OAuth2AuthServer.class, args);
	}

}

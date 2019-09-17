/**
 * 
 */
package com.imooc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author jojo
 *
 */

@SpringBootApplication
@EnableResourceServer
public class PriceApi {

	/**
	 * 
	 * @author jojo
	 * 2019年8月4日
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PriceApi.class, args);
	}

}

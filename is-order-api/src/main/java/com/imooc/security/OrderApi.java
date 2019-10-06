/**
 * 
 */
package com.imooc.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.imooc.security.config.SentinelRequestInterceptor;

/**
 * @author jojo
 *
 */

@Configuration
@SpringBootApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OrderApi {
	
	@Autowired
	private SentinelRequestInterceptor sentinelRequestInterceptor;

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, context);
		restTemplate.setInterceptors(Collections.singletonList(sentinelRequestInterceptor));
		return restTemplate;
	}
	
	/**
	 * 
	 * @author jojo
	 * 2019年8月4日
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderApi.class, args);
	}

}

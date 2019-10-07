/**
 * 
 */
package com.imooc.security.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author jojo
 *
 */
@Configuration
public class ActuatorSecurity extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
			.anyRequest().authenticated();
	}

}

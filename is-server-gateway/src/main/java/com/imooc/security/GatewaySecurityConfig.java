/**
 * 
 */
package com.imooc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * @author jojo
 *
 */
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private GatewayWebSecurityExpressionHandler gatewayWebSecurityExpressionHandler;
	
	@Autowired
	private GatewayAccessDeniedHandler gatewayAccessDeniedHandler;
	
	@Autowired
	private GatewayAuthenticationEntryPoint gatewayAuthenticationEntryPoint;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.authenticationEntryPoint(gatewayAuthenticationEntryPoint)
			.accessDeniedHandler(gatewayAccessDeniedHandler)
			.expressionHandler(gatewayWebSecurityExpressionHandler);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new GatewayAuditLogFilter(), ExceptionTranslationFilter.class)
			.addFilterBefore(new GatewayRateLimitFilter(), SecurityContextPersistenceFilter.class)
			.authorizeRequests()
			.antMatchers("/token/**").permitAll()
			.anyRequest().access("#permissionService.hasPermission(request, authentication)");
	}
	
}

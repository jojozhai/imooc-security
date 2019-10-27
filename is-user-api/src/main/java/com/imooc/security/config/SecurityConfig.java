/**
 * 
 */
package com.imooc.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.imooc.security.filter.AclInterceptor;
import com.imooc.security.filter.AuditLogInterceptor;
import com.imooc.security.user.UserInfo;

/**
 * @author jojo
 *
 */
@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private AuditLogInterceptor auditLogInterceptor;
//	
//	@Autowired
//	private AclInterceptor aclInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(auditLogInterceptor);
//		registry.addInterceptor(aclInterceptor);
//	}
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
				UserInfo info = (UserInfo)servletRequestAttributes.getRequest().getSession().getAttribute("user");
				String username = null;
				if(info != null) {
					username = info.getUsername();
				}
				return Optional.ofNullable(username);
			}};
	}
	
}

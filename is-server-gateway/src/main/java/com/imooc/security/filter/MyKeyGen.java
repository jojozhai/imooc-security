/**
 * 
 */
package com.imooc.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.Policy;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;

/**
 * @author jojo
 *
 */
@Component
public class MyKeyGen extends DefaultRateLimitKeyGenerator {

	public MyKeyGen(RateLimitProperties properties,
			RateLimitUtils rateLimitUtils) {
		super(properties, rateLimitUtils);
	}
	
	@Override
	public String key(HttpServletRequest request, Route route, Policy policy) {
		// TODO Auto-generated method stub
		return super.key(request, route, policy);
	}
}

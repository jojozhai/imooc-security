/**
 * 
 */
package com.imooc.security.filter;

import org.springframework.stereotype.Component;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;

/**
 * @author jojo
 *
 */
@Component
public class MyRateLimitErrorHandler extends DefaultRateLimiterErrorHandler {

	@Override
	public void handleError(String msg, Exception e) {
		super.handleError(msg, e);
	}
}

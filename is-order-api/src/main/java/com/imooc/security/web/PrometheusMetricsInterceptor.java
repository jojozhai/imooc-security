/**
 * 
 */
package com.imooc.security.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.prometheus.client.Counter;

/**
 * @author jojo
 *
 */
@Component
public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Counter requestCounter;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String service = ((HandlerMethod)handler).getMethod().getName();
		requestCounter.labels(service, request.getMethod(), String.valueOf(response.getStatus()))
					  .inc();
	
	}
	
}

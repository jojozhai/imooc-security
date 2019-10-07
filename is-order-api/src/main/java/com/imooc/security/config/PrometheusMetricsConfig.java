/**
 * 
 */
package com.imooc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Counter;

/**
 * @author jojo
 *
 */
@Configuration
public class PrometheusMetricsConfig {

	@Autowired
	private PrometheusMeterRegistry registry;
	
	@Bean
	public Counter requestCounter() {
		return Counter.build("is_request_count", "count request by service")
				.labelNames("service", "method", "code")
				.register(registry.getPrometheusRegistry());
	}
	
}

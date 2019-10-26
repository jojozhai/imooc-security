/**
 * 
 */
package com.imooc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

/**
 * @author jojo
 *
 */
@Configuration
public class PrometheusMetricsConfig {

	@Autowired
	private PrometheusMeterRegistry prometheusMeterRegistry;
	
	@Bean
	public Counter requestCounter() {
		return Counter.build("is_request_count","count request by service")
				.labelNames("service", "method", "code")
				.register(prometheusMeterRegistry.getPrometheusRegistry());
	}
	
	@Bean
	public Summary requestLatency() {
		return Summary.build("is_request_latency","monite request latency by service")
				.quantile(0.5, 0.05)
				.quantile(0.9, 0.01)
				.labelNames("service", "method", "code")
				.register(prometheusMeterRegistry.getPrometheusRegistry());
	}
	
}

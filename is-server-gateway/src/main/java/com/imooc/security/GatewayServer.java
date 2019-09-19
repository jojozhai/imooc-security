/**
 * 
 */
package com.imooc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

/**
 * @author jojo
 *
 */
@Configuration
@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {
	
	/**
	 * 
	 * @author jojo
	 * 2019年8月18日
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayServer.class, args);
	}

}

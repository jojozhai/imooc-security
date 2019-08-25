/**
 * 
 */
package com.imooc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author jojo
 *
 */
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

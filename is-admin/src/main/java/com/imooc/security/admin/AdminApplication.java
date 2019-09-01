/**
 * 
 */
package com.imooc.security.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jojo
 *
 */
@SpringBootApplication
@RestController
@EnableZuulProxy
public class AdminApplication {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
	}

	@PostMapping("/login")
	public void login (@RequestBody Credentials credentials, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String oauthServiceUrl = "http://gateway.imooc.com:9070/token/oauth/token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth("admin", "123456");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", credentials.getUsername());
		params.add("password", credentials.getPassword());
		params.add("grant_type", "password");
		params.add("scope", "read write");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		
		ResponseEntity<TokenInfo> token = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
		request.getSession().setAttribute("token", token.getBody());
		
	}
	
	/**
	 * 
	 * @author jojo
	 * 2019年8月25日
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}

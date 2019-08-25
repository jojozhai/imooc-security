/**
 * 
 */
package com.imooc.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jojo
 *
 */
@Slf4j
@Component
public class OAuthFilter extends ZuulFilter {
	
	private RestTemplate restTemplate = new RestTemplate();

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() throws ZuulException {
		
		log.info("oauth start");
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		if(StringUtils.startsWith(request.getRequestURI(), "/token")) {
			return null;
		}
		
		String authHeader = request.getHeader("Authorization");
		
		if(StringUtils.isBlank(authHeader)) {
			return null;
		}
		
		if(!StringUtils.startsWithIgnoreCase(authHeader, "bearer ")) {
			return null;
		}
		
		try {
			
			TokenInfo info = getTokenInfo(authHeader);
			request.setAttribute("tokenInfo", info);
			
		} catch (Exception e) {
			log.error("get token info fail", e);
		}
		
		return null;
	}

	private TokenInfo getTokenInfo(String authHeader) {
		
		String token = StringUtils.substringAfter(authHeader, "bearer ");
		String oauthServiceUrl = "http://localhost:9090/oauth/check_token";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth("gateway", "123456");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("token", token);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		
		ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
		
		log.info("token info :" + response.getBody().toString());
		
		return response.getBody();
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

}

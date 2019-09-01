/**
 * 
 */
package com.imooc.security.admin;

import javax.servlet.http.HttpServletRequest;

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

/**
 * @author jojo
 *
 */
@Component
public class SessionTokenFilter extends ZuulFilter {
	
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
		
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		
		TokenInfo token = (TokenInfo)request.getSession().getAttribute("token");
		
		if(token != null) {
			String tokenValue = token.getAccess_token();
			if(token.isExpired()) {
				String oauthServiceUrl = "http://gateway.imooc.com:9070/token/oauth/token";
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				headers.setBasicAuth("admin", "123456");
				
				MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
				params.add("grant_type", "refresh_token");
				params.add("refresh_token", token.getRefresh_token());
				
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
				
				try {
					ResponseEntity<TokenInfo> newToken = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);
					request.getSession().setAttribute("token", newToken.getBody().init());
					tokenValue = newToken.getBody().getAccess_token();
				} catch (Exception e) {
					requestContext.setSendZuulResponse(false);
					requestContext.setResponseStatusCode(500);
					requestContext.setResponseBody("{\"message\":\"refresh fail\"}");
					requestContext.getResponse().setContentType("application/json");
				}
				
			}
			requestContext.addZuulRequestHeader("Authorization", "bearer "+tokenValue);
		}
		
		return null;
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
		return 0;
	}

}

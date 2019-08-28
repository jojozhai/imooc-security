/**
 * 
 */
package com.imooc.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author jojo
 *
 */
@Component
public class MeFilter extends ZuulFilter {

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		return StringUtils.equals(request.getRequestURI(), "/user/me");
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		requestContext.getResponse().setContentType("application/json");
		requestContext.setResponseStatusCode(200);
		String user = requestContext.getZuulRequestHeaders().get("username");
		if(StringUtils.isNotBlank(user)) {
			requestContext.setResponseBody("{\"username\":\""+user+"\"}");
		}
		requestContext.setSendZuulResponse(false);
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
		return 4;
	}

}

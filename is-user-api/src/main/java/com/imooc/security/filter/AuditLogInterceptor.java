/**
 * 
 */
package com.imooc.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.imooc.security.log.AuditLog;
import com.imooc.security.log.AuditLogRepository;

/**
 * @author jojo
 *
 */
@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private AuditLogRepository auditLogRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println(3);

		AuditLog log = new AuditLog();
		log.setMethod(request.getMethod());
		log.setPath(request.getRequestURI());
		
		auditLogRepository.save(log);
		
		request.setAttribute("auditLogId", log.getId());
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		Long auditLogId = (Long) request.getAttribute("auditLogId");
		
		AuditLog log = auditLogRepository.findById(auditLogId).get();
		
		log.setStatus(response.getStatus());
		
		auditLogRepository.save(log);
		
	}

}

/**
 * 
 */
package com.imooc.security.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jojo
 *
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandler {
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> handle(Exception ex){
		log.error("system error", ex);
		Map<String, Object> info = new HashMap<>();
		info.put("message", ex.getMessage());
		info.put("time", new Date().getTime());
		return info;
	}
	

}

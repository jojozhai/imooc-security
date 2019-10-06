/**
 * 
 */
package com.imooc.security.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jojo
 *
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	
	@Autowired
	private OAuth2RestTemplate restTemplate;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SentinelResource(value = "createOrder", blockHandler = "doOnBlock")
	public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) throws InterruptedException {
		log.info("user is " + username);
		String price = restTemplate.getForObject("http://localhost:9060/prices/"+info.getProductId(), String.class);
		log.info("price is "+price);
		Thread.sleep(50);
		return info;
	}
	
	public OrderInfo doOnBlock(@RequestBody OrderInfo info, @AuthenticationPrincipal String username, BlockException exception) throws InterruptedException {
		log.info("blocked by " + exception.getClass().getSimpleName());
		return info;
	}
	
	@GetMapping("/{id}")
	@SentinelResource("getOrder")
	public OrderInfo getInfo(@PathVariable Long id, @AuthenticationPrincipal String username) throws InterruptedException {
		log.info("user is " + username);
		log.info("orderId is " + id);
		OrderInfo info = new OrderInfo();
		info.setId(id);
		info.setProductId(id * 5);
		return info;
	}

}

/**
 * 
 */
package com.imooc.security.order;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jojo
 *
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	
//	@Autowired
//	private OAuth2RestTemplate restTemplate;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SentinelResource("createOrder")
	public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) throws InterruptedException {
		log.info("user is " + username);
//		PriceInfo price = restTemplate.getForObject("http://localhost:9060/prices/"+info.getProductId(), PriceInfo.class);
//		log.info("price is "+price.getPrice());
//		throw new RuntimeException("haha, test");
		return info;
	}
	
	@GetMapping("/{id}")
	public OrderInfo getInfo(@PathVariable Long id, @AuthenticationPrincipal String username) {
		log.info("user is " + username);
		log.info("orderId is " + id);
		OrderInfo info = new OrderInfo();
		info.setId(id);
		info.setProductId(id * 5);
		return info;
	}

}

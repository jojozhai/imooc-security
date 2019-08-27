/**
 * 
 */
package com.imooc.security.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jojo
 *
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	
//	private RestTemplate restTemplate = new RestTemplate();
	
	@PostMapping
	public OrderInfo create(@RequestBody OrderInfo info, @RequestHeader String username) {
		log.info("user is " + username);
//		PriceInfo price = restTemplate.getForObject("http://localhost:9060/prices/"+info.getProductId(), PriceInfo.class);
//		log.info("price is "+price.getPrice());
		return info;
	}
	
	@GetMapping("/{id}")
	public OrderInfo getInfo(@PathVariable Long id, @RequestHeader String username) {
		log.info("user is " + username);
		log.info("orderId is " + id);
		OrderInfo info = new OrderInfo();
		info.setId(id);
		info.setProductId(id * 5);
		return info;
	}

}

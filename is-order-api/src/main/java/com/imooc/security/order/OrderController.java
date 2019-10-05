/**
 * 
 */
package com.imooc.security.order;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
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

	// @Autowired
	// private OAuth2RestTemplate restTemplate;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) {
		try (Entry entry = SphU.entry("createOrder")) {
			log.info("user is " + username);
		} catch (BlockException ex) {
			log.info("blocked!");
		}
		// PriceInfo price =
		// restTemplate.getForObject("http://localhost:9060/prices/"+info.getProductId(),
		// PriceInfo.class);
		// log.info("price is "+price.getPrice());
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

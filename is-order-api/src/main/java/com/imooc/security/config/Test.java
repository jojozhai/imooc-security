/**
 * 
 */
package com.imooc.security.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.imooc.security.order.OrderInfo;

/**
 * @author jojo
 *
 */
public class Test {

	/**
	 * 
	 * @author jojo
	 * 2019年10月7日
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzA0MjExODcsInVzZXJfbmFtZSI6Impvam8iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjI3NTkzNDM2LWZiMDYtNDMyMC05OGY1LWVkYmUwMTgxZWQ4NCIsImNsaWVudF9pZCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.VJO-u8VGYUh-N2IkDorO4ZCzwtjGaw3q1PrtcZnCGcq7K6VcB3HfwYGhgGMzInHCSojD7pe84CfA75-4tUsuEeX6qUKaWyBRv9ch9BHt-Bd4zgzvEXFQ9XWUoJz44dXSoQa53bFv4GYeo_UmBOzfJ2zNm12mLfp8gizgsGFyRZWJ2WPmNSPe9z2J4MDJeeyVUFTdawoiX0T3GIKeWk8qCJJLvNP6jPogqXuROIafPn9wt_ZkaBsMh0TJyRJ2F6fmeBNPHJoyizexKEgLKHIJRetCVVlkhFKZ9a8joYInKt6wXxS4pCfUJJ_6t1rwr8DsHyxNrvMdilwZAKhdUcRGQg");
		
		OrderInfo info = new OrderInfo();
		info.setProductId(123L);
		
		HttpEntity<OrderInfo> entity = new HttpEntity<OrderInfo>(info, headers);
		
		while (true) {
			try {
				restTemplate.exchange("http://order.imooc.com:9082/orders", HttpMethod.POST, entity, String.class);
			} catch (Exception e) {
			}
			Thread.sleep(100);
		}
	}

}

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
public class Tester {

	/**
	 * 
	 * @author jojo
	 * 2019年10月6日
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzAzNzgyMTMsInVzZXJfbmFtZSI6Impvam8iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjI5NmRhZWFlLTZmNTYtNDVjOS1hZDJhLTYxOWUzOWFhZmVmNCIsImNsaWVudF9pZCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.NXNakrsSHjY_DyQ3j9_NpqsAj1FYQQk4v-rEAV_ENIsc3bv6kZNdqD44z6kdE2Ev2QlnMtlhfoM2-sxAdm7Qr9b8PelpLBMqwfX5Qg0-fUEuCd2sEX6gfqwDR0hKHH1mYMLZAo18Hv4gL_6PO_Tngvi7hLt0YtnvAX_vOMY1eWz3RTvYiHsrdzQk9DhSZDas1akeZ2yS9HkQEu0p0BfpdtHX3UF0FaPt8caFFPrCoZ4PCqTwvTBQf7R81mGpFlHEu3awwf-Uj17itS1UBHfkue5v2KEt6RaO8IZ5e-TWIWpr0GcbutumqZnfPjv3EwSntf00Uq5FJ9TpxNO1C3qvuA");
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setProductId(1L);
		
		HttpEntity<OrderInfo> entity = new HttpEntity<>(orderInfo, headers);
		
		while (true) {
			try {
				restTemplate.exchange("http://order.imooc.com:9082/orders", HttpMethod.POST, entity, String.class);
			} catch (Exception e) {
				
			}
			Thread.sleep(100);
		}
		
		
	}

}

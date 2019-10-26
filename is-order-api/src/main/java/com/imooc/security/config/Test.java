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
		headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzA0NDgyOTcsInVzZXJfbmFtZSI6Impvam8iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjU0OWU5ZDg1LWI4YTYtNDY4Ny05NDM2LTRmZmE4MzViY2RiYiIsImNsaWVudF9pZCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.GcUeMKt4VUqFjQrZ_KAOAZCbCq_mBpWwkU9RYCspCtysgKR6ePnBmoQmjmM8J1KeMqhSdfVui0d-sdjFOD33kRdJpfjIXx7gL5D2-goD-itpdzx6qSYSI6AULniQvFnVIqWtVzuZ64BoNifz-VibgY4M9xOi89XoBOvW2q83akiA0AqzZoWXGMKD-pimgs9SCD11rwgaEk7nzJdJY2fgQxb0ZeWqIFgTDPqrvl6pfpZJ-_-x6GmpMXHUFGJGg0hrDoIrisTjlLJ3NwTTxArSFB0rR36Uj5N52-ovSzlySorni7_LmTeHlyI0f85Un5-ycGkYjU3ig-Ume6fNYm2WZg");
		
		OrderInfo info = new OrderInfo();
		info.setProductId(123L);
		
		HttpEntity<OrderInfo> entity = new HttpEntity<OrderInfo>(info, headers);
		
		while (true) {
			try {
				restTemplate.exchange("http://order.imooc.com:9082/orders", HttpMethod.POST, entity, String.class);
			} catch (Exception e) {
			}
//			Thread.sleep(100);
		}
	}

}

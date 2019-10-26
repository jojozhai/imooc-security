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
		headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzIwNzcwMTMsInVzZXJfbmFtZSI6Impvam8iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6ImQyNjU0OGY3LWViYjUtNDk3My1iZWY3LTQwMTBjOWNiMGJhZCIsImNsaWVudF9pZCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.Svx3Z6ev-YuNkgku8yLdAhnT9ZhoKCjleIlgyBz_2j5j27f4c5wOMJcTl23PR3y77GUH3vWKj8j0tiohyBZT1m6CCUYL00CCkZ0u1po0sJtz7CxwWxkmsq7qq5IfXDgHVHur11kj-CLlKb_9wWTnWUvxPnDqByyjmpzEeT13Cgm0UJzkoV3bHe2f6JG5ktyqBi-5siUAF48V3Vuqxlcf_3KcqDsUtpmGSjHJS6_x1wbFixdewE6unOA06CL9lP2byktZz7xd_9xZtWcY3WK5N8kFbUa0NnD3dPB19AAoSuRfVw-Y_EqrxNDIG15GPtOmjOFs97al54Kz2Eqh6S86jQ");
		
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

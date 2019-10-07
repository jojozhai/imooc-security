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
		headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NzA0NDM4OTQsInVzZXJfbmFtZSI6Impvam8iLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjFlMzFlY2U1LTJjMmQtNDQ0My04NmE2LTYxMDhiODUyZWY3ZSIsImNsaWVudF9pZCI6ImFkbWluIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.JdykbwYxG6XtM3TRrnjAv75_Yb85FUEIFGyixqRBKgJ4YqPVKdCH4d9q9Vg7dlGrjW9PEGyZ9aXi0Xpk49tg4rkOhCTkjpWCxcwieI2cOKciiOUXT4N4z_klfL_xNm63sGDVoPlnik2ZoNj9SpUzYCOKBDTiVOW7htc-TjKhIxKFDg-WTUywa_21DV0042xEDfm_uxfCza3oT8fQLabcVugUhNHO0Us9NDKmyx-s0Ij2PGUCZceFQev0DBjWlg4Vom2kuiSW61ZYPAoPC9CYDn-QBNiAcNLaL43j6sVprLG9h7YwJPkvBFEc2CfoJe4-3CPZAd3N-jbWDKM2w4LjhA");
		
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

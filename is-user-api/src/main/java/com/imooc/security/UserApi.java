/**
 * 
 */
package com.imooc.security;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jojo
 *
 */

@SpringBootApplication
public class UserApi {

	/**
	 * 
	 * @author jojo
	 * 2019年8月4日
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1568863275457L)));
//		SpringApplication.run(UserApi.class, args);
	}

}

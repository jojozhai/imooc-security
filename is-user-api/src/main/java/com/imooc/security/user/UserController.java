/**
 * 
 */
package com.imooc.security.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jojo
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public void login(@Validated UserInfo user, HttpServletRequest request) throws IOException {
		UserInfo info = userService.login(user);
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		request.getSession(true).setAttribute("user", info);
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest request) throws IOException {
		request.getSession().invalidate();
	}
	
	@PostMapping
	public UserInfo create(@RequestBody @Validated UserInfo user) throws IOException {
		return userService.create(user);
	}
	
	@PutMapping("/{id}")
	public UserInfo update(@RequestBody UserInfo user) {
		return userService.update(user);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@GetMapping("/{id}")
	public UserInfo get(@PathVariable Long id, HttpServletRequest request) throws IOException {
//		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
//		
//		if(user == null || !user.getId().equals(id)) {
//			throw new RuntimeException("身份认证信息异常，获取用户信息失败");
//		}
		
		return userService.get(id);
	}
	
	@GetMapping
	public List<UserInfo> query(String name) {
		return userService.query(name);
	}

}

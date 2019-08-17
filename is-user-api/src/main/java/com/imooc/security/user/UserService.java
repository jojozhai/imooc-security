/**
 * 
 */
package com.imooc.security.user;

import java.io.IOException;
import java.util.List;

/**
 * @author jojo
 *
 */
public interface UserService {
	
	UserInfo create(UserInfo user) throws IOException;
	
	UserInfo update(UserInfo user);
	
	void delete(Long id);
	
	UserInfo get(Long id) throws IOException;
	
	List<UserInfo> query(String name);

	UserInfo login(UserInfo user);

}

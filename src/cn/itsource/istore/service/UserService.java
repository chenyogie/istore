package cn.itsource.istore.service;

import cn.itsource.istore.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {

	/**
	 * 注册用户
	 * @param user
	 */
	public void regist(User user);

	/**
	 * 根据激活码来激活用户
	 * @param activecode
	 */
	public void activeUser(String activecode);

	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByNameAndPassword(String username, String password);

	
	
}

package cn.itsource.istore.dao;

import java.sql.Connection;

import cn.itsource.istore.entity.User;

/**
 * 用户DAO接口
 */
public interface UserDAO {

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @param connection
	 * @return
	 */
	public User findUserByName(String username, Connection connection);

	/**
	 * 添加用户的方法
	 * 
	 * @param user
	 * @param connection
	 */
	public void addUser(User user, Connection connection);

	/**
	 * 改变用户状态吗的方法
	 * 
	 * @param id
	 */
	public void updateState(int id, int state);

	/**
	 * 根据激活码找到用户的方法
	 * 
	 * @param activecode
	 * @return
	 */
	public User findUserByActivecode(String activecode);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 */
	public void deleteUser(int id);

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByNameAndPassword(String username, String password);

}

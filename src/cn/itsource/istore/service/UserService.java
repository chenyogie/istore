package cn.itsource.istore.service;

import cn.itsource.istore.entity.User;

/**
 * �û�����ӿ�
 */
public interface UserService {

	/**
	 * ע���û�
	 * @param user
	 */
	public void regist(User user);

	/**
	 * ���ݼ������������û�
	 * @param activecode
	 */
	public void activeUser(String activecode);

	/**
	 * �����û�������������û�
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByNameAndPassword(String username, String password);

	
	
}

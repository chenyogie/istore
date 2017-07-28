package cn.itsource.istore.dao;

import java.sql.Connection;

import cn.itsource.istore.entity.User;

/**
 * �û�DAO�ӿ�
 */
public interface UserDAO {

	/**
	 * �����û��������û�
	 * 
	 * @param username
	 * @param connection
	 * @return
	 */
	public User findUserByName(String username, Connection connection);

	/**
	 * ����û��ķ���
	 * 
	 * @param user
	 * @param connection
	 */
	public void addUser(User user, Connection connection);

	/**
	 * �ı��û�״̬��ķ���
	 * 
	 * @param id
	 */
	public void updateState(int id, int state);

	/**
	 * ���ݼ������ҵ��û��ķ���
	 * 
	 * @param activecode
	 * @return
	 */
	public User findUserByActivecode(String activecode);

	/**
	 * ����idɾ���û�
	 * 
	 * @param id
	 */
	public void deleteUser(int id);

	/**
	 * �����û����������ѯ�û�
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserByNameAndPassword(String username, String password);

}

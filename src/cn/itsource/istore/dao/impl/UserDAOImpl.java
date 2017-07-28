package cn.itsource.istore.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itsource.istore.dao.UserDAO;
import cn.itsource.istore.entity.User;
import cn.itsource.istore.util.JDBCUtils;

/**
 * 用户dao接口实现类
 */
public class UserDAOImpl implements UserDAO {

	public User findUserByName(String username, Connection connection) {

		String sql = "select * from users where username=?";

		try {
			QueryRunner runner = new QueryRunner();

			return runner.query(connection, sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();

			throw new RuntimeException();
		}
	}

	public void addUser(User user, Connection connection) {

		String sql = "insert into users values(null, ?, ?, ?, ?, ?, ?, ?, null)";

		try {
			QueryRunner runner = new QueryRunner();

			runner.update(connection, sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(),
					user.getRole(), user.getState(), user.getActivecoede());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateState(int id, int state) {

		String sql = "update users set state=? where id=?";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());
			runner.update(sql, state, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public User findUserByActivecode(String activecode) {

		String sql = "select * from users where activecode=?";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), activecode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteUser(int id) {
		String sql = "delete from users where id=?";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	public User findUserByNameAndPassword(String username, String password) {

		String sql = "select * from users where username = ? and password = ?";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

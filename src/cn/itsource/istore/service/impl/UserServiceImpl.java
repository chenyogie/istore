package cn.itsource.istore.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.dbutils.DbUtils;

import cn.itsource.istore.dao.UserDAO;
import cn.itsource.istore.entity.User;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;
import cn.itsource.istore.util.JDBCUtils;

/**
 * 用户服务接口实现类
 */
public class UserServiceImpl implements UserService {

	UserDAO dao = BasicFactory.getFactory().getInstance(UserDAO.class);
	Connection connection = null;

	/**
	 * 注册用户
	 */
	public void regist(User user) {

		try {
			connection = JDBCUtils.getConnection();
			// 设置数据库不自动提交事物
			connection.setAutoCommit(false);

			/**
			 * 检测用户名是否已经被注册（也就是说， 用户名是否已经存在于数据库）
			 */
			if (dao.findUserByName(user.getUsername(), connection) != null) {
				throw new RuntimeException("用户名已经存在");
			}

			/**
			 * 调用dao中的添加用户的方法把用户添加到数据库中
			 */
			// 设置角色
			user.setRole("user");
			// 设置激活状态 (0 表示未激活)
			user.setState(0);
			// 设置激活码(唯一)
			user.setActivecoede(UUID.randomUUID().toString());

			dao.addUser(user, connection);

			/**
			 * 发送一封激活邮件(SMTP协议, POP3协议)
			 */
			Properties prop = new Properties();
			// 设置邮箱的传输协议： smtp协议
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "smtp.126.com");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");

			// 设置一次会话
			Session session = Session.getInstance(prop);

			// 准备消息内容
			Message msg = new MimeMessage(session);

			// 设置邮件发出的地址
			msg.setFrom(new InternetAddress("cyjxhu@126.com"));
			// 设置收件人， 可以是一个， 可以是多个， 可以是接收者， 可以是抄送者， 可以是密送者，
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			// 设置邮件的主题
			msg.setSubject(user.getUsername() + " 来自istore的激活邮件");
			// 设置邮件发送时间
			msg.setSentDate(new Date());
			// 设置邮件的正文内容
			msg.setText(user.getUsername() + " 请点击该链接激活账户， 如果不能点击， 请将地址复制到浏览器地址栏访问。"
					+ "http://localhost:8080/istore/ActiveServlet?activecode=" + user.getActivecoede());

			// 传输邮件
			Transport transport = session.getTransport();
			transport.connect("cyjxhu@126.com", "aaa123456");
			transport.sendMessage(msg, msg.getAllRecipients());

			System.out.println("邮件发送成功");

			/**
			 * 提交事务， 并且关闭数据库连接
			 */
			DbUtils.commitAndCloseQuietly(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void activeUser(String activecode) {

		/**
		 * 根据激活码来查找数据库中的用户
		 */
		User user = dao.findUserByActivecode(activecode);

		/**
		 * 判断是否存在该用户,<br>
		 * 如果不不能再， 则抛出异常<br>
		 * 如果存在， 则看是否已经激活过： <br>
		 * 如果没有被激活过， 判断激活码是否已经过期，<br>
		 * 如果已经过期, 则提示激活码已经过期， 并且删除数据库中的该用户
		 */
		if (user == null) {
			throw new RuntimeException("激活码有误， 请重新激活用户");
		}

		// 判断该用户是否已经被激活
		if (user.getState() != 0) {
			throw new RuntimeException("该用户已经被激活， 请直接登录");
		}

		// 判断激活码没有被激活， 但是已经过期 (邮件发送两个小时后过期)
		if ((System.currentTimeMillis() - user.getUpdatetime().getTime()) > 1000 * 60 * 60 * 2) {
			dao.deleteUser(user.getId());
			throw new RuntimeException("激活码已经过期， 请重新注册并且在2h内激活");
		}

		/**
		 * 调用dao中的方法来激活用户， 也就是id或者激活码来改变用户的状态码
		 */
		dao.updateState(user.getId(), 1);

	}

	public User findUserByNameAndPassword(String username, String password) {
		return dao.findUserByNameAndPassword(username, password);
	}

}

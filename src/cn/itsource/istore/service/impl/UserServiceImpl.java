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
 * �û�����ӿ�ʵ����
 */
public class UserServiceImpl implements UserService {

	UserDAO dao = BasicFactory.getFactory().getInstance(UserDAO.class);
	Connection connection = null;

	/**
	 * ע���û�
	 */
	public void regist(User user) {

		try {
			connection = JDBCUtils.getConnection();
			// �������ݿⲻ�Զ��ύ����
			connection.setAutoCommit(false);

			/**
			 * ����û����Ƿ��Ѿ���ע�ᣨҲ����˵�� �û����Ƿ��Ѿ����������ݿ⣩
			 */
			if (dao.findUserByName(user.getUsername(), connection) != null) {
				throw new RuntimeException("�û����Ѿ�����");
			}

			/**
			 * ����dao�е�����û��ķ������û���ӵ����ݿ���
			 */
			// ���ý�ɫ
			user.setRole("user");
			// ���ü���״̬ (0 ��ʾδ����)
			user.setState(0);
			// ���ü�����(Ψһ)
			user.setActivecoede(UUID.randomUUID().toString());

			dao.addUser(user, connection);

			/**
			 * ����һ�⼤���ʼ�(SMTPЭ��, POP3Э��)
			 */
			Properties prop = new Properties();
			// ��������Ĵ���Э�飺 smtpЭ��
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "smtp.126.com");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");

			// ����һ�λỰ
			Session session = Session.getInstance(prop);

			// ׼����Ϣ����
			Message msg = new MimeMessage(session);

			// �����ʼ������ĵ�ַ
			msg.setFrom(new InternetAddress("cyjxhu@126.com"));
			// �����ռ��ˣ� ������һ���� �����Ƕ���� �����ǽ����ߣ� �����ǳ����ߣ� �����������ߣ�
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			// �����ʼ�������
			msg.setSubject(user.getUsername() + " ����istore�ļ����ʼ�");
			// �����ʼ�����ʱ��
			msg.setSentDate(new Date());
			// �����ʼ�����������
			msg.setText(user.getUsername() + " ���������Ӽ����˻��� ������ܵ���� �뽫��ַ���Ƶ��������ַ�����ʡ�"
					+ "http://localhost:8080/istore/ActiveServlet?activecode=" + user.getActivecoede());

			// �����ʼ�
			Transport transport = session.getTransport();
			transport.connect("cyjxhu@126.com", "aaa123456");
			transport.sendMessage(msg, msg.getAllRecipients());

			System.out.println("�ʼ����ͳɹ�");

			/**
			 * �ύ���� ���ҹر����ݿ�����
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
		 * ���ݼ��������������ݿ��е��û�
		 */
		User user = dao.findUserByActivecode(activecode);

		/**
		 * �ж��Ƿ���ڸ��û�,<br>
		 * ����������٣� ���׳��쳣<br>
		 * ������ڣ� ���Ƿ��Ѿ�������� <br>
		 * ���û�б�������� �жϼ������Ƿ��Ѿ����ڣ�<br>
		 * ����Ѿ�����, ����ʾ�������Ѿ����ڣ� ����ɾ�����ݿ��еĸ��û�
		 */
		if (user == null) {
			throw new RuntimeException("���������� �����¼����û�");
		}

		// �жϸ��û��Ƿ��Ѿ�������
		if (user.getState() != 0) {
			throw new RuntimeException("���û��Ѿ������ ��ֱ�ӵ�¼");
		}

		// �жϼ�����û�б���� �����Ѿ����� (�ʼ���������Сʱ�����)
		if ((System.currentTimeMillis() - user.getUpdatetime().getTime()) > 1000 * 60 * 60 * 2) {
			dao.deleteUser(user.getId());
			throw new RuntimeException("�������Ѿ����ڣ� ������ע�Ტ����2h�ڼ���");
		}

		/**
		 * ����dao�еķ����������û��� Ҳ����id���߼��������ı��û���״̬��
		 */
		dao.updateState(user.getId(), 1);

	}

	public User findUserByNameAndPassword(String username, String password) {
		return dao.findUserByNameAndPassword(username, password);
	}

}

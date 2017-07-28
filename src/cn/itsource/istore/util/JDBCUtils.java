package cn.itsource.istore.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/***
 * c3p0��ȡ����Դ������
 */
public class JDBCUtils {

	private JDBCUtils() {

	}

	/**
	 * ��ȡ����Դ����
	 */
	private static DataSource source = new ComboPooledDataSource();

	/**
	 * �����ô˷�����ȡ����Դ
	 * 
	 * @return
	 */
	public static DataSource getSource() {
		return source;
	}

	/**
	 * ������Դ�л�ȡ�������Ӷ���
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

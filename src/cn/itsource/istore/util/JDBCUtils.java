package cn.itsource.istore.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/***
 * c3p0获取数据源工具类
 */
public class JDBCUtils {

	private JDBCUtils() {

	}

	/**
	 * 获取数据源对象
	 */
	private static DataSource source = new ComboPooledDataSource();

	/**
	 * 外界调用此方法获取数据源
	 * 
	 * @return
	 */
	public static DataSource getSource() {
		return source;
	}

	/**
	 * 在数据源中获取单个连接对象
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

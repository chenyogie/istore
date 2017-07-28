package cn.itsource.istore.test;

import org.junit.Test;

import cn.itsource.istore.util.JDBCUtils;

/**
 * 测试c3p0连接池获取连接对象
 */
public class TestJDBCUtils {

	@Test
	public void testGetConnection() {

		System.out.println(JDBCUtils.getConnection());

	}

}

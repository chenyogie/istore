package cn.itsource.istore.test;

import org.junit.Test;

import cn.itsource.istore.util.JDBCUtils;

/**
 * ����c3p0���ӳػ�ȡ���Ӷ���
 */
public class TestJDBCUtils {

	@Test
	public void testGetConnection() {

		System.out.println(JDBCUtils.getConnection());

	}

}

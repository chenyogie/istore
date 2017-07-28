package cn.itsource.istore.test;

import org.junit.Test;

import cn.itsource.istore.dao.UserDAO;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;

/**
 * ����ͨ�ù����ഴ������
 */
public class TestBasicFactory {

	@Test
	public void testFactory(){
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		System.out.println(service);
		
		UserDAO dao = BasicFactory.getFactory().getInstance(UserDAO.class);
		System.out.println(dao);
	}
	
}

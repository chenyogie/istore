package cn.itsource.istore.test;

import org.junit.Test;

import cn.itsource.istore.dao.UserDAO;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;

/**
 * 测试通用工厂类创建对象
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

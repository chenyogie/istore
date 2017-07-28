package cn.itsource.istore.lisenter;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.itsource.istore.entity.Product;

/**
 * ʵ���˶�Session�ļ������ܵļ�����
 */
public class MyListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent cevent) {
		cevent.getSession().setAttribute("cartmap", new LinkedHashMap<Product, Integer>());
	}

	public void sessionDestroyed(HttpSessionEvent devent) {

	}

}

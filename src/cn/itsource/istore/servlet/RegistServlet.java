package cn.itsource.istore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itsource.istore.entity.User;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;
import cn.itsource.istore.util.MD5Util;

/**
 * ע���Servlet
 */
@SuppressWarnings("serial")
public class RegistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * У����֤��
		 */
		// ǰ�˵���֤��
		String val = request.getParameter("validate");
		// session�е���֤��
		String val2 = (String) request.getSession().getAttribute("validate");

		System.out.println(val + "   " + val2);

		if (val == null || val2 == null || !val.equals(val2)) {
			request.setAttribute("msg", "<font color='red'>��֤�����</font>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);

			return;
		}

		try {
			/**
			 * ��ȡ��Service����
			 */
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);

			/**
			 * ��װ���ݣ� У������
			 */
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			user.setPassword(MD5Util.secret(user.getPassword()));

			/**
			 * ע���û�
			 */
			service.regist(user);

			/**
			 * �ص���ҳ
			 */
			response.getWriter().write("ע��ɹ��� 3s��ص���ҳ...");
			response.setHeader("refresh", "3;url=index.jsp");

			System.out.println(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

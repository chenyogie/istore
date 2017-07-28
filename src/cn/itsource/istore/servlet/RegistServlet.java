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
 * 注册的Servlet
 */
@SuppressWarnings("serial")
public class RegistServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 校验验证码
		 */
		// 前端的验证码
		String val = request.getParameter("validate");
		// session中的验证码
		String val2 = (String) request.getSession().getAttribute("validate");

		System.out.println(val + "   " + val2);

		if (val == null || val2 == null || !val.equals(val2)) {
			request.setAttribute("msg", "<font color='red'>验证码错误</font>");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);

			return;
		}

		try {
			/**
			 * 获取到Service对象
			 */
			UserService service = BasicFactory.getFactory().getInstance(UserService.class);

			/**
			 * 封装数据， 校验数据
			 */
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			user.setPassword(MD5Util.secret(user.getPassword()));

			/**
			 * 注册用户
			 */
			service.regist(user);

			/**
			 * 回到主页
			 */
			response.getWriter().write("注册成功， 3s后回到主页...");
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

package cn.itsource.istore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;

/**
 * 激活用户的servlet
 */
@SuppressWarnings("serial")
public class ActiveServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 获取激活码
		 */
		String activecode = request.getParameter("activecode");

		/**
		 * 调用service中激活用户的方法, 根据激活码来激活用户
		 */
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		service.activeUser(activecode);

		/**
		 * 提示激活成功， 返回到主页面或者登录页面
		 */
		response.getWriter().write("恭喜您激活成功， 3s后回到主页面...");
		response.setHeader("refresh", "3;url=/istore/index.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

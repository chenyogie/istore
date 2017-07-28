package cn.itsource.istore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全推出的Servlet
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 必须是用户在登录的状态才能安全推出
		 */
		if (request.getSession(false) != null) {
			/**
			 * 使session无效
			 */
			request.getSession().invalidate();

			/**
			 * 删除浏览器中保存的cookie
			 */
			Cookie cookie = new Cookie("autologin", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		/**
		 * 重定向到主页面
		 */
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

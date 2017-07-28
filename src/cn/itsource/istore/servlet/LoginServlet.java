package cn.itsource.istore.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.entity.User;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;
import cn.itsource.istore.util.MD5Util;

/**
 * 登录的Servlet
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 获取用户名和密码
		 */
		String username = request.getParameter("username");
		String password = MD5Util.secret(request.getParameter("password"));

		/**
		 * 调用service中的方法来查找该用户
		 */

		UserService service = BasicFactory.getFactory().getInstance(UserService.class);

		User user = service.findUserByNameAndPassword(username, password);

		/**
		 * 判断是否查找到该用户
		 */
		if (user == null) {
			request.setAttribute("msg", "<font color='red'>用户名或密码有误， 请检查后登录.</font>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

			return;
		}

		/**
		 * 查看该用户是否是激活的用户， 只有被激活之后才允许登录
		 */
		if (user.getState() == 0) {
			request.setAttribute("msg", "<font color='red'>该用户未被激活， 请激活后登录</font>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

			return;
		}

		/**
		 * 登录后的用户重定向到主页
		 */

		// 把用户绑定到session中
		request.getSession().setAttribute("user", user);

		// 记住用户名
		if ("true".equals(request.getParameter("remembername"))) {
			Cookie cookie = new Cookie("remembername", URLEncoder.encode(user.getUsername(), "utf-8"));
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("remembername", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		// 30天内自动登录
		if("true".equals(request.getParameter("autologin"))){
			Cookie cookie = new Cookie("autologin", URLEncoder.encode(user.getUsername() + ":" + user.getPassword(), "utf-8"));
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(cookie);
		}
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

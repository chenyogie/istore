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
 * ��¼��Servlet
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * ��ȡ�û���������
		 */
		String username = request.getParameter("username");
		String password = MD5Util.secret(request.getParameter("password"));

		/**
		 * ����service�еķ��������Ҹ��û�
		 */

		UserService service = BasicFactory.getFactory().getInstance(UserService.class);

		User user = service.findUserByNameAndPassword(username, password);

		/**
		 * �ж��Ƿ���ҵ����û�
		 */
		if (user == null) {
			request.setAttribute("msg", "<font color='red'>�û������������� ������¼.</font>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

			return;
		}

		/**
		 * �鿴���û��Ƿ��Ǽ�����û��� ֻ�б�����֮��������¼
		 */
		if (user.getState() == 0) {
			request.setAttribute("msg", "<font color='red'>���û�δ����� �뼤����¼</font>");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

			return;
		}

		/**
		 * ��¼����û��ض�����ҳ
		 */

		// ���û��󶨵�session��
		request.getSession().setAttribute("user", user);

		// ��ס�û���
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

		// 30�����Զ���¼
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

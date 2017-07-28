package cn.itsource.istore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��ȫ�Ƴ���Servlet
 */
@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * �������û��ڵ�¼��״̬���ܰ�ȫ�Ƴ�
		 */
		if (request.getSession(false) != null) {
			/**
			 * ʹsession��Ч
			 */
			request.getSession().invalidate();

			/**
			 * ɾ��������б����cookie
			 */
			Cookie cookie = new Cookie("autologin", "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		/**
		 * �ض�����ҳ��
		 */
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

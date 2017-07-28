package cn.itsource.istore.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.entity.User;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;

/**
 * �Զ���¼�Ĺ�����
 */
public class AutoLoginFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/**
		 * û�е�¼�����û������Զ���¼
		 */
		if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
			/**
			 * ��������б�����cookie���û����ܹ��Զ���¼
			 */
			Cookie[] cookies = request.getCookies();

			Cookie autologin = null;

			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("autologin".equals(c.getName())) {
						autologin = c;

						break;
					}
				}
			}

			// ����Ƿ��ȡ����autologin�����cookie
			if (autologin != null) {
				/**
				 * �ж�Cookie�д洢���ʺź����붼��ȷ���ܽ��е�¼
				 */
				String str = URLDecoder.decode(autologin.getValue(), "utf-8");

				String username = str.split(":")[0];
				String password = str.split(":")[1];

				UserService service = BasicFactory.getFactory().getInstance(UserService.class);
				User user = service.findUserByNameAndPassword(username, password);

				if (user != null) {
					request.getSession().setAttribute("user", user);
				}
			}
		}

		/**
		 * �����ܷ��¼�� ��Ҫ����
		 */
		fChain.doFilter(request, response);
	}

	public void destroy() {

	}

}

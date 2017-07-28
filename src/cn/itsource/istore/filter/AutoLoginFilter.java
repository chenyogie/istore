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
 * 自动登录的过滤器
 */
public class AutoLoginFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/**
		 * 没有登录过的用户可以自动登录
		 */
		if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
			/**
			 * 在浏览器中保存了cookie的用户才能够自动登录
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

			// 检查是否获取到“autologin”这个cookie
			if (autologin != null) {
				/**
				 * 判断Cookie中存储的帐号和密码都正确才能进行登录
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
		 * 无论能否登录， 都要放行
		 */
		fChain.doFilter(request, response);
	}

	public void destroy() {

	}

}

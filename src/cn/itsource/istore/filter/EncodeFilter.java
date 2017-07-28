package cn.itsource.istore.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 全站乱码过滤器<br>
 * 所有的请求在进入web工程的时候， 首先一定要经过过滤器
 */
public class EncodeFilter implements Filter {

	private FilterConfig config = null;
	private ServletContext context = null;
	private String encode = null;

	/**
	 * 初始化过滤器
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.context = this.config.getServletContext();
		this.encode = context.getInitParameter("encode");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain)
			throws IOException, ServletException {

		/**
		 * 响应乱码解决方案
		 */
		resp.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset=" + encode);

		/**
		 * 请求乱码解决方法<br>
		 * 
		 * 使用装饰模式对请求对象进行包装处理, 从而解决请求乱码问题
		 */
		// 首先一定要放行
		fChain.doFilter(new MyServletRequest((HttpServletRequest) req), resp);
	}

	/**
	 * 内部类解决请求乱码问题
	 */
	class MyServletRequest extends HttpServletRequestWrapper {

		private HttpServletRequest request = null;

		// 这是一个标记有没有解决过乱码的标记, 默认值是没有被解决
		private boolean isNotEncode = true;

		public MyServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public Map<String, String[]> getParameterMap() {

			try {

				/**
				 * 我们这里重点处理GET请求 和 POST请求
				 */
				if ("POST".equalsIgnoreCase(request.getMethod())) {

					request.setCharacterEncoding(encode);

					/**
					 * post请求在实体内容中
					 */
					return request.getParameterMap();
				} else if ("GET".equalsIgnoreCase(request.getMethod())) {
					Map<String, String[]> map = request.getParameterMap();

					if (isNotEncode) {
						for (Map.Entry<String, String[]> entry : map.entrySet()) {
							String[] values = entry.getValue();
							for (int i = 0; i < values.length; i++) {
								values[i] = new String(values[i].getBytes("iso-8859-1"), encode);
							}
						}

						isNotEncode = false;
					}

					return map;
				} else {
					return request.getParameterMap();
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();

				throw new RuntimeException();
			}

		}

		@Override
		public String getParameter(String name) {
			return getParameterValues(name) == null ? null : getParameterValues(name)[0];
		}

		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
	}

	public void destroy() {

	}

}

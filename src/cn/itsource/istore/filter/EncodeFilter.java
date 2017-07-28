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
 * ȫվ���������<br>
 * ���е������ڽ���web���̵�ʱ�� ����һ��Ҫ����������
 */
public class EncodeFilter implements Filter {

	private FilterConfig config = null;
	private ServletContext context = null;
	private String encode = null;

	/**
	 * ��ʼ��������
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.context = this.config.getServletContext();
		this.encode = context.getInitParameter("encode");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain)
			throws IOException, ServletException {

		/**
		 * ��Ӧ����������
		 */
		resp.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset=" + encode);

		/**
		 * ��������������<br>
		 * 
		 * ʹ��װ��ģʽ�����������а�װ����, �Ӷ����������������
		 */
		// ����һ��Ҫ����
		fChain.doFilter(new MyServletRequest((HttpServletRequest) req), resp);
	}

	/**
	 * �ڲ�����������������
	 */
	class MyServletRequest extends HttpServletRequestWrapper {

		private HttpServletRequest request = null;

		// ����һ�������û�н��������ı��, Ĭ��ֵ��û�б����
		private boolean isNotEncode = true;

		public MyServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public Map<String, String[]> getParameterMap() {

			try {

				/**
				 * ���������ص㴦��GET���� �� POST����
				 */
				if ("POST".equalsIgnoreCase(request.getMethod())) {

					request.setCharacterEncoding(encode);

					/**
					 * post������ʵ��������
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

package cn.itsource.istore.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.entity.Product;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.ProductService;

/**
 * ��Ʒ���빺�ﳵ��Servlet
 */
@SuppressWarnings("serial")
public class AddToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * ����id��ȡ����Ҫ�������Ʒ
		 */
		String id = request.getParameter("id");

		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product product = service.findProductById(id);

		/**
		 * �ж���Ʒ�Ƿ���ڣ� ����Ʒ���ڵ��������ӵ����ﳵ<br>
		 * �����Ʒ֮ǰû�д����ڹ��ﳵ�� ����빺�ﳵ�� ���ҹ�����������Ϊ 1<br>
		 * r�����Ʒ֮ǰ�����ڹ��ﳵ�� ��ֻ��ѹ������� +1
		 */
		if (product == null) {
			throw new RuntimeException();
		} else {
			@SuppressWarnings("unchecked")
			Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.put(product, cartmap.containsKey(product) ? cartmap.get(product) + 1 : 1);
		}

		/**
		 * �ض��򵽹��ﳵ
		 */
		response.sendRedirect(this.getServletContext().getContextPath() + "/cart.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

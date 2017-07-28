package cn.itsource.istore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.entity.Product;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.ProductService;

/**
 * ��ʾ��Ʒ��ϸ��Ϣ��Servlet
 */
@SuppressWarnings("serial")
public class ProductInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * ��ȡ�����е���Ʒ��id
		 */
		String id = request.getParameter("id");

		/**
		 * ����service�еķ�������id������Ʒ
		 */
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product product = service.findProductById(id);

		/**
		 * �ж϶����Ƿ��ҵ��� ����ҵ��� ��ת����ǰ����ʾ��ϸ��Ϣ
		 */
		if (product == null) {
			throw new RuntimeException();
		}

		request.setAttribute("product", product);
		request.getRequestDispatcher("/productInfo.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

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
 * 从购物车中删除商品的Servlet
 */
@SuppressWarnings("serial")
public class DeleteFormCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product product = service.findProductById(id);

		if (product == null) {
			throw new RuntimeException();
		}

		@SuppressWarnings("unchecked")
		Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
		cartmap.remove(product);

		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

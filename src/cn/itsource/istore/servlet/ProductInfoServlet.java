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
 * 显示商品详细信息的Servlet
 */
@SuppressWarnings("serial")
public class ProductInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 获取请求中的商品的id
		 */
		String id = request.getParameter("id");

		/**
		 * 调用service中的方法根据id查找商品
		 */
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product product = service.findProductById(id);

		/**
		 * 判断对象是否找到， 如果找到， 则转发给前端显示详细信息
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

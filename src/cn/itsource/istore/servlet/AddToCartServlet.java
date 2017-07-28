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
 * 商品加入购物车的Servlet
 */
@SuppressWarnings("serial")
public class AddToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 根据id获取到将要购买的商品
		 */
		String id = request.getParameter("id");

		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		Product product = service.findProductById(id);

		/**
		 * 判断商品是否存在， 在商品存在的情况下添加到购物车<br>
		 * 如果商品之前没有存在于购物车， 则加入购物车， 并且购买数量设置为 1<br>
		 * r如果商品之前存在于购物车， 则只需把购买数量 +1
		 */
		if (product == null) {
			throw new RuntimeException();
		} else {
			@SuppressWarnings("unchecked")
			Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
			cartmap.put(product, cartmap.containsKey(product) ? cartmap.get(product) + 1 : 1);
		}

		/**
		 * 重定向到购物车
		 */
		response.sendRedirect(this.getServletContext().getContextPath() + "/cart.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

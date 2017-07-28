package cn.itsource.istore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.entity.Product;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.ProductService;

/**
 * ��ʾ��Ʒ�б��Servlet
 */
@SuppressWarnings("serial")
public class ListProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);

		List<Product> list = service.findAllProduct();

		request.setAttribute("list", list);
		request.getRequestDispatcher("/listProduct.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

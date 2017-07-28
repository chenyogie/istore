package cn.itsource.istore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.UserService;

/**
 * �����û���servlet
 */
@SuppressWarnings("serial")
public class ActiveServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * ��ȡ������
		 */
		String activecode = request.getParameter("activecode");

		/**
		 * ����service�м����û��ķ���, ���ݼ������������û�
		 */
		UserService service = BasicFactory.getFactory().getInstance(UserService.class);
		service.activeUser(activecode);

		/**
		 * ��ʾ����ɹ��� ���ص���ҳ����ߵ�¼ҳ��
		 */
		response.getWriter().write("��ϲ������ɹ��� 3s��ص���ҳ��...");
		response.setHeader("refresh", "3;url=/istore/index.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

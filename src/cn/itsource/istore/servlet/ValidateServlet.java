package cn.itsource.istore.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ������֤���Servlet
 */
@SuppressWarnings("serial")
public class ValidateServlet extends HttpServlet {

	Random random = new Random();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * ������Ӧ��Ϣͷ�� �������������ͼƬ
		 */
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-Cache");
		response.setHeader("pragma", "no-Cache");

		int width = 150;
		int height = 40;

		// ��ͼƬ
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// ��ȡ������
		Graphics2D g = (Graphics2D) img.getGraphics();

		// ����"����", ��������
		g.setColor(Color.PINK);
		g.fillRect(0, 0, width, height);

		// ���߿�
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1);

		// ��������
		int r = random.nextInt(256);
		int gg = random.nextInt(256);
		int b = random.nextInt(256);
		g.setColor(new Color(r, gg, b));
		for (int i = 0; i < 5; i++) {
			g.drawLine(getRandomInt(0, width), getRandomInt(0, height), getRandomInt(0, width),
					getRandomInt(0, height));
		}

		// ������������ַ��뵽ͼƬ��
		String str = "\u5C0F\u8377\u624D\u9732\u5C16\u5C16\u89D2\u65E9\u6709\u873B\u8713\u7ACB\u4E0A\u5934";

		// ��������
		g.setFont(new Font("����", Font.BOLD, 20));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {

			double c = getRandomInt(-45, 45) / 180F * Math.PI;
			g.rotate(c, 5 + width / 4 * i, 20);

			String s = str.charAt(getRandomInt(0, str.length() - 1)) + "";

			sb.append(s);

			g.drawString(s, 5 + width / 4 * i, 20);

			g.rotate(-c, 5 + width / 4 * i, 20);

		}
		
		System.out.println(sb.toString());

		// ������Session��
		request.getSession().setAttribute("validate", sb.toString());

		g.dispose();
		ImageIO.write(img, "jpg", response.getOutputStream());

	}

	/**
	 * �����߽������
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int getRandomInt(int begin, int end) {

		return random.nextInt(end - begin) + begin;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

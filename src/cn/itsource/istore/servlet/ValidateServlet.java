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
 * 产生验证码的Servlet
 */
@SuppressWarnings("serial")
public class ValidateServlet extends HttpServlet {

	Random random = new Random();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 设置响应消息头， 不让浏览器缓存图片
		 */
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-Cache");
		response.setHeader("pragma", "no-Cache");

		int width = 150;
		int height = 40;

		// 画图片
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取到画笔
		Graphics2D g = (Graphics2D) img.getGraphics();

		// 设置"画笔", 画出矩形
		g.setColor(Color.PINK);
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1);

		// 画干扰线
		int r = random.nextInt(256);
		int gg = random.nextInt(256);
		int b = random.nextInt(256);
		g.setColor(new Color(r, gg, b));
		for (int i = 0; i < 5; i++) {
			g.drawLine(getRandomInt(0, width), getRandomInt(0, height), getRandomInt(0, width),
					getRandomInt(0, height));
		}

		// 产生随机的文字放入到图片上
		String str = "\u5C0F\u8377\u624D\u9732\u5C16\u5C16\u89D2\u65E9\u6709\u873B\u8713\u7ACB\u4E0A\u5934";

		// 设置字体
		g.setFont(new Font("楷体", Font.BOLD, 20));
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

		// 保存在Session中
		request.getSession().setAttribute("validate", sb.toString());

		g.dispose();
		ImageIO.write(img, "jpg", response.getOutputStream());

	}

	/**
	 * 产生边界随机数
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

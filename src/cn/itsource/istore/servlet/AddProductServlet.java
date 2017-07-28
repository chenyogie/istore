package cn.itsource.istore.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itsource.istore.entity.Product;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.ProductService;
import cn.itsource.istore.util.PictureUtil;

/**
 * 添加商品的Servlet
 */
@SuppressWarnings("serial")
public class AddProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取到service对象
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);

		// 我们需要对上传文件进行编码处理， 所以需要全站的编码参数
		String encode = this.getServletContext().getInitParameter("encode");

		// request.getParameterMap();
		Map<String, String> parameterMap = new HashMap<String, String>();

		/**
		 * 上传文件
		 */
		// 创建磁盘文件条目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传缓存区的最大值 默认值是10K， 这里设置的是100K
		factory.setSizeThreshold(1024 * 100);
		// 设置文件上传的位置
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));

		// 创建文件上传对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的编码
		upload.setHeaderEncoding(encode);
		// 设置单个上传文件的大小, 这里设置的是10M
		upload.setFileSizeMax(1024 * 1024 * 10);
		// 设置总上传文件的大小， 这里设置的是100M
		upload.setSizeMax(1024 * 1024 * 100);

		// 判断是否是正确的表单提交
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("请使用正确的表单上传文件");
		}

		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {

				// 普通的请求字段
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString(encode);

					parameterMap.put(name, value);
				} else {
					// 文件上传的字段

					String filename = item.getName();
					String uuid = UUID.randomUUID().toString();
					int hashcode = uuid.hashCode();
					String hex = Integer.toHexString(hashcode);

					String uploadpath = this.getServletContext().getRealPath("WEB-INF/upload");
					String imgurl = "/WEB-INF/upload";

					for (char c : hex.toCharArray()) {
						uploadpath += "/" + c;
						imgurl += "/" + c;
					}

					imgurl += "/" + filename;

					parameterMap.put("imgurl", imgurl);

					File uploadDir = new File(uploadpath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(uploadpath, filename));

					byte[] bs = new byte[64];
					int i = 1;
					while ((i = in.read(bs)) != -1) {
						out.write(bs, 0, i);
					}

					in.close();
					out.flush();
					out.close();

					item.delete();

					System.out.println(this.getServletContext().getRealPath(imgurl));

					// 生成图片的缩略图
					PictureUtil pu = new PictureUtil(this.getServletContext().getRealPath(imgurl));
					pu.resizeByWidth(150);

				}

			}

			/**
			 * 调用service中的方法往数据库中存储产品的信息
			 */
			Product product = new Product();
			BeanUtils.populate(product, parameterMap);

			service.addProduct(product);

			/**
			 * 添加商品成功，3s后回到主页
			 */
			response.getWriter().write("添加商品成功，3s后回到主页...");
			response.setHeader("refresh", "3;url=/istore/index.jsp");

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

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
 * �����Ʒ��Servlet
 */
@SuppressWarnings("serial")
public class AddProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ��service����
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);

		// ������Ҫ���ϴ��ļ����б��봦�� ������Ҫȫվ�ı������
		String encode = this.getServletContext().getInitParameter("encode");

		// request.getParameterMap();
		Map<String, String> parameterMap = new HashMap<String, String>();

		/**
		 * �ϴ��ļ�
		 */
		// ���������ļ���Ŀ��������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ļ��ϴ������������ֵ Ĭ��ֵ��10K�� �������õ���100K
		factory.setSizeThreshold(1024 * 100);
		// �����ļ��ϴ���λ��
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));

		// �����ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// �����ϴ��ļ��ı���
		upload.setHeaderEncoding(encode);
		// ���õ����ϴ��ļ��Ĵ�С, �������õ���10M
		upload.setFileSizeMax(1024 * 1024 * 10);
		// �������ϴ��ļ��Ĵ�С�� �������õ���100M
		upload.setSizeMax(1024 * 1024 * 100);

		// �ж��Ƿ�����ȷ�ı��ύ
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("��ʹ����ȷ�ı��ϴ��ļ�");
		}

		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);

			for (FileItem item : list) {

				// ��ͨ�������ֶ�
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString(encode);

					parameterMap.put(name, value);
				} else {
					// �ļ��ϴ����ֶ�

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

					// ����ͼƬ������ͼ
					PictureUtil pu = new PictureUtil(this.getServletContext().getRealPath(imgurl));
					pu.resizeByWidth(150);

				}

			}

			/**
			 * ����service�еķ��������ݿ��д洢��Ʒ����Ϣ
			 */
			Product product = new Product();
			BeanUtils.populate(product, parameterMap);

			service.addProduct(product);

			/**
			 * �����Ʒ�ɹ���3s��ص���ҳ
			 */
			response.getWriter().write("�����Ʒ�ɹ���3s��ص���ҳ...");
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

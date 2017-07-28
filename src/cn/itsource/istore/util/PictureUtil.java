package cn.itsource.istore.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * ��������ͼ�Ĺ�����
 */
public class PictureUtil {
	private String srcFile;
	private String destFile;
	private int width;
	private int height;
	private Image img;

	/**
	 * ���췽��
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public PictureUtil(String fileName) throws IOException {
		File _file = new File(fileName);
		this.srcFile = fileName;
		// �������һ��.
		int index = this.srcFile.lastIndexOf(".");
		String ext = this.srcFile.substring(index);
		this.destFile = this.srcFile.substring(0, index) + "_2" + ext;
		img = javax.imageio.ImageIO.read(_file);
		width = img.getWidth(null);
		height = img.getHeight(null);
	}

	/**
	 * ָ����С����ͼƬ
	 * 
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public void resize(int w, int h) throws IOException {
		BufferedImage _image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		_image.getGraphics().drawImage(img, 0, 0, w, h, null);
		FileOutputStream out = new FileOutputStream(destFile);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(_image);
		out.close();
	}

	/**
	 * ָ����������ͼƬ
	 * 
	 * @param t
	 * @throws IOException
	 */
	public void resize(double t) throws IOException {
		int w = (int) (width * t);
		int h = (int) (height * t);
		resize(w, h);
	}

	/**
	 * �Կ��Ϊ��׼���ȱ�������ͼƬ
	 * 
	 * @param w
	 * @throws IOException
	 */
	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

	/**
	 * �Ը߶�Ϊ��׼���ȱ�������ͼƬ
	 * 
	 * @param h
	 * @throws IOException
	 */
	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

}

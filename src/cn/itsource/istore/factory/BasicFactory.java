package cn.itsource.istore.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ͨ������������������
 */
public class BasicFactory {

	/**
	 * ��ʽ������������
	 */
	private static BasicFactory factory = new BasicFactory();

	private BasicFactory() {

	}

	/**
	 * ����Properties�������ڴ洢��config.properties�ļ��ж�ȡ������
	 */
	private static Properties properties = null;

	static {
		try {
			/**
			 * ����Properties����
			 */
			properties = new Properties();

			/**
			 * ��ȡconfig.properties�ļ�����ת��Ϊ������
			 */
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

			properties.load(is);

		} catch (IOException e) {
			e.printStackTrace();

			throw new RuntimeException("δ�ҵ���Դ�ļ�!!!");
		}
	}

	/**
	 * �����ô˷�����ȡ��BasicFactory�Ķ���
	 * 
	 * @return
	 */
	public static BasicFactory getFactory() {
		return factory;
	}

	/**
	 * ���ݴ��������ʱ���� ��������ʵ������
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> clazz) {

		try {
			/**
			 * ��ȡ�ӿڵ�����
			 */
			String interName = clazz.getSimpleName();

			/**
			 * ��ȡ�ӿڶ�Ӧ��ʵ���������
			 */
			String implName = properties.getProperty(interName);

			return (T) Class.forName(implName).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}

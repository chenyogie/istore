package cn.itsource.istore.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过工厂方法创建对象
 */
public class BasicFactory {

	/**
	 * 恶汉式创建工厂对象
	 */
	private static BasicFactory factory = new BasicFactory();

	private BasicFactory() {

	}

	/**
	 * 创建Properties对象用于存储从config.properties文件中读取的数据
	 */
	private static Properties properties = null;

	static {
		try {
			/**
			 * 创建Properties对象
			 */
			properties = new Properties();

			/**
			 * 读取config.properties文件并且转化为流对象
			 */
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

			properties.load(is);

		} catch (IOException e) {
			e.printStackTrace();

			throw new RuntimeException("未找到资源文件!!!");
		}
	}

	/**
	 * 外界调用此方法获取到BasicFactory的对象
	 * 
	 * @return
	 */
	public static BasicFactory getFactory() {
		return factory;
	}

	/**
	 * 根据传入的运行时对象， 来创建出实例对象
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> clazz) {

		try {
			/**
			 * 获取接口的名字
			 */
			String interName = clazz.getSimpleName();

			/**
			 * 获取接口对应的实现类的名字
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

package cn.itsource.istore.service;

import java.util.List;

import cn.itsource.istore.entity.Product;

/**
 * ��Ʒ����ӿ�
 */
public interface ProductService {

	/**
	 * �����Ʒ
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * ��ѯ������Ʒ�ķ���, ������Ʒ���϶���
	 * 
	 * @return
	 */
	public List<Product> findAllProduct();

	/**
	 * ����id������Ʒ
	 * @param id
	 * @return
	 */
	public Product findProductById(String id);

}

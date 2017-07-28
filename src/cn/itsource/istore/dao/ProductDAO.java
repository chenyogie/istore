package cn.itsource.istore.dao;

import java.util.List;

import cn.itsource.istore.entity.Product;

/**
 * ��Ʒ��DAO�ӿ�
 */
public interface ProductDAO {

	/**
	 * �����Ʒ
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * ����������Ʒ�ķ����� ���ҰѰ����ݷ�װ�ɶ��� Ȼ����뵽������
	 * 
	 * @return
	 */
	public List<Product> findAllProduct();

	/**
	 * ����id������Ʒ
	 * 
	 * @param id
	 * @return
	 */
	public Product findProductById(String id);

}

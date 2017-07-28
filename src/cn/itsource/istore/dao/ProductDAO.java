package cn.itsource.istore.dao;

import java.util.List;

import cn.itsource.istore.entity.Product;

/**
 * 产品的DAO接口
 */
public interface ProductDAO {

	/**
	 * 添加商品
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * 查找所有商品的方法， 并且把把数据封装成对象， 然后存入到集合中
	 * 
	 * @return
	 */
	public List<Product> findAllProduct();

	/**
	 * 根据id查找商品
	 * 
	 * @param id
	 * @return
	 */
	public Product findProductById(String id);

}

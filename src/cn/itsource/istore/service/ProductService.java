package cn.itsource.istore.service;

import java.util.List;

import cn.itsource.istore.entity.Product;

/**
 * 产品服务接口
 */
public interface ProductService {

	/**
	 * 添加商品
	 * 
	 * @param product
	 */
	public void addProduct(Product product);

	/**
	 * 查询所有商品的方法, 返回商品集合对象
	 * 
	 * @return
	 */
	public List<Product> findAllProduct();

	/**
	 * 根据id查找商品
	 * @param id
	 * @return
	 */
	public Product findProductById(String id);

}

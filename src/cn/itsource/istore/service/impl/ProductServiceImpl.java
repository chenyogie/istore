package cn.itsource.istore.service.impl;

import java.util.List;
import java.util.UUID;

import cn.itsource.istore.dao.ProductDAO;
import cn.itsource.istore.entity.Product;
import cn.itsource.istore.factory.BasicFactory;
import cn.itsource.istore.service.ProductService;

/**
 * 产品服务接口的实现类
 */
public class ProductServiceImpl implements ProductService {

	ProductDAO dao = BasicFactory.getFactory().getInstance(ProductDAO.class);

	public void addProduct(Product product) {

		String uuid = UUID.randomUUID().toString();
		product.setId(uuid);

		dao.addProduct(product);
	}

	public List<Product> findAllProduct() {
		return dao.findAllProduct();
	}

	public Product findProductById(String id) {
		return dao.findProductById(id);
	}

}

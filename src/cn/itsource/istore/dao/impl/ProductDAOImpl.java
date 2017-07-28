package cn.itsource.istore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itsource.istore.dao.ProductDAO;
import cn.itsource.istore.entity.Product;
import cn.itsource.istore.util.JDBCUtils;

/**
 * 产品DAO实现类
 */
public class ProductDAOImpl implements ProductDAO {

	public void addProduct(Product product) {
		String sql = "insert into products values(?, ?, ?, ?, ?, ?, ?)";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());
			runner.update(sql, product.getId(), product.getName(), product.getCategory(), product.getPrice(),
					product.getProductnumber(), product.getImgurl(), product.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Product> findAllProduct() {

		String sql = "select * from products";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());

			return runner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Product findProductById(String id) {

		String sql = "select * from products where id=?";

		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getSource());

			return runner.query(sql, new BeanHandler<Product>(Product.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

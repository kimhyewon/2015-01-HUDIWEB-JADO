package jado.dao;

import java.util.List;


import jado.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(final Product product) {
		String sql = "insert into Product values(null, ?, ?, ?, ?, ?, ?, null)";
		Object[] args = new Object[] {product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc()};
		jdbcTemplate.update(sql, args);
	}

	public Product selectByPk(final int productId) {
		String sql = "select * from Product where ID=?";
		Object[] args = new Object[] { productId };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<Product> selectAllByCateGoryId(final Product product) {
		String sql = "select * from Product where CATEGORY_ID=?";
		Object[] args = new Object[] { product.getCategoryId() };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

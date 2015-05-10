package jado.dao;

import java.util.List;

import jado.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final Product product) {
		String sql = "insert into PRODUCT values(null, ?, ?, ?, ?, ?, ?, null)";
		Object[] args = new Object[] { product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc() };
		jdbcTemplate.update(sql, args);
	}

	public Product selectByPk(final int productId) {
		String sql = "select * from PRODUCT where ID=?";
		Object[] args = new Object[] { productId };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Product> selectAllByCateGoryId(final int categoryId) {
		String sql = "select * from PRODUCT where CATEGORY_ID=?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), categoryId);
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<Product> selectAllByUrl(String url) {
		String sql = "select * from (select PRODUCT.* from PRODUCT inner join CATEGORY on PRODUCT.CATEGORY_ID = CATEGORY.ID "
				+ "where CATEGORY.SHOP_URL = ?) as PRODUCT_INFO order by INSERT_TIME desc limit 9";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), url);
		} catch (DataAccessException e) {
			return null;
		}
	}

}

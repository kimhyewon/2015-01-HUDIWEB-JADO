package jado.dao;

import jado.model.Product;

import java.util.List;

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
		String sql = "insert into PRODUCT values(null, ?, ?, ?, ?, ?, ?, null)";
		jdbcTemplate.update(sql, product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc() );
	}

	public Product selectByPk(int productId) {
		String sql = "select * from PRODUCT where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Product> selectAllByCateGoryId(final int categoryId) {
		String sql = "select * from PRODUCT where CATEGORY_ID=? order by ID desc";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), categoryId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Product> selectAllByUrl(String url) {
		String sql = "select * from (select PRODUCT.* from PRODUCT inner join CATEGORY on PRODUCT.CATEGORY_ID = CATEGORY.ID "
				+ "where CATEGORY.SHOP_URL = ?) as PRODUCT_INFO order by INSERT_TIME desc limit 9";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), url);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void update(Product product) {
		String sql = "update PRODUCT set NAME=?, PRICE=?, STOCK=?, `DESC`=? where ID=?";
		jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStock(), product.getDesc(), product.getId());
	}

	public void remove(int productId) {
		String sql = "delete from PRODUCT where ID=?";
		jdbcTemplate.update(sql, productId);
	}
	

	public Integer countPaymentByProduct(int productId) {
		String sql = "select count(*) from PAYMENT inner join PRODUCT on PRODUCT.ID = PAYMENT.PRODUCT_ID where PRODUCT.ID=?";
		Object[] args = new Object[] { productId };
		try {
			return jdbcTemplate.queryForObject(sql, args, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public Integer countCommentByProduct(int productId) {
		String sql = "select count(*) from PRODUCT_COMMENT inner join PRODUCT on PRODUCT.ID = PRODUCT_COMMENT.PRODUCT_ID where PRODUCT.ID=?";
		Object[] args = new Object[] { productId };
		try {
			return jdbcTemplate.queryForObject(sql, args, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateImgUrl(Product product) {
		String sql = "update PRODUCT set IMG_URL=? where ID=?";
		jdbcTemplate.update(sql, product.getImgUrl(), product.getId());
		
	}
}

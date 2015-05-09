package jado.dao;

import java.util.List;

import jado.model.Product;
import jado.model.Shop;

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
		Object[] args = new Object[] {product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc()};
		jdbcTemplate.update(sql, args);
	}

	public Product selectByPk(int productId) {
		String sql = "select * from PRODUCT where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), productId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<Product> selectAllByCateGoryId(int categoryId) {
		String sql = "select * from PRODUCT where CATEGORY_ID=?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), categoryId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

//	public Product selectByUrl(final String url) {
//		String sql = "select * from PRODUCT where imgUrl=?";
//		try {
//			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Product>(Product.class), url);
//		} catch (EmptyResultDataAccessException e) {
//			return null;
//		}
//	}
	
	public List<Product> selectAllByUrl(String url) {
		String sql = "select PRODUCT.* from PRODUCT inner join CATEGORY on PRODUCT.CATEGORY_ID = CATEGORY.ID "
				+ "where CATEGORY.SHOP_URL = ?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), url);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public void update(Product product) {
		String sql = "update PRODUCT set NAME=?, PRICE=?, STOCK=?, IMG_URL=?, DESC=? where ID=?";
		Object[] args = new Object[] {product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc(), product.getId()};
		jdbcTemplate.update(sql, args);
	}
	
	public void remove(int productId) {
		String sql = "delete from PRODUCT where ID=?";
		jdbcTemplate.update(sql, productId);
	}
	
//	public void updateImageUrl(FileInfo fileInfo) {
//		String sql = "update PRODUCT set ".concat(fileInfo.getType())+"=? where imgUrl=?";
//		Object[] args = new Object[]{ fileInfo.getLocalLocation(), fileInfo.getUrl()};
//		jdbcTemplate.update(sql, args);
//		
//	}

}

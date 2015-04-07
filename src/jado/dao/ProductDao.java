package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Product;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends JdbcDaoSupport{

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}
	
	public void insert(final Product product) {
		String sql = "insert into Product values(null, ?, ?, ?, ?, ?, ?, null)";
		Object[] args = new Object[] {product.getCategoryId(), product.getName(), product.getPrice(), product.getStock(), product.getImgUrl(), product.getDesc()};
		getJdbcTemplate().update(sql, args);
	}

	public Product selectByPk(final int productId) {
		String sql = "select * from Product where ID=?";
		Object[] args = new Object[] { productId };
		try {
			return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<Product> selectAllByCateGoryId(final Product product) {
		String sql = "select * from Product where CATEGORY_ID=?";
		Object[] args = new Object[] { product.getCategoryId() };
		try {
			return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<Product>(Product.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}

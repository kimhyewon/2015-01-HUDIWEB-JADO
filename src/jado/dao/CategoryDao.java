package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDao { 
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}
	
	public void insert(final Category category) {
		String sql = "insert into CATEGORY values(null, ?, ?)";
		
		Object[] args = new Object[] { category.getName(), category.getShopUrl() };
		jdbcTemplate.update(sql, args);
	}
	public Category selectByPk(final int id) {
		String sql = "select * from CATEGORY where ID=?";
		Object[] args = new Object[] { id };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Category>(Category.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<Category> selectAllByUrl(final String url) {
		String sql = "select * from CATEGORY where SHOP_URL=?";
		Object[] args = new Object[] { url };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Category>(Category.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final int id) {
		String sql = "delete from CATEGORY where ID=?";
		jdbcTemplate.update(sql, id);
	}

	public int countProduct(final int id) {
		String sql = "select count(*) from PRODUCT WHERE CATEGORY_ID=?";
		Object[] args = new Object[] { id };
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}
}

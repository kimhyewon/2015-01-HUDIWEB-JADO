package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;


@Repository
public class CategoryDao extends JdbcDaoSupport{ 
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}
	
	public void insert(final Category category) {
		String sql = "insert into CATEGORY values(null, ?, ?)";
		
		Object[] args = new Object[] { category.getName(), category.getShopUrl() };
		getJdbcTemplate().update(sql, args);
	}
	public Category selectByPk(final int i) {
		String sql = "select * from CATEGORY where ID=?";
		Object[] args = new Object[] { i };
		try {
			return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Category>(Category.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<Category> selectAllByUrl(final String url) {
		String sql = "select * from CATEGORY where SHOP_URL=?";
		Object[] args = new Object[] { url };
		try {
			return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<Category>(Category.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final int id) {
		String sql = "delete from CATEGORY where ID=?";
		getJdbcTemplate().update(sql, id);
	}
}

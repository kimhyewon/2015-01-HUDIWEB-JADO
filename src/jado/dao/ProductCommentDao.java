package jado.dao;

import jado.model.ProductComment;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;


@Repository
public class ProductCommentDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public void insert(final ProductComment productComment){
		String sql = "insert into PRODUCT_COMMENT values(?, ?, null, ?)";
		Object[] args = new Object[] { productComment.getProductId(), productComment.getUserId(), productComment.getContent()};
		getJdbcTemplate().update(sql, args);
	}

	public ProductComment findtByPK(final ProductComment productComment) {
		String sql = "select * from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] { productComment.getProductId(), productComment.getUserId(), productComment.getCommentTime()};
		try {
			return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<ProductComment>(ProductComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public List<ProductComment> findByProduct(final ProductComment productComment) {
		String sql = "select * from PRODUCT_COMMENT where PRODUCT_ID=?";
		Object[] args = new Object[] { productComment.getProductId() };

		try {
			return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<ProductComment>(ProductComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void remove(final ProductComment productComment) {
		String sql = "delete from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] { productComment.getProductId(), productComment.getUserId(), productComment.getCommentTime()};
		getJdbcTemplate().update(sql, args);
	}

}

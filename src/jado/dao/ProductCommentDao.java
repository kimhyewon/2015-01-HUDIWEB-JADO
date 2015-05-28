package jado.dao;

import jado.model.ProductComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductCommentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final ProductComment productComment) {
		String sql = "insert into PRODUCT_COMMENT values(?, ?, null, ?)";
		jdbcTemplate.update(sql, productComment.getProductId(), productComment.getUserId(), productComment.getContent());
	}

	public ProductComment findtByPK(final ProductComment productComment) {
		String sql = "select * from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] { productComment.getProductId(), productComment.getUserId(), productComment.getCommentTime() };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<ProductComment>(ProductComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<ProductComment> findByProduct(final int productId) {
		String sql = "select * from PRODUCT_COMMENT where PRODUCT_ID=? order by COMMENT_TIME";
		Object[] args = new Object[] { productId };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<ProductComment>(ProductComment.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(int productId, String userId, String commentTime) {
		String sql = "delete from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=? and COMMENT_TIME=?";
		jdbcTemplate.update(sql, productId, userId, commentTime);
	}

	public void removeByPk(ProductComment productComment) {
		String sql = "delete from PRODUCT_COMMENT where PRODUCT_ID=? and USER_ID=? and COMMENT_TIME=?";
		Object[] args = new Object[] { productComment.getProductId(), productComment.getUserId(), productComment.getCommentTime() };
		jdbcTemplate.update(sql, args);
	}

	public void removeByProductId(Integer productId) {
		String sql = "delete from PRODUCT_COMMENT where PRODUCT_ID=?";
		Object[] args = new Object[] { productId };
		jdbcTemplate.update(sql, args);
	}

}

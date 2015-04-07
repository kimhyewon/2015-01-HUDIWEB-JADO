package jado.dao;

import jado.model.ArticleComment;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;


@Repository
public class ArticleCommentDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public ArticleComment findById(final String userId) {
		String sql = "select * from ARTICLE_COMMENT where USER_ID=?";
		RowMapper<ArticleComment> rm = new RowMapper<ArticleComment>() {
			@Override
			public ArticleComment mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new ArticleComment(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getDate(5), rs.getString(6));
			}
		};		
		return getJdbcTemplate().queryForObject(sql, rm, userId);
	}
	
	
	public int insert(final ArticleComment ac){
		String sql = "insert into ARTICLE_COMMENT values(?, ?, ?, ?, null, ?)";
		return getJdbcTemplate().update(sql, ac.getShopUrl(), ac.getArticleTitle(), ac.getBoardName(), ac.getUserId(), ac.getContent());
	} 
	
//	// select all
//	public List<ArticleComment> selectAll(ArticleComment articleComment) {
//		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
//		String sql = "select * from ARTICLE_COMMENT where SHOP_URL=? and BOARD_NAME=? and ARTICLE_TITLE=? and USER_ID=?";
//		RowMapper<List> rm = rs -> new ArrayList<ArticleComment>();
//		return jdbcTemplate.executeQuery(sql, rm, articleComment.getShopUrl(), articleComment.getBoardName(), articleComment.getArticleTitle(), articleComment.getUserId());
//	}
}

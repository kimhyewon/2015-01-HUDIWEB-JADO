package jado.dao;

import jado.model.Board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final Board board) {
		String sql = "insert into BOARD values(null, ?, ?)";
		jdbcTemplate.update(sql, board.getShopUrl(), board.getName());
	}

	public Board selectByPk(final int boardId) {
		String sql = "select * from BOARD where ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), boardId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Board> selectAllByUrl(final String url) {
		String sql = "select * from BOARD where SHOP_URL=?";
		try {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Board>(Board.class), url);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final int boardId) {
		String sql = "delete from BOARD where ID=?";
		jdbcTemplate.update(sql, boardId);
	}
	
	public int countArticles(int boardId) {
		String sql = "select count(*) from ARTICLE WHERE BOARD_ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, boardId);			
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
}

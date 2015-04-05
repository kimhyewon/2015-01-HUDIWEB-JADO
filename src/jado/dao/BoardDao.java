package jado.dao;

import jado.model.Board;

import org.springframework.stereotype.Repository;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

@Repository
public class BoardDao {
	public static void insert(final Board board) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into BOARD values(?, ?)";
		jdbcTemplate.executeUpdate(sql,board.getShopUrl(), board.getName());
	}

	public static Board select(Board board) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select * from BOARD where Name=?";
		RowMapper<Board> rm = rs -> new Board(rs.getString(1),rs.getString(2));
		return jdbcTemplate.executeQuery(sql, rm, board.getName());
	}
}

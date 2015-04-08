package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public void insert(final Board board) {
		String sql = "insert into BOARD values(?, ?, ?)";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		jdbcTemplate.update(sql, args);
	}

	public Board selectByPk(final Board board) {
		String sql = "select * from BOARD where SHOP_URL=? and Name=?";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		try {
			return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Board>(Board.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Board> selectAllByUrl(final Board board) {
		String sql = "select * from BOARD where SHOP_URL=?";
		Object[] args = new Object[] { board.getShopUrl() };
		try {
			return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Board>(Board.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final Board board) {
		String sql = "delete from BOARD where SHOP_URL=? and NAME=?";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		jdbcTemplate.update(sql, args);
	}

}

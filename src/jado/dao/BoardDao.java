package jado.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import jado.model.Board;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public void insert(final Board board) {
		String sql = "insert into BOARD values(?, ?)";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		getJdbcTemplate().update(sql, args);
	}

	public Board selectByPk(Board board) {
		String sql = "select * from BOARD where SHOP_URL=? and Name=?";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		try {
			return getJdbcTemplate().queryForObject(sql, args, new BeanPropertyRowMapper<Board>(Board.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Board> selectAllByUrl(Board board) {
		String sql = "select * from BOARD where SHOP_URL=?";
		Object[] args = new Object[] { board.getShopUrl() };
		try {
			return getJdbcTemplate().query(sql, args, new BeanPropertyRowMapper<Board>(Board.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(Board board) {
		String sql = "delete from BOARD where SHOP_URL=? and NAME=?";
		Object[] args = new Object[] { board.getShopUrl(), board.getName() };
		getJdbcTemplate().update(sql, args);
	}

}

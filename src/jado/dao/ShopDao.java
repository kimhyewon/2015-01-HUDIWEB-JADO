package jado.dao;

import jado.model.Shop;

import javax.annotation.PostConstruct;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public void insert(final Shop shop) {
		String sql = "insert into SHOP values(?, default, ?, default, default ,default, ?, default)";
		getJdbcTemplate().update(sql, shop.getUrl(), shop.getPhone(), shop.getFooter());
	}

	public void update(final Shop shop) {
		String sql = "update SHOP set TITLE = ?, PHONE = ?, BANNER_URL=?, MAIN_URL=?, LOGO_URL=?, THEME=?, FOOTER=? where URL = ?";
		getJdbcTemplate().update(sql, shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter(), shop.getUrl());
	}

	public Shop selectByUrl(final String url) {
		String sql = "select * from SHOP where URL=?";
		try {
			return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Shop>(Shop.class), url);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final String url) {
		String sql = "delete from SHOP where URL = ?";
		getJdbcTemplate().update(sql, url);
	}

}
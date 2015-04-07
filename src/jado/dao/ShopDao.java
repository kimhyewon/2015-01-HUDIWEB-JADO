package jado.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import jado.model.Shop;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDao extends JdbcDaoSupport {

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
	}

	public void insert(final Shop shop) {
		String sql = "insert into SHOP values(?, default, ?, default, default ,default, default , default)";
		getJdbcTemplate().update(sql, shop.getUrl(), shop.getPhone());
	}

	public void update(final Shop shop) {
		String sql = "update SHOP set TITLE = ?, PHONE = ?, BANNER_URL=?, MAIN_URL=?, LOGO_URL=?, THEME=?, FOOTER=? where URL = ?";
		getJdbcTemplate().update(sql, shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter(), shop.getUrl());
	}

	public Shop selectByUrl(final String url) {
		String sql = "select * from SHOP where URL=?";
		RowMapper<Shop> rm = new RowMapper<Shop>() {
			@Override
			public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Shop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
		};
		return getJdbcTemplate().queryForObject(sql, rm, url);
	}

	public void remove(final String url) {
		String sql = "delete from SHOP where URL = ?";
		getJdbcTemplate().update(sql, url);
	}

}
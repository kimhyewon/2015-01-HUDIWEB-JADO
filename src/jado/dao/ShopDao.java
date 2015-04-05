package jado.dao;

import jado.model.Shop;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScripts(new ClassPathResource("sql/initDbSchema.sql"), new ClassPathResource("sql/insertTestSet.sql"));
		DatabasePopulatorUtils.execute(populator, dataSource);
	}
	
	public  void insert(final Shop shop) {
		String sql = "insert into shop values(?, ?, ?, ?, ? ,? ,? )";
		jdbcTemplate.update(sql, shop.getUrl(), shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter());
	}

	public  void update(final Shop shop) {
		String sql = "update SHOP set TITLE = ?, PHONE = ?, BANNER_URL=?, MAIN_URL=?, LOGO_URL=?, THEME=?,  FOOTER=? where URL = ?";
		jdbcTemplate.update(sql, shop.getTitle(), shop.getPhone(), shop.getBanner_url(), shop.getMain_url(), shop.getLogo_url(), shop.getTheme(), shop.getFooter(), shop.getUrl());
	}
	
	//TODO 고쳐야 함 
	public  Shop selectByUrl(final String url) {
		String sql = "select * from SHOP where URL=?";
//		RowMapper<Shop> rm = rs -> new Shop(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
		return (Shop) jdbcTemplate.queryForObject(sql, new Object [] {url}, Shop.class);
//		return jdbcTemplate.executeQuery(sql, rm, url);
	}

	public  void remove(final String url) {
		String sql = "delete from SHOP where URL = ?";
		jdbcTemplate.update(sql, url);
	}

}
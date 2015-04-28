package jado.dao;

import jado.model.FileInfo;
import jado.model.Shop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public void insert(final Shop shop) {
		String sql = "insert into SHOP values(?, default, ?, default, default , ?, default, default, default, default)";
		jdbcTemplate.update(sql, shop.getUrl(), shop.getPhone(), shop.getFooter());
	}

	public void updateInfo(final Shop shop) {
		String sql = "update SHOP set TITLE = ?, PHONE = ?, FOOTER=?, THEME=?, where URL = ?";
		Object[] args = new Object[] {shop.getTitle(), shop.getPhone(), shop.getFooter(), shop.getTheme(), shop.getUrl()};
		jdbcTemplate.update(sql, args);
	}
	
	public void updateImage(final Shop shop) {
		String sql = "update SHOP set MAIN_URL=?, PROFILE_URL=?, SUB_IMG1=?, SUB_IMG2=?, SUB_IMG3=? where URL = ?";
		Object[] args = new Object[] {shop.getMainUrl(), shop.getProfileUrl(), shop.getSubImg1Url(), shop.getSubImg2Url(), shop.getSubImg3Url(), shop.getUrl()};
		jdbcTemplate.update(sql, args);
	}

	public Shop selectByUrl(final String url) {
		String sql = "select * from SHOP where URL=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Shop>(Shop.class), url);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void remove(final String url) {
		String sql = "delete from SHOP where URL = ?";
		jdbcTemplate.update(sql, url);
	}

	public void updateImageUrl(FileInfo fileInfo) {
		String sql = "update SHOP set ".concat(fileInfo.getType())+"=? where URL=?";
		Object[] args = new Object[]{ fileInfo.getLocalLocation(), fileInfo.getUrl()};
		jdbcTemplate.update(sql, args);
		
	}

}
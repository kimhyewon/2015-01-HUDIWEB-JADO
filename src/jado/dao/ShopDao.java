package jado.dao;

import jado.model.FileInfo;
import jado.model.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insert(final Shop shop) {
		String sql = "insert into SHOP values(?, default, ?, default, default , ?, default, default, default, default)";
		jdbcTemplate.update(sql, shop.getUrl(), shop.getPhone(), shop.getFooter());
	}

	public void updateInfo(final Shop shop) {
		String sql = "update SHOP set TITLE = ?, PHONE = ?, FOOTER=?, THEME=? where URL = ?";
		jdbcTemplate.update(sql, shop.getTitle(), shop.getPhone(), shop.getFooter(), shop.getTheme(), shop.getUrl());
	}
	
	public void updateImage(final Shop shop) {
		String sql = "update SHOP set MAIN_URL=?, PROFILE_URL=?, SUB_IMG1=?, SUB_IMG2=?, SUB_IMG3=? where URL = ?";
		jdbcTemplate.update(sql, shop.getMainUrl(), shop.getProfileUrl(), shop.getSubImg1Url(), shop.getSubImg2Url(), shop.getSubImg3Url(), shop.getUrl());
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
		jdbcTemplate.update(sql, fileInfo.getLocalLocation(), fileInfo.getUrl());
	}

	public void setTheme(int theme, String userId) {
		String sql = "update SHOP inner join SELLER on SHOP.URL = SELLER.SHOP_URL set SHOP.THEME=? where SELLER.ID=?";
		jdbcTemplate.update(sql, theme, userId);
	}
}
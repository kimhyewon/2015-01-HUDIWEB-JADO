package jado.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

@Repository
public class MailAuthDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}
	
	public void insert(String mailRecipient, String uuid) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into MAIL_AUTH values(?, ?, null)";
		jdbcTemplate.executeUpdate(sql, mailRecipient, uuid);
//		jdbcTemplate.update(sql, mailRecipient, uuid);
	}
	
	public Boolean verify(String userId, String uuid) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select count(*) from MAIL_AUTH where USER_ID=? and UUID_KEY = ?";
		RowMapper<Integer> rm = rs -> rs.getInt(1);
		if (jdbcTemplate.executeQuery(sql, rm, userId, uuid) > 0)
			return true;
		return false;
//		try {
//			Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId, uuid);
//			if(result > 0) return true;
//			return false;
//		} catch (EmptyResultDataAccessException e) {
//			return false;
//		}
	}

	public boolean isAlreadyVerified(String userEmail) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select EMAIL_VALIDATE_STATUS from USER where ID=?";
		RowMapper<String> rm = rs -> rs.getString("EMAIL_VALIDATE_STATUS");
		String result = jdbcTemplate.executeQuery(sql, rm, userEmail);
		if(result == null) return false;
		if(result.equals("T")) return true;
		return false;
//		try {
//			if(jdbcTemplate.queryForObject(sql, String.class, userEmail).equals("T")) return true; 
//			return false;
//		} catch (EmptyResultDataAccessException e) {
//			return false;
//		}
	}
}

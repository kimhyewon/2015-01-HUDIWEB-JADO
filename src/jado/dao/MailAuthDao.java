package jado.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MailAuthDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void insert(String mailRecipient, String uuid) {
		String sql = "insert into MAIL_AUTH values(?, ?, null)";
		jdbcTemplate.update(sql, mailRecipient, uuid);
	}
	
	public Integer verify(String userId, String uuid) {
		String sql = "select count(*) from MAIL_AUTH where USER_ID=? and UUID_KEY = ?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, userId, uuid);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String userRole(String userEmail) {
		String sql = "select ROLE from USER_ROLE WHERE USER_ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, String.class, userEmail);		
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

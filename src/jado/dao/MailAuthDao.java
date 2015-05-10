package jado.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	public Boolean verify(String userId, String uuid) {
		String sql = "select count(*) from MAIL_AUTH where USER_ID=? and UUID_KEY = ?";
		try {
			Integer result = jdbcTemplate.queryForObject(sql, Integer.class, userId, uuid);
			if(result > 0) return true;
			return false;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	public boolean isAlreadyVerified(String userEmail) {
		String sql = "select EMAIL_VALIDATE_STATUS from USER where ID=?";
		try {
			if(jdbcTemplate.queryForObject(sql, String.class, userEmail).equals("T")) return true; 
			return false;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
}

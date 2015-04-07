package jado.dao;

import org.springframework.stereotype.Repository;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

@Repository
public class MailAuthDao {
	public void insert(String mailRecipient, String uuid) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "insert into MAIL_AUTH values(?, ?, ?)";
		jdbcTemplate.executeUpdate(sql, mailRecipient, uuid, new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public Boolean verify(String userId, String uuid) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select count(*) as count from MAIL_AUTH where USER_ID=? and UUID_KEY = ?";
		RowMapper<Integer> rm = rs -> rs.getInt("count");
		if (jdbcTemplate.executeQuery(sql, rm, userId, uuid) > 0)
			return true;
		return false;
	}

	public boolean isAlreadyVerified(String userEmail) {
		JdbcTemplate222 jdbcTemplate = new JdbcTemplate222();
		String sql = "select EMAIL_VALIDATE_STATUS from USER where ID=?";
		RowMapper<String> rm = rs -> rs.getString("EMAIL_VALIDATE_STATUS");
		
		// 회원가입 한 적 없는 회원이 부정한 방법으로 메일 인증을 시도할 경우 
		if(jdbcTemplate.executeQuery(sql, rm, userEmail) == null) return false;
		
		if (jdbcTemplate.executeQuery(sql, rm, userEmail).equals("T"))
			return true;
		return false;
	}
}

package jado.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class MailAuthDao {
	public void insert(String mailRecipient, String uuid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "insert into MAIL_AUTH values(?, ?, ?)";
		jdbcTemplate.executeUpdate(sql, mailRecipient, uuid, new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public Boolean verify(String userId, String uuid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "select count(*) as count from MAIL_AUTH where USER_ID=? and UUID_KEY = ?";
		RowMapper<Integer> rm = rs -> rs.getInt("count");
		if (jdbcTemplate.executeQuery(sql, rm, userId, uuid) > 0)
			return true;
		return false;
	}
}

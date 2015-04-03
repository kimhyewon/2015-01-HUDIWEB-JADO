package jado.dao;

import core.jdbc.JdbcTemplate222;
import core.jdbc.RowMapper;

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
		String sql = "select IS_VALIDATED from USER where ID=?";
		RowMapper<String> rm = rs -> rs.getString("IS_VALIDATED");
		if (jdbcTemplate.executeQuery(sql, rm, userEmail).equals("T"))
			return true;
		return false;
	}
}

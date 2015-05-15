package core.util;

import jado.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityManagerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void updateUserRole(User user, String authorityTobeChanged) {
		String sql = "update USER_ROLE set ROLE=? WHERE USER_ID=?";
		jdbcTemplate.update(sql, authorityTobeChanged, user.getId());
	}

	public String findUserRoleByUserId(String userId) {
		String sql = "select ROLE from USER_ROLE where USER_ID=?";
		try {
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<String>(String.class), userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}

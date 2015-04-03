package jado.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import core.jdbc.ConnectionManager;
import core.jdbc.DataAccessException;
import core.jdbc.PreparedStatementSetter;

public class ObjectMapper<T> implements RowMapper<T> {
	// public <T> mapRow(ResultSet rs, int rowNum) throws SQLException {
	//
	// }

	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) {
		PreparedStatementSetter pss = createPreparedStatementSetter(parameters);
		return executeQuery(sql, rm, pss);
	}

	private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) {
				try {
					for (int i = 0; i < parameters.length; i++) {
						pstmt.setObject(i + 1, parameters[i]);
					}
				} catch (SQLException e) {
					throw new DataAccessException();
				}
			}
		};
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return null;
	}
}
//
// public class StudentMapper implements RowMapper<Student> {
// public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
// Student student = new Student();
// student.setId(rs.getInt("id"));
// student.setName(rs.getString("name"));
// student.setAge(rs.getInt("age"));
// return student;
// }
// }
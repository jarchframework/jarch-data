package org.jarchframework.data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class LimitAwareResultSetExtractor<T> {

	private RowMapper<T> rowMapper;

	public LimitAwareResultSetExtractor(RowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}

	public List<T> extractData(ResultSet result, int limit) {
		List<T> list = new ArrayList<>();
		try {
			int rowNum = 0;
			while (result.next()) {
				if (rowNum >= limit) {
					break;
				}
				list.add(rowMapper.mapRow(result, rowNum));
				rowNum++;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}

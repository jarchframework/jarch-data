package org.jarchframework.data.jdbc;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jarchframework.core.util.UtilsForReflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class BeanRowMapper<T> implements RowMapper<T> {

	private static final Logger logger = LoggerFactory.getLogger(BeanRowMapper.class);

	private Class<T> clazz;
	private List<String> fieldNames;

	public BeanRowMapper(Class<T> clazz) {
		this.clazz = clazz;
		fieldNames = UtilsForReflection.getFieldNamesByGetterMethod(clazz);
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T instance = createInstance();

		if (instance == null) {
			return null;
		}

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = JdbcUtils.lookupColumnName(metaData, i);
			String column = columnName.replace("_", "").replace(" ", "").toLowerCase(Locale.ENGLISH);

			for (String fieldName : fieldNames) {
				String f = fieldName.toLowerCase(Locale.ENGLISH);
				String fieldNameEndWithID = f + "id";

				if (f.equalsIgnoreCase(column) || fieldNameEndWithID.equalsIgnoreCase(columnName)) {
					Method method = UtilsForReflection.getNestedGetterMethod(clazz, fieldName);
					Class<?> returnType = method.getReturnType();

					Object value = JdbcUtils.getResultSetValue(rs, i, returnType);
					if (value instanceof byte[]) {
						value = SerializationUtils.deserialize((byte[]) value);
					}

					try {
						UtilsForReflection.getNestedSetterMethod(clazz, fieldName).invoke(instance, value);
					} catch (Exception e) {
						logger.info(ExceptionUtils.getRootCauseMessage(e));
					}
				}
			}
		}

		return instance;
	}

	protected T createInstance() {
		T newInstance = null;
		try {
			newInstance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
		return newInstance;
	}

}

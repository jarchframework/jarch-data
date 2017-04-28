package org.jarchframework.data.test.audit;

import java.util.Collection;

import org.jarchframework.core.util.UtilsForReflection;

public class EntityStateExtractor {

	private final Object entity;

	public EntityStateExtractor(Object entity) {
		super();
		this.entity = entity;
	}

	public String[] collectPropertyNames() {
		Collection<String> fieldNames = UtilsForReflection.getFieldNames(entity.getClass());
		return fieldNames.toArray(new String[0]);
	}

	public Object[] collectStateValues() {
		Collection<String> fieldNames = UtilsForReflection.getFieldNames(entity.getClass());
		Object[] state = new Object[fieldNames.size()];
		int i = 0;
		for (String name : fieldNames) {
			state[i] = UtilsForReflection.getValue(entity, name);
			i++;
		}
		return state;
	}

}

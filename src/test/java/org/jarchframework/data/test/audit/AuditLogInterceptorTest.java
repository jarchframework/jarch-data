package org.jarchframework.data.test.audit;

import org.hibernate.type.Type;
import org.jarchframework.data.audit.AuditLogInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuditLogInterceptorTest {

	private AuditLogInterceptor auditInterceptor = new TestAuditLogInterceptorImpl();

	private TestEntityNotAuditable entity1;
	private TestEntity entity2;
	private Type[] types;

	@Before
	public void setup() {
		this.entity1 = new TestEntityNotAuditable("John Doe", 32);
		this.entity1.setId(1L);
		this.entity2 = new TestEntity("John Doe", 32);
		this.entity2.setId(2L);
		// types not used in our implementation so just ignore mocking it
		this.types = new Type[0];
	}

	@Test
	public void testEntityUpdateShouldReturnFalseWhenEntityHasNotAuditProperty() {

		EntityStateExtractor extractor = new EntityStateExtractor(entity1);
		String[] propertyNames = extractor.collectPropertyNames();
		Object[] prevState = extractor.collectStateValues();
		Object[] currentState = extractor.collectStateValues();

		boolean stateModified = auditInterceptor.onFlushDirty(entity1, entity1.getId(), currentState, prevState,
				propertyNames, types);
		Assert.assertEquals(false, stateModified);
	}

	@Test
	public void testEntityUpdateShouldReturnTrueWhenEntityHasAuditProperty() {

		EntityStateExtractor extractor = new EntityStateExtractor(entity2);
		String[] propertyNames = extractor.collectPropertyNames();
		Object[] prevState = extractor.collectStateValues();
		Object[] currentState = extractor.collectStateValues();

		boolean stateModified = auditInterceptor.onFlushDirty(entity2, entity2.getId(), currentState, prevState,
				propertyNames, types);
		Assert.assertEquals(true, stateModified);
	}

	@Test
	public void testEntitySaveShouldReturnFalseWhenEntityHasNotAuditProperty() {

		EntityStateExtractor extractor = new EntityStateExtractor(entity1);
		String[] propertyNames = extractor.collectPropertyNames();
		Object[] state = extractor.collectStateValues();

		boolean stateModified = auditInterceptor.onSave(entity1, entity1.getId(), state, propertyNames, types);
		Assert.assertEquals(false, stateModified);
	}

	@Test
	public void testEntitySaveShouldReturnTrueWhenEntityHasAuditProperty() {

		EntityStateExtractor extractor = new EntityStateExtractor(entity2);
		String[] propertyNames = extractor.collectPropertyNames();
		Object[] state = extractor.collectStateValues();

		boolean stateModified = auditInterceptor.onSave(entity2, entity2.getId(), state, propertyNames, types);
		Assert.assertEquals(true, stateModified);
	}

}

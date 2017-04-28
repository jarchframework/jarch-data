package org.jarchframework.data.test.audit;

import org.jarchframework.data.audit.AuditLogInterceptor;

public class TestAuditLogInterceptorImpl extends AuditLogInterceptor {

	@Override
	public String resolveUsername() {
		return "testUser";
	}

}

package org.jarchframework.data.audit;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * This abstract class is an hibernate interceptor responsible for persisting
 * basic audit state when any entity saved or updated automatically. Extend this
 * to implement resolving authentication principal behaviour
 * 
 * @author Yavuz S.Tas
 *
 */
public abstract class AuditLogInterceptor extends EmptyInterceptor {

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		for (int i = 0; i < propertyNames.length; i++) {
			if (currentState[i] instanceof Audit) {
				Audit audit = (Audit) currentState[i];
				audit.setModifiedBy(resolveUsername());
				audit.setModificationTime(ZonedDateTime.now());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		for (int i = 0; i < propertyNames.length; i++) {
			if (state[i] instanceof Audit) {
				Audit audit = (Audit) state[i];
				audit.setCreatedBy(resolveUsername());
				audit.setCreationTime(ZonedDateTime.now());
				return true;
			}
		}
		return false;
	}

	public abstract String resolveUsername();

}

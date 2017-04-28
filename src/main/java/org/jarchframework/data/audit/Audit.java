package org.jarchframework.data.audit;

import java.time.ZonedDateTime;

import org.jarchframework.core.model.BaseObject;
import org.jarchframework.core.model.Identity;
import org.jarchframework.core.model.ToString;

@Identity({ "createdBy", "creationTime" })
@ToString({ "createdBy", "creationTime", "modifiedBy", "modificationTime" })
public class Audit extends BaseObject {

	private String createdBy;
	private ZonedDateTime creationTime;

	private String modifiedBy;
	private ZonedDateTime modificationTime;

	public Audit() {
	}

	public Audit(String createdBy, ZonedDateTime creationTime) {
		this.createdBy = createdBy;
		this.creationTime = creationTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public ZonedDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ZonedDateTime getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(ZonedDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

}

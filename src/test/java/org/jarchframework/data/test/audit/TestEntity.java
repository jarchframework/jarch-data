package org.jarchframework.data.test.audit;

import org.jarchframework.data.audit.Audit;

class TestEntity {

	public TestEntity() {
		// TODO Auto-generated constructor stub
	}

	public TestEntity(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	private Long id;
	private String name;
	private int age;
	private Audit audit = new Audit();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

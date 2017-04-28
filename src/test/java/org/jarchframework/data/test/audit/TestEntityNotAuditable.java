package org.jarchframework.data.test.audit;

class TestEntityNotAuditable {

	public TestEntityNotAuditable() {
		// TODO Auto-generated constructor stub
	}

	public TestEntityNotAuditable(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	private Long id;
	private String name;
	private int age;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

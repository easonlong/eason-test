package com.eason.coding.life.reflect;

public class Pojo {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setId(String id) {
		this.id = Long.valueOf(id);
	}

	public String toString() {
		return String.valueOf(this.id);
	}
}

package com.eason.coding.life.entity;

import java.util.Calendar;

public class Person {
	private String name;
	private Calendar birthDate;
	private Life life;
	
	public Person(String name, Calendar birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public Life getLife() {
		return life;
	}

	public void setLife(Life life) {
		this.life = life;
	}

	public int getAge() {
		Calendar today = Calendar.getInstance();
		return today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
	}

	private void changeNameAndBirthDate(String name, Calendar birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}
	public String introduceMyself(){
		return "I am "+getName()+", and I am "+getAge();
	}

	@Override
	public int hashCode() {
		return super.hashCode() + name.hashCode()
				+ birthDate.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(obj) && birthDate.equals(obj);
	}
	
}

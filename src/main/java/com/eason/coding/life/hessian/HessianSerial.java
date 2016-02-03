package com.eason.coding.life.hessian;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.fusesource.hawtbuf.ByteArrayInputStream;
import org.junit.Test;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianSerial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int BUFFER = 0x4 << 6;

	@Test
	public void test1() throws IOException{
		ByteArrayOutputStream os = null;
		HessianOutput output = new HessianOutput(new BufferedOutputStream(os = new ByteArrayOutputStream(BUFFER)));
		output.writeString("Eason");
		output.writeString("M");
		output.writeInt(26);
		output.writeBoolean(true);
		output.flush();
		byte[] array = os.toByteArray();
		System.out.println(new String(array, "UTF-8"));
		HessianInput input = new HessianInput(new BufferedInputStream(new ByteArrayInputStream(array)));
		String name = input.readString();
		String sex = input.readString();
		int age = input.readInt();
		boolean married = input.readBoolean();
		System.out.println("name:" + name + " sex:" + sex + " age:" + age + " married:" + married);
	}
	
	@Test
	public void test2() throws IOException{
		ByteArrayOutputStream os = null;
		HessianOutput output = new HessianOutput(new BufferedOutputStream(os = new ByteArrayOutputStream(BUFFER)));
		output.writeObject(new Family(new Person("Eason", (short)26, (short)(1<<15 -1)), 1));
		output.flush();
		byte[] array = os.toByteArray();
		System.out.println(new String(array, "UTF-8"));
		HessianInput input = new HessianInput(new BufferedInputStream(new ByteArrayInputStream(array)));
		Family family=(Family) input.readObject();
		System.out.println(ToStringBuilder.reflectionToString(family.getPerson()));
	}
	
	private class Family implements Serializable{
		private Person person;
		private int count;
		private Family(Person person, int count) {
	        super();
	        this.person = person;
	        this.count = count;
        }
		public Person getPerson() {
			return person;
		}
		public void setPerson(Person person) {
			this.person = person;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
	}
	private class Person implements Serializable{
		
		private static final long serialVersionUID = 1L;

		private String name;
		
		private short age;

		private short level;
		
		private Person(String name, short age, short level) {
			super();
			this.name = name;
			this.age = age;
			this.level = level;
		}

	}
}

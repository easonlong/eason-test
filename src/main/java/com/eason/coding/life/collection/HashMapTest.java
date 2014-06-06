package com.eason.coding.life.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.eason.coding.life.entity.Person;

public class HashMapTest {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, 1, 21);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(1991, 2, 22);
		Person eason = new Person("Eason", calendar);
		Person eason2 = new Person("Eason2", calendar2);
		Map<Person, String> map = new HashMap<Person, String>();
		map.put(eason, "eason");
		map.put(eason2, "eason2");
		System.out.println("eason.hashcode=" + eason.hashCode());
		System.out.println(map.get(eason));
		eason.setName("eason3");
		System.out.println("eason.hashcode=" + eason.hashCode());
		System.out.println(map.get(eason));
	}

}

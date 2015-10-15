package com.eason.coding.life.json;

import net.sf.json.JSONObject;

public class JsonTest {
	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Eason");
		jsonObject.put("age", 12);
		System.out.println(jsonObject.getString("ff"));
		
		String jsonString="{\"name\":\"Eason\",\"age\":12}";
		JSONObject jsonObject2=JSONObject.fromObject(jsonString);
		System.out.println(jsonObject2.toString());
	}
}

package com.eason.coding.life.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
	public static final String URL1 = "http://10.128.8.100:10000/detail?uid=1&cid=1&signature=YjM1NjNjNDk5MTNiYmJmNjE1MjFiMWU3ZGZlYmRjMzY=&old.sign=6DE53CF1084FADBF20AB246385A0813D&old.uid=1&old.logid=1&old.count=1";
	public static final String URL2 = "http://10.128.8.100:8080/web-points/detail?uid=1&cid=1&signature=YjM1NjNjNDk5MTNiYmJmNjE1MjFiMWU3ZGZlYmRjMzY=&old.sign=6DE53CF1084FADBF20AB246385A0813D&old.uid=1&old.logid=1&old.count=1";
	public static final int TIMEOUT_CONNECTION = 5000;
	public static final int TIMEOUT_READ = 5000;
	public static final int REQ_TIMES = 1;

	public static void main(String[] args) throws IOException {
		System.out.println("URL1:" + cost(URL1, REQ_TIMES));
		System.out.println("URL2:" + cost(URL2, REQ_TIMES));
	}

	public static long cost(String urlString, int requestTimes) {
		long start = System.currentTimeMillis();
		int exceptionCount = 0;
		for (int i = 0; i < requestTimes; i++) {
			try {
				URL url = new URL(urlString);
				HttpURLConnection connection = HttpURLConnection.class.cast(url.openConnection());
				connection.setConnectTimeout(TIMEOUT_CONNECTION);
				connection.setReadTimeout(TIMEOUT_READ);
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "/n");
				}
				System.out.println(sb.toString());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				exceptionCount++;
			}
		}
		System.out.println("exceptionCount:" + exceptionCount);
		long end = System.currentTimeMillis();
		return (end - start) / requestTimes;
	}
}

package com.eason.coding.life;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) {
	  
        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("JST"));
        Date date1=cal.getTime();
		System.out.println(date1.toString());
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date2=cal.getTime();
		System.out.println(date2.toString());
		
	}
	


}

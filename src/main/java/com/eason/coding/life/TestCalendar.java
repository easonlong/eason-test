package com.eason.coding.life;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestCalendar {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EDT"));
        Date begainDate=dateFormat.parse("2014-10-18 20:00:00");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        Date endDate=dateFormat.parse("2014-12-18 19:00:00");
        System.out.println(getNumOfDaysBetweenCalendarsExclusiveOfEndDate(begainDate, endDate, Calendar.DATE));
        
    }

    public static  Integer getNumOfDaysBetweenCalendarsExclusiveOfEndDate(Date earlierDate, Date laterDate, int compareOnWhat) {
        if (earlierDate == null || laterDate == null) {
            return 0;
        }
        Calendar earlierCal = Calendar.getInstance();
        earlierCal.setTime(earlierDate);
        Calendar laterCal = Calendar.getInstance();
        laterCal.setTime(laterDate);
        return getNumOfDaysBetweenCalendarsExclusiveOfEndDate(earlierCal, laterCal, compareOnWhat);
    }

    public static  Integer getNumOfDaysBetweenCalendarsExclusiveOfEndDate(Calendar earlierCal, Calendar laterCal, int compareOnWhat) {
        int numberOfX = 0;
        Calendar tempEarlierCal = Calendar.getInstance();
        tempEarlierCal.setTime(earlierCal.getTime());
        while (tempEarlierCal.compareTo(laterCal) <= 0) {
            numberOfX++;
            tempEarlierCal.add(compareOnWhat, 1);
        }
        return numberOfX - 1;
    }
}

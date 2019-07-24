package com.binance.assignments.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateAndTimeDifferenceUtil {

    public static long getDateDifferenceInHours(String date,String startTime,String endTime) throws Exception
    {
        if(date == null || date =="") throw new Exception("invalid Date");
        if(startTime ==null || startTime =="") throw new Exception("invalid start time");
        if (endTime == null || endTime =="") throw new Exception("Invalid end time");
        LocalDateTime dateStartTime,localEndDateTime;
        try{
             dateStartTime = LocalDateTime.parse(date+"T"+startTime+":00");
        }catch(Exception ex){
            throw new Exception("Unable to convert start time to correct date time");
        }
        try{
            localEndDateTime = LocalDateTime.parse(date+"T"+endTime+":00");
        }catch(Exception ex){
            throw new Exception("Unable to convert end time to correct date time");
        }
        long hours = ChronoUnit.HOURS.between(dateStartTime, localEndDateTime);
        return hours;
    }
}

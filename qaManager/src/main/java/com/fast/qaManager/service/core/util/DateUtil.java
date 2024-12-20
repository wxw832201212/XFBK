package com.fast.qaManager.service.core.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtil {
    public static Timestamp add(Timestamp timestamp, Integer day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.DATE, day);
        Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());
        return newTimestamp;
    }
}

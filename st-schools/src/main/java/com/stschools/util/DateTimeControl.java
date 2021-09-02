package com.stschools.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeControl {
    public static String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return dateFormat.format(date);
    }
}

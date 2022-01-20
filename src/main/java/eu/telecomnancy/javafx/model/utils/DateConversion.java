package eu.telecomnancy.javafx.model.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateConversion {

    private Date javaDate;
    private java.sql.Date sqlDate;

    public DateConversion(Date javaDate){
        this.javaDate=javaDate;
    }
    public DateConversion(java.sql.Date sqlDate){
        this.sqlDate=sqlDate;
    }

    public java.sql.Date javaToSql(Date javaDate){

        long timeInMilliSeconds = javaDate.getTime();
        java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);

        return date1;
    }

    public Date sqlToJava(java.sql.Date sqlDate){

        Date utilDate = new Date(sqlDate.getTime());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        return utilDate;
    }

    public int weekOfYear(int year, int month, int day){

        Calendar calendar = Calendar.getInstance(Locale.FRANCE);
        calendar.set(year, month-1, day);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfYear;
    }
}




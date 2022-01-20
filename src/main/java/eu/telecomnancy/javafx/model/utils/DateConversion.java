package eu.telecomnancy.javafx.model.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
}




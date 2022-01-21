package eu.telecomnancy.javafx.model.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateConversion {

    public DateConversion(){}

    public static int getWeek(String date){
        String input = date;
        String format = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date dateBis = new Date();
        try {
            dateBis = df.parse(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("FCT getWeek");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateBis);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    public static Date stringToDate(String date){
        try {
            Date dateBis = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return dateBis;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Date();
    }

    public static String dateToString(Date date){
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateBis = df.format(date);
        return dateBis;
    }
    
    public static int getMonth(String date){
        String[] dateElement = date.split("-");
        String mois = dateElement[1];
        int moisInt = Integer.parseInt(mois);
        return moisInt;
    }

    public static int getDay(String date){
        String[] dateElement = date.split("-");
        String jour = dateElement[2];
        int dayInt = Integer.parseInt(jour);
        return dayInt;
    }
}





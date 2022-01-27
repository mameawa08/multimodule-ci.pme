package com.scoring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private static final int SECOND = 1000;
	private static final int MINUTE = 60 * SECOND;
	private static final int HOUR = 60 * MINUTE;
	private static final int DAY = 24 * HOUR;
	private static final Long MONTH = 31 * Long.valueOf(DAY);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dateFormatWithDash = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static String formatDate(Date date) {
        if (date != null)
            return dateFormat.format(date);
        return "";
    }
    public static String formatDateTime(Date date) {
        if (date != null)
            return dateTimeFormat.format(date);
        return "";
    }

    public static Date getZeroTimeDate(Date fecha) {
		Date res = fecha;
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		res = calendar.getTime();
		return res;
	}

	public static Date dateBefore2Weeks() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -14);
        return cal.getTime();
    }
	
	public static Date dateBefore3Weeks() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -21);
		return cal.getTime();	
	}
	
	public static String getDureeEntreDatesEnMn(Date date1, Date date2){ 
		Long duree=new Date(date2.getTime()-date1.getTime()).getTime();
		StringBuffer text = new StringBuffer("");
		if (duree > MONTH) {
			text.append(duree / MONTH).append("m ");
			duree %= MONTH;
		}
		if (duree > DAY) {
		  text.append(duree / DAY).append("j ");
		  duree %= DAY;
		}
		if (duree > HOUR) {
		  text.append(duree / HOUR).append("h");
		  duree %= HOUR;
		}
		if (duree > MINUTE) {
			  text.append(duree / MINUTE).append("mn");
			  duree %= MINUTE;
		}
		String duration = text.toString() ;
		return duration;
	}
	
	public static Long getDureeEnLongEntreDates(Date date1, Date date2){ 
		Long duree=new Date(date2.getTime()-date1.getTime()).getTime();
		return duree;
	}

	public static Date parseDate(String date){
		try {
			return dateFormatWithDash.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static LocalDate toLocalDate(Date date){
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static int getAge(Date date){
    	return Period.between(toLocalDate(date), LocalDate.now()).getYears();
	}

}

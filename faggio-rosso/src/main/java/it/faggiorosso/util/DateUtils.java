package it.faggiorosso.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static LocalDate convertDateToLocalDate(java.util.Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date convertLocalDateToDate(LocalDate localDate) {
		return Date.valueOf(localDate);
	}
	
	public static String prettyLocalDate(LocalDate localDate) {
		return localDate.format(getDateTimeFormatter());
	}
	
	private static DateTimeFormatter getDateTimeFormatter() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
}
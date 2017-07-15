package com.apress.prospring5.ch16.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by iuliana.cosmina on 7/12/17.
 */
public class DateFormatter implements Formatter<Date> {
	public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date parse(String s, Locale locale) throws ParseException {
		return formatter.parse(s);
	}

	@Override
	public String print(Date date, Locale locale) {
		return formatter.format(date);
	}

}
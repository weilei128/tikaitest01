package com.pctic.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 时间相关工具类
 * @author meihongli
 *
 */
public class DateUtils {

	/**
	 * String转换为Date
	 * @param string
	 * @param format
	 * @return
	 * @throws ParseException String与format不匹配
	 */
	public static Date stringToDate(String string, DateFormat format) throws ParseException {
		if (string == null) {
			return null;
		}
		Date date = format.parse(string);
		return date;
	}
	
	/**
	 * Date转换为String
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, DateFormat format) {
		if (date == null) {
			return null;
		}
		return format.format(date);
	}

}

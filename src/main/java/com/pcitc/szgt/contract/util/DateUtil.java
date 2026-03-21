package com.pcitc.szgt.contract.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateUtil {

  public static SimpleDateFormat FMT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static SimpleDateFormat FMT_DATE = new SimpleDateFormat("yyyy-MM-dd");
  public static String FMT_DATE_MONTH = "yyyy-MM";
  public static String FMT_NOW_TIME = "yyyy-MM-dd HH:mm:ss" ;

  /**
   * 格式化现有时间
   * @param formatStr
   * @return
   */
  public static String getFormatNowTime(String formatStr) {
    SimpleDateFormat myFormatter = new SimpleDateFormat(formatStr);
    String time = myFormatter.format(new Date());
//    System.out.println(time);
    return time;
  }
  /**
   * 获取当前时间 
   * @param date
   * @return
   */
  public static String getNowDate(Date date) {
    return date == null ? null : FMT_DATE.format(date);
  }
  public static String getDateFormatStr(Date date, SimpleDateFormat timeFormat) {
    return date == null ? "" : timeFormat.format(date);
  }
  /**
   * Description: 获取GMT8时间
   * @return 将当前时间转换为GMT8时区后的Date
   */
  public static Date getGMT8Time(){
      Date gmt8 = null;
      try {
          Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"),Locale.CHINESE);
          Calendar day = Calendar.getInstance();
          day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
          day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
          day.set(Calendar.DATE, cal.get(Calendar.DATE));
          day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
          day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
          day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
          gmt8 = day.getTime();
      } catch (Exception e) {
          System.out.println("获取GMT8时间 getGMT8Time() error !");
          e.printStackTrace();
          gmt8 = null;
      }
      return  gmt8;
  } 
  /**
   * 根据参数获得前某天的时间日期 
   * @param day
   * @return
   */
  public static String getDateByBeforeTime(int day){
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -day);
    return FMT_DATE.format(cal.getTime());
  }
  /**
   * 获得过去和未来的日期
   * @return
   */
  public static String getBeforeAndAfterTime(int day, String formatStr) {
    SimpleDateFormat myFormatter = new SimpleDateFormat(formatStr);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, day);
    String time = myFormatter.format(cal.getTime());
    return time;
  }

  /**
   * 时间显示 **分钟前 **小时前
   */
  public static String changTimeType(String time) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String timeString = "";
    try {
      long millionSeconds = format.parse(time).getTime();// 毫秒
      Date date = new Date();
      long newSeconds = format.parse(format.format(date)).getTime();
      long subtraction = newSeconds - millionSeconds;
      long days = subtraction / (1000 * 60 * 60 * 24); // 获得天数
      long hours = (subtraction % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);// 获得余下的小时数
      long minutes = (subtraction % (1000 * 60 * 60)) / (1000 * 60); // 获得余下的分钟数
      long seconds = (subtraction % (1000 * 60)) / 1000; // 获得余下的秒数
      if (minutes > 5) {
        timeString = minutes + "分 前";
      } else {
        timeString = "刚刚";
      }
      if (hours > 0) {
        timeString = hours + "小时 前";
      }
      if (days > 0) {
        timeString = days + "天 前";
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return timeString;
  }

  /**
        *    判断现在时间是否在开始和结束时间之间
   * @param startTime
   * @param endTime
   */
  public static boolean isExpired(String startTime, String endTime) {
    Date starttime =  getDateByString(startTime+" 00:00:00","yyyy-MM-dd HH:mm:ss");
    Date endtime =  getDateByString(endTime+" 23:59:59","yyyy-MM-dd HH:mm:ss");
    Date nowTime = new Date();
    Calendar startCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    Calendar nowCal = Calendar.getInstance();
    startCal.setTime(starttime);
    endCal.setTime(endtime);
    nowCal.setTime(nowTime);
    boolean a = nowCal.after(startCal);
    boolean b = nowCal.before(endCal);
    if (a && b) {
      return true;
    } else {
      return false;
    }

  }
  //LocalDateTime --> Date
  public static Date asDate(LocalDateTime localDateTime) {
	  return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }
  //Date --> LocalDateTime
  public static LocalDateTime asLocalDateTime(Date date) {
	  return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
  
  
  /**
   * 判断指定时间是否在开始和结束时间之间
   * 
   * @param startTime
   * @param endTime
   */
  public static boolean isExpired(Date startTime, Date endTime, Date time) {

    // Date nowTime = new Date();
    Calendar startCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    Calendar timeCal = Calendar.getInstance();
    startCal.setTime(startTime);
    endCal.setTime(endTime);
    timeCal.setTime(time);
    if (startTime == time || endTime == time) {
      System.out.println("ff");
      return true;
    }
    boolean a = timeCal.before(startCal);
    boolean b = timeCal.after(endCal);
    if (!a && !b) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * 是否有效上班时间
   * 
   * @param startTime
   * @param endTime 9:30之前算
   */
  public static boolean isWorkTime(Date endTime, Date time) {

    // Date nowTime = new Date();
    // Calendar startCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    Calendar timeCal = Calendar.getInstance();
    // startCal.setTime(startTime);
    endCal.setTime(endTime);
    timeCal.setTime(time);
    // boolean a = timeCal.after(startCal);
    boolean b = timeCal.before(endCal);
    return b;

  }

  /**
   * 是否是有效下班时间
   * 
   * @param startTime
   * @param endTime 17:30以后
   */
  public static boolean isLeaveTime(Date startTime, Date endTime, Date time) {

    // Date nowTime = new Date();
    Calendar startCal = Calendar.getInstance();
    Calendar endCal = Calendar.getInstance();
    Calendar timeCal = Calendar.getInstance();
    startCal.setTime(startTime);
    endCal.setTime(endTime);
    timeCal.setTime(time);
    // 是否在5:30之后
    boolean a = timeCal.after(startCal);
    boolean b = timeCal.after(endCal);
    if (a && b) {
      return true;
    } else {
      return false;
    }

  }


  /**
   * 格式化当前时间为年月
   * @param date
   * @param format
   * @return
   */
  public static String getYearAndMonth(Date date, String format) {

    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String dateStr = sdf.format(date);
    return dateStr;

  }
  /**
   * 字符串转成 Date
   * @param date
   * @param formt
   * @return
   */
  public static Date getDateByString(String date, String formt) {
    Date d = null;
    try {
      SimpleDateFormat f = new SimpleDateFormat(formt);
      d = (Date) f.parseObject(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return d;
  }

  /**
   * 时间格式 string 转字符
   * 
   * @param date
   * @param formt
   * @return
   */
  public static String getStringByString(String date, String formt) {
    String s = "";
    if (StringUtils.isNotEmpty(date)) {
      s = date.replace("/", "-");
    }
    return s;


  }

  /**
   * 获取年月日
   * 
   * @param date
   * @param formt
   * @return
   */
  public static String getDateYMS(String date, String formt) {
    String ymd = null;
    if (StringUtils.isNotEmpty(date)) {
      String[] times = date.split(" ");
      if (times.length == 2) {
        ymd = times[0];
      }
    }
    return ymd;
  }


  /**
   * 获取时分秒时间 Date
   * 
   * @param time 上班时间string 类型
   * @return
   */
  public static Date getHHMM(String time) {
    String hms = "";
    Date d = null;
    String[] times = time.split(" ");
    if (times.length == 2) {
      hms = times[1];
      try {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        d = (Date) f.parseObject(hms);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return d;

    // return hms;
  }


  /**
   * 获取时分秒时间 Date
   * 
   * @param time 上班时间string 类型
   * @return
   */
  public static Date getHHMMSS(String time) {
    String hms = "";
    Date d = null;
    String[] times = time.split(" ");
    if (times.length == 2) {
      hms = times[1];
      try {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        // d = (Date) f.parseObject(hms);
        d = DateUtil.getDateByString(hms, "HH:mm:ss");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return d;

    // return hms;
  }

  /**
   * 获取有效下班时间
   * 
   * @return startTime 早上上班时间
   */
  public static Date getLeaveTime(Date startTime) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(startTime);
    cal.add(Calendar.HOUR, 9);
    return cal.getTime();

  }

  /**
   * 获取当月最大天数
   * 
   * @return
   */
  public static int getDayOfMonth() {
    Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
    int day = aCalendar.getActualMaximum(Calendar.DATE);
    return day;
  }


  /**
   * 循环某月日期列表
   * 
   * @param time (2014-12)
   * @return
   */
  public static List<String> getDatesofMonth(String time) {
    List<String> list = new ArrayList<String>();
    GregorianCalendar g = new GregorianCalendar();
    g.setTime(DateUtil.getDateByString(time, "yyyy-MM"));
    // 设置为此年此月的1号
    g.set(Calendar.DAY_OF_MONTH, 1);
    // 获取此月日期 号
    int day = g.get(Calendar.DAY_OF_YEAR);
    // 年
    int year = g.get(Calendar.YEAR);
    // 获取这定月份一共多少天
    int maxDate = DateUtil.getDates(time);
    for (int i = 0; i < maxDate; i++) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      // 获取去年月日 信息 2014-12-01
      String formatTime = sdf.format(g.getTime());
//      System.out.println("formatTime--" + formatTime);
      if (g.get(Calendar.YEAR) > year) {
        day = 0;
        // day = 1; 0和1 结果一样
        year = g.get(Calendar.YEAR);
      }
      g.set(Calendar.DAY_OF_YEAR, day + 1);
      day++;
      list.add(formatTime);
    }
    return list;
  }

  /**
   * 获取两个时间段 日期遍历
   * @param s 起始时间
   * @param e 结束时间
   */
  public static List<String> getDateList(String s, String e) {
    List<String> list = new ArrayList<String>();
    try {
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
      Date sd = (Date) f.parseObject(s);
      Date ed = (Date) f.parseObject(e);
      Calendar start = Calendar.getInstance();
      start.setTime(sd);
      Long startTIme = start.getTimeInMillis();

      Calendar end = Calendar.getInstance();
      end.setTime(ed);
      Long endTime = end.getTimeInMillis();

      Long oneDay = 1000 * 60 * 60 * 24l;

      Long time = startTIme;
      while (time < endTime) {
        Date d = new Date(time);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        time += oneDay;
        list.add(df.format(d));
      }
    } catch (Exception e2) {
      // TODO: handle exception
    }
    return list;
  }

  /**
   * 获取某年某月总共天数
   * 
   * @param time
   * @return
   */
  public static int getDates(String time) {
    int j = 0;
    String[] times = time.split("-");
    if (times.length == 2) {
      String year = times[0];
      String month = times[1];
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, Integer.parseInt(year));
      cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
      cal.set(Calendar.DATE, 1);
      cal.add(Calendar.MONTH, 1);
      cal.add(Calendar.DATE, -1);
      j = cal.get(Calendar.DAY_OF_MONTH);

    }
    return j;
  }


  /**
   * 计算两个时间差值 秒单位
   * 
   * @param start_time
   * @param end_time
   * @return
   */
  public static Long getPeriodS(Date start_time, Date end_time) {
    // String start_work_time1 = "2014-12-30 09:32:00";
    // Date start_work_time = getDateByString(start_work_time1, "yyyy-MM-dd HH:mm:dd");
    // start_time = Constant.END_MORNING_WORK_TIME;
    // end_time = DateUtil.getHHMMSS(getYearAndMonth(start_work_time, "yyyy-MM-dd HH:mm:ss")
    // );//上午上班打卡时间 hms
    long between = (end_time.getTime() - start_time.getTime()) / 1000;
    return between / 60;

    // return 1l;

  }

  /**
   * 计算两时间间隔 年为单位
   * 
   * @param start_time
   * @param flg 0 为上一年1 为当年
   * @return
   */
  public static int getPeriodY(String start_time, String flg) {
    Calendar ca = Calendar.getInstance();
    int now_year = 0;
    // 上一年
    if ("0".equals(flg)) {
      // ca.setTime(new Date());
      // 上一年
      now_year = getBeforYear();
    } else {
      ca.setTime(new Date());
      now_year = ca.get(Calendar.YEAR);
    }
    Date d = getDateByString(start_time, "yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    int old_year = cal.get(Calendar.YEAR);
    int bet = now_year - old_year;
    return bet;

    // return 1l;

  }

  /**
   * 计算两个时间差值 小时为单位
   * 
   * @param start_time
   * @param end_time
   * @return
   */
  public static double getPeriodH(Date start_time, Date end_time) {
    // Date s_time = getDateByString(start_time,"yyyy-MM-dd HH:mm:ss");
    // Date e_time = getDateByString(end_time,"yyyy-MM-dd HH:mm:ss");
    long between = (end_time.getTime() - start_time.getTime()) / 1000;
    long nh = 60 * 60;// 一小时的毫秒数
    long nm = 60;// 一分钟的毫秒数
    long min = between % 60;// 计算差多少分钟
    System.out.println(between / (60 * 60) + ":" + between % nh / nm);
    System.out.println("==" + ((double) between % nh / nm / 60));
    return between / (60 * 60) + ((double) between % nh / nm / 60);
  }

  // public static long getPeriodH(Date start_time ,Date end_time){
  // long between = (end_time.getTime()-start_time.getTime())/1000;
  // return between/(60*60);
  // }
  /**
   * 获取上月份
   * 
   * @return
   */
  public static String getBeforMonth() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, -1);
    System.out.println("上个月是：" + new SimpleDateFormat("yyyy年MM月").format(c.getTime()));
    return new SimpleDateFormat("yyyy-MM").format(c.getTime());
  }
  
  public static String getStartTime(){
    SimpleDateFormat format = new SimpleDateFormat(FMT_DATE_MONTH);
    Calendar c = Calendar.getInstance();
    String firstDay = format.format(c.getTime())+"-01";
    String nowTime =  FMT_DATE.format(c.getTime());
    if(firstDay.equalsIgnoreCase(nowTime)){
      c.add(Calendar.MONTH, -1);
      c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
      firstDay = FMT_DATE.format(c.getTime());
    }
    return firstDay ;
  }
  /**
   * 获取上一年
   * @return
   */
  public static int getBeforYear() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.YEAR, -1);
    int year = c.get(Calendar.YEAR);
    return year;
  }
  /**
         *   获取下一年
   * @return
   */
  public static int getNextYear() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.YEAR, 1);
    int year = c.get(Calendar.YEAR);
    return year;
  }
  public static int getNowYear() {
	    Calendar c = Calendar.getInstance();
	    int year = c.get(Calendar.YEAR);
	    return year;
	  }
  /**
   * 获取当前月份 从0开始 要加1
   * @return
   */
  public static int getNowMonth() {
    Calendar c = Calendar.getInstance();
    return c.get(Calendar.MONTH) + 1;
  }


  /**
   * 获取当月份
   * @return
   */
  public static int getMonth(String timestr) {
    Date time = getDateByString(timestr, "yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(time);
    int month = c.get(Calendar.MONTH);
    //
    return month + 1;
  }

  /*
   ** 
   * 计算两个日期之间相差的天数
   * 
   * @param smdate 较小的时间
   * 
   * @param bdate 较大的时间
   * 
   * @return 相差天数
   * 
   * @throws ParseException
   */
  public static int daysBetween(Date smdate, Date bdate) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      smdate = sdf.parse(sdf.format(smdate));
      bdate = sdf.parse(sdf.format(bdate));
      Calendar cal = Calendar.getInstance();
      cal.setTime(smdate);
      long time1 = cal.getTimeInMillis();
      cal.setTime(bdate);
      long time2 = cal.getTimeInMillis();
      long between_days = (time2 - time1) / (1000 * 3600 * 24);
      return Integer.parseInt(String.valueOf(between_days));

    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 字符串的日期格式的计算
   */
  public static int daysBetween(String smdate, String bdate) {
    try {
      if (smdate.equals(bdate)) {
        return 1;
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
      cal.setTime(sdf.parse(smdate));
      long time1 = cal.getTimeInMillis();
      cal.setTime(sdf.parse(bdate));
      long time2 = cal.getTimeInMillis();
      long between_days = (time2 - time1) / (1000 * 3600 * 24);
      return Integer.parseInt(String.valueOf(between_days));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 字符串的日期格式的计算 比如 2014-12-12 至2014-12-15
   */
  public static int daysBetweenTime(String smdate, String bdate) {

    try {
      /*
       * int result = 0;
       * 
       * 
       * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Calendar c1 =
       * Calendar.getInstance(); Calendar c2 = Calendar.getInstance();
       * 
       * c1.setTime(sdf.parse(smdate)); c2.setTime(sdf.parse(bdate));
       * 
       * result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
       * 
       * return result == 0 ? 1 : Math.abs(result);
       */
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();

      c1.setTime(sdf.parse(smdate));
      c2.setTime(sdf.parse(bdate));
      int i_c1 = c1.get(Calendar.DAY_OF_YEAR);
      int i_c2 = c2.get(Calendar.DAY_OF_YEAR);
      System.out.println("c1--" + i_c1 + " c2-" + i_c2);
      // if(i_c1 == i_c2){
      // return 1;
      // }
      Calendar cal = Calendar.getInstance();
      cal.setTime(sdf.parse(smdate));
      long time1 = cal.getTimeInMillis();
      cal.setTime(sdf.parse(bdate));
      long time2 = cal.getTimeInMillis();
      long between_days = (time2 - time1) / (1000 * 3600 * 24);
      int i = Integer.parseInt(String.valueOf(between_days));
      System.out.println("c1--" + i_c1 + " c2-" + i_c2 + "  i=" + i + "between_days==" + between_days);
      i = (int) Math.ceil(i);
      return i + 1;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      return -1;
    }
  }

  /**
   * 根据开始时间和结束时间 字符串的日期格式的计算 时间间隔的日期列表
   */
  public static List<String> daysBetweenList(String smdate, String bdate) {

    try {
      int maxDate = daysBetweenTime(smdate, bdate);

      System.out.println("maxDate 说是--" + maxDate);
      // String time = "2014-12-29";
      List<String> list = new ArrayList<String>();
      GregorianCalendar g = new GregorianCalendar();
      g.setTime(DateUtil.getDateByString(smdate, "yyyy-MM-dd"));
      // 设置为此年此月的1号
      // g.set(Calendar.DAY_OF_MONTH, 1);
      // 获取此月日期 号
      int day = g.get(Calendar.DAY_OF_YEAR);
      // System.out.println("几号 day="+day);
      // 年
      int year = g.get(Calendar.YEAR);
      // 获取这定月份一共多少天
      // int maxDate = DateUtil.getDates(time);
      // int maxDate = 4;
      for (int i = 0; i < maxDate; i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取去年月日 信息 2014-12-01
        String formatTime = sdf.format(g.getTime());
        System.out.println("formatTime--" + formatTime);

        if (g.get(Calendar.YEAR) > year) {
          day = 1;
          year = g.get(Calendar.YEAR);
        }
        g.set(Calendar.DAY_OF_YEAR, day + 1);
        day++;
        list.add(formatTime);
      }
      return list;

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      return null;
    }
  }

  public static String get24(String time) {
    if (StringUtils.isNotEmpty(time)) {
      int hi = 0;
      StringBuffer sb = new StringBuffer();
      String[] strs = time.split(":");
      if (strs.length > 0) {
        String h = strs[0];
        hi = Integer.valueOf(h);
        System.out.println("hi--" + hi);
        if (hi < 12) {
          hi = hi + 12;
          for (int i = 1; i < strs.length; i++) {
            sb.append(":");
            sb.append(strs[i]);
          }
          time = hi + sb.toString();
          System.out.println("time --" + time);
        }
      }
    }
    return time;
  }

  // 输入日期取星期几的方法
  public static String getWeekDay(String DateStr) {
    SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");// formatYMD表示的是yyyy-MM-dd格式
    SimpleDateFormat formatD = new SimpleDateFormat("E");// "E"表示"day in week"
    Date d = null;
    String weekDay = "";
    try {
      d = formatYMD.parse(DateStr);// 将String 转换为符合格式的日期
      weekDay = formatD.format(d);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return weekDay;
  }

  // 时间戳转化为日期
  public static String getDate(String unixDate) throws Exception {
    String date = null;
    long unixLong = 0;
    try {
      unixLong = Long.parseLong(unixDate);
      date = getDate(unixLong);
    } catch (Exception ex) {
      throw ex;
      // System.out.println("String转换Long错误，请确认数据可以转换！")
    }
    return date;
  }

  public static String getDate(Long unixLong) {
    SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = null;
    try {
      date = fm2.format(new Date(unixLong * 1000));
    } catch (Exception ex) {
      // System.out.println("日期转换错误！");
    }
    return date;
  }

  // 将字符串转为时间戳
  public static String getTime(String user_time) {
    String re_time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d;
    try {
      d = sdf.parse(user_time);
      long l = d.getTime();
      String str = String.valueOf(l);
      re_time = str.substring(0, 10);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return re_time;
  }

  // 昨天
  public static String getYesterday() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -1);
    // cal1.set(2000,1,29);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String yesterday = sdf.format(cal1.getTime());
    return yesterday;
  }

  // 前7天计算
  public static String getQian7() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -7);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String sevenDaysBefore = sdf.format(cal1.getTime());
    return sevenDaysBefore;
  }

  // 前30天计算
  public static String getQian30() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -30);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String qian30 = sdf.format(cal1.getTime());
    return qian30;
  }

  // 上月计算
  public static String getNextMonth() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.MONTH, -1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String nextMonth = sdf.format(cal1.getTime());
    return nextMonth;
  }

  // 上周计算
  public static String[] getNextWeek() {
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
    int offset1 = 1 - dayOfWeek;
    int offset2 = 7 - dayOfWeek;
    calendar1.add(Calendar.DATE, offset1 - 7);
    calendar2.add(Calendar.DATE, offset2 - 7);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String[] nextWeek = {sdf.format(calendar1.getTime()), sdf.format(calendar2.getTime())};
    return nextWeek;
  }

  // 获取当前日期的时间戳
  public static String getUnixLong(String user_time) {
    String re_time = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date d;
    try {
      d = sdf.parse(user_time);
      long l = d.getTime();
      String str = String.valueOf(l);
      re_time = str.substring(0, 10);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return re_time;
  }

  /**
   * 获取昨天的两个时间戳
   */
  public static int[] getYesterdayUnixLong() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String yesterday = sdf.format(cal1.getTime());
    String unixLong = getUnixLong(yesterday);
    String unixLong2 = getUnixLong(sdf.format(new Date()));
    int[] yesterdaySz = {Integer.parseInt(unixLong), Integer.parseInt(unixLong2)};
    return yesterdaySz;
  }

  /**
   * 获取前七天的两个时间戳
   */
  public static int[] getQian7UnixLong() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -7);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String sevenDaysBefore = sdf.format(cal1.getTime());
    String unixLong = getUnixLong(sevenDaysBefore);
    String unixLong2 = getUnixLong(sdf.format(new Date()));
    int[] getQian7UnixLong = {Integer.parseInt(unixLong), Integer.parseInt(unixLong2)};
    return getQian7UnixLong;
  }

  /**
   * 获取前三十天的两个时间戳
   */
  public static int[] getQian30UnixLong() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.DATE, -30);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String qian30 = sdf.format(cal1.getTime());
    String unixLong = getUnixLong(qian30);
    String unixLong2 = getUnixLong(sdf.format(new Date()));
    int[] getQian7UnixLong = {Integer.parseInt(unixLong), Integer.parseInt(unixLong2)};
    return getQian7UnixLong;
  }

  /**
   * 获取上月计算的两个时间戳
   */
  public static int[] getNextMonthUnixLong() {
    Calendar cal1 = Calendar.getInstance();
    cal1.add(Calendar.MONTH, -1);
    cal1.set(Calendar.DAY_OF_MONTH, cal1.getActualMinimum(Calendar.DAY_OF_MONTH));
    Calendar cal2 = Calendar.getInstance();
    cal2.add(Calendar.MONTH, -1);
    cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String nextMonth = sdf.format(cal1.getTime());
    String unixLong = getUnixLong(nextMonth);
    String unixLong2 = getUnixLong(sdf.format(cal2.getTime()));
    int[] getQian7UnixLong = {Integer.parseInt(unixLong), Integer.parseInt(unixLong2)};
    return getQian7UnixLong;
  }


  /**
   * 获取上周计算的两个时间戳
   */
  public static int[] getNextWeekUnixLong() {
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
    int offset1 = 1 - dayOfWeek;
    int offset2 = 7 - dayOfWeek;
    calendar1.add(Calendar.DATE, offset1 - 7);
    calendar2.add(Calendar.DATE, offset2 - 7);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String unixLong = getUnixLong(sdf.format(calendar1.getTime()));
    String unixLong2 = getUnixLong(sdf.format(calendar2.getTime()));

    int[] getQian7UnixLong = {Integer.parseInt(unixLong), Integer.parseInt(unixLong2)};
    return getQian7UnixLong;
  }
  /**
   * 字符串转换成日期
   * @param str
   * @return date
   */
  public static Date StrToDate(String str) {
    Date date = null;
    try {
      date = FMT_DATETIME.parse(str);
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
    return date;
  }
  /**    
   * plusDays(当前日期加n天)    
   */
  public static Date plusDays(int days){
      DateTime dateTime = new DateTime(new Date());
      String ex = dateTime.plusDays(days).toString("yyyyMMdd");// 让日加周期
      DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
      Date expire = format.parseDateTime(ex).toDate();
      return expire;
  }
  
  /**    
   * reduceDays(当前日期减n天)    
   */
  public static Date reduceDays(int days){
      DateTime dateTime = new DateTime(new Date());
      String ex = dateTime.minusDays(days).toString("yyyy-MM-dd");// 让日加周期
      DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
      Date expire = format.parseDateTime(ex).toDate();
      return expire;
  }
  public static String reduceDayString(int days){
    DateTime dateTime = new DateTime(new Date());
    String expire = dateTime.minusDays(days).toString("yyyy-MM-dd");
    return expire;
  }
  
  public static String plusHours(int hours,Date date){
    DateTime dateTime = new DateTime(date);
    String expire = dateTime.plusHours(hours).toString("yyyy-MM-dd'T'HH:mm:ss");
    return expire;
  }
  public static String plusDate(Date date){
    DateTime dateTime = new DateTime(date);
    String expire = dateTime.toString("yyyy-MM-dd'T'HH:mm:ss");
    return expire;
  }    
  /**    
   * plusHours(当前日期加n小时)    
   */
  public static Date plusHours(int hours){
      DateTime dateTime = new DateTime(new Date());
      String ex =dateTime.plusHours(hours).toString("yyyyMMddHH");
      DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHH");
      Date expire = format.parseDateTime(ex).toDate();
      return expire;
  }
  /**    
   * plusHours(当前日期减n小时)    
   */
  public static Date minusHours(int hours){
      DateTime dateTime = new DateTime(new Date());
      String ex =dateTime.minusHours(hours).toString("yyyy-MM-dd HH:mm:ss");
      DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
      Date expire = format.parseDateTime(ex).toDate();
      return expire;
  }
  /**
   * 时间字符串比较方法
   * @param DATE1
   * @param DATE2
   * @return
   */
  public static boolean compare_date(String DATE1, String DATE2) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
        Date bt = df.parse(DATE1);
        Date et = df.parse(DATE2);
        if (bt.before(et)) {//表示bt小于et 
            return true;
        }
    } catch (Exception exception) {
        exception.printStackTrace();
    }
    return false;
  }   
  
 /** 
  * 日期格式字符串转换成时间戳 
  * @param date 字符串日期 
  * @param format 如：yyyy-MM-dd HH:mm:ss 
  * @return 
  */  
  public static String dateToTimeStamp(Date date_str){  
       try {  
         return String.valueOf(date_str.getTime()/1000);  
       }catch (Exception e) {  
           e.printStackTrace();  
       }  
      return "";  
  }
  /**
   * 比较当前时间是否大于指定日期
   * @return 
   */
  public static boolean dateCompareNow(){
    Calendar cal=Calendar.getInstance();    
    int d=cal.get(Calendar.DATE); 
    System.out.println(d);
    boolean flag = d > 22 ? true :false ;
    return flag ;
  }
  /**
   * 获取当前季度的时间范围
   * @return current quarter
   */
  public static String getThisQuarter() {
      Calendar startCalendar = Calendar.getInstance();
      startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
      startCalendar.set(Calendar.DAY_OF_MONTH, 1);
      setMinTime(startCalendar);
      
      Calendar endCalendar = Calendar.getInstance();
      endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
      endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
      setMaxTime(endCalendar);
      return "";
  }
  private static void setMinTime(Calendar calendar){
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    System.out.println("开始时间"+FMT_DATETIME.format(calendar.getTime()));
  }
  
  private static void setMaxTime(Calendar calendar){
      calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
      calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
      calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
      calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
      System.out.println("结束时间"+FMT_DATETIME.format(calendar.getTime()));
  }

  public static String getStartQuarterDate(String year,String quarter){
    String quarterStr = "" ;
    if(StringUtils.isNoneBlank(quarter)){
      switch(quarter){
        case "Q1" : quarterStr = year+"-01" ; break ;
        case "Q2" : quarterStr = year+"-04" ; break ;
        case "Q3" : quarterStr = year+"-07" ; break ;
        case "Q4" : quarterStr = year+"-10" ; break ;
      }
    }
    return quarterStr ;
  }
  public static String getEndQuarterDate(String year,String quarter){
    String quarterStr = "" ;
    if(StringUtils.isNoneBlank(quarter)){
      switch(quarter){
        case "Q1" : quarterStr = year+"-03" ; break ;
        case "Q2" : quarterStr = year+"-06" ; break ;
        case "Q3" : quarterStr = year+"-09" ; break ;
        case "Q4" : quarterStr = year+"-12"+quarter ; break ;
      }
    }
    return quarterStr ;
  }  
  public static void main(String[] args) throws Exception {
    //System.out.println(DateUtil.getStartTime());
    //System.out.println(DateUtil.reduceDayString(7));
//    DateUtil.dateCompareNow();
//    String str = "2019-03";
//    System.out.println(str.substring(0, str.lastIndexOf("-")));
//    DateUtil.getThisQuarter();
//    System.out.println(DateUtil.minusHours(8).getTime()/1000);
//    System.out.println(dateToTimeStamp(DateUtil.getGMT8Time()));
//    System.out.println(DateUtil.isExpired("2021-04-06","2021-04-06"));
//	  System.out.println(DateUtil.asLocalDateTime(DateUtil.getDateByString("2021-04-09", "yyyy-MM-dd")));
	  System.out.println(DateUtil.getNextYear());
	  System.out.println(DateUtil.getNowYear());
  }
}
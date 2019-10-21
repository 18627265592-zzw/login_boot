package com.eastday.demo.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

// http://www.ofyu.com/index.php/8-java
public class DateUtils {
    public final static String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public final static SimpleDateFormat sDateTimeFormat = new SimpleDateFormat(DateTimeFormat);
    public final static SimpleDateFormat sTimeFormat = new SimpleDateFormat("HH:mm:ss");
    public final static SimpleDateFormat hhformat = new SimpleDateFormat("HH");
    public final static SimpleDateFormat sTimeWeekFormat = new SimpleDateFormat("EEEE");
    public final static SimpleDateFormat hmFormat = new SimpleDateFormat("HH:mm");
    public final static SimpleDateFormat mdFormat = new SimpleDateFormat("MM-dd");
    public final static SimpleDateFormat msFormat = new SimpleDateFormat("mm:ss");
    public final static SimpleDateFormat ymFormat = new SimpleDateFormat("yyyy-MM");
    public final static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
    public final static SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat wholeDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat sDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public final static SimpleDateFormat sDateFormat4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sDateFormat5 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat yFormat = new SimpleDateFormat("yyyy");
    public final static SimpleDateFormat MFormat = new SimpleDateFormat("MM");
    public final static SimpleDateFormat dFormat = new SimpleDateFormat("dd");
    public final static SimpleDateFormat hFormat = new SimpleDateFormat("hh");
    public final static SimpleDateFormat mFormat = new SimpleDateFormat("mm");
    public final static SimpleDateFormat sFormat = new SimpleDateFormat("ss");

    public final static String DEFAULT_TIMEZONE = "GMT+8";

    public static String getSimpleSeconds(Date date) {
        return sFormat.format(date);
    }

    public static String getSimpleMinutes(Date date) {
        return mFormat.format(date);
    }

    public static String getSimpleHour(Date date) {
        return hFormat.format(date);
    }

    public static String getSimpleDay(Date date) {
        return dFormat.format(date);
    }

    public static String getSimpleMonth(Date date) {
        return MFormat.format(date);
    }

    public static String getSimpleYear(Date date) {
        return yFormat.format(date);
    }

    public static String getSimpleYearMonth(Date date) {
        return ymFormat.format(date);
    }

    public static String getHour(Date date) {
        return hhformat.format(date);
    }

    public static String tohmFormat(Date date) {
        return hmFormat.format(date);
    }

    public static String tomsFormat(Date date) {
        return msFormat.format(date);
    }

    public static String timeWeek(Date date) {
        return sTimeWeekFormat.format(date);
    }

    public static Date toDateTime(long ms) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ms);
        return cal.getTime();
    }

    public static Date toDateTime(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateTimeFormat.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toDate(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateFormat.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toDateFormat(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateFormat2.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toDateFormat3(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateFormat3.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toDateFormat4(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateFormat4.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toDateFormat5(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return sDateFormat5.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static Date toFullDate(String dateString) {
        if (Strings.isNullOrEmpty(dateString))
            return null;

        try {
            return wholeDateFormat.parse(dateString);
        } catch (Exception pe) {
            return null;
        }
    }

    public static String nowString() {
        return sDateTimeFormat.format(new Date());
    }

    public static String nowString(SimpleDateFormat df) {
        return df.format(new Date());
    }

    public static String toDateString(Date date) {
        if (date == null)
            return null;
        else
            return sDateFormat.format(date);
    }

    public static String toSimpleDateString(Date date) {
        if (date == null)
            return null;
        else
            return sSimpleDateFormat.format(date);
    }

    public static String toWholeDateString(Date date) {
        if (date == null)
            return null;
        else
            return wholeDateFormat.format(date);
    }

    public static String toDateTimeString(Date date) {
        if (date == null)
            return null;
        else
            return sDateTimeFormat.format(date);
    }

    public static String toString(Date date, SimpleDateFormat df) {
        if (date == null)
            return null;
        else
            return df.format(date);
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int monthsBetween(Date dateStart, Date dateEnd) {
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(dateStart);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dateEnd);

        return monthsBetween(calStart, calEnd);
    }

    public static int monthsBetween(Calendar calStart, Calendar calEnd) {
        int sYear = calStart.get(Calendar.YEAR);
        int sMonth = calStart.get(Calendar.MONTH);

        int eYear = calEnd.get(Calendar.YEAR);
        int eMonth = calEnd.get(Calendar.MONTH);

        return ((eYear - sYear) * 12 + (eMonth - sMonth));
    }

    public static int daysBetween(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return daysBetween(cal1, cal2);
    }

    public static int daysBetween(Calendar cal1, Calendar cal2) {
        long time1 = cal1.getTimeInMillis();
        long time2 = cal2.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int minBetween(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long between_min = (time1 - time2) / (1000 * 60);

        return Integer.parseInt(String.valueOf(between_min));
    }

    public static String daysBetweenString(Date date1, Date date2) {
        int dayPast = DateUtils.daysBetween(date1, date2);
        if (dayPast == 0)
            return "今天";
        else if (dayPast < 0)
            return String.format("%d天前", -dayPast);
        else
            return String.format("%d天后", dayPast);
    }

    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Calendar getTodayCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Date getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Calendar getDay(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Date getTomorrow() {
        return addDay(1);
    }

    public static Date getTomorrow(Date date) {
        return addDay(date, 1);
    }

    public static Date getYesterday() {
        return addDay(-1);
    }

    public static Date getYesterday(Date date) {
        return addDay(date, -1);
    }

    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int addMinute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, addMinute);
        return cal.getTime();
    }

    public static Date addHour(Date date, int addHour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, addHour);
        return cal.getTime();
    }

    public static Date addDay(int addDay) {
        Calendar cal = getTodayCalendar();
        cal.add(Calendar.DATE, addDay);
        return cal.getTime();
    }

    public static Date addDay(Date date, Integer addDay) {
        if (date == null)
            return null;
        if (addDay == null)
            return date;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addDay);
        return cal.getTime();
    }

    public static Date addDay(Date date, int addDay) {
        if (date == null)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addDay);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int addMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, addMonth);
        return cal.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date getFirstDayOfHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        return getDay(cal).getTime();
    }

    public static List<Date> getDateStartEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date start = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);

        Date end = calendar.getTime();

        List<Date> list = Lists.newArrayList();
        list.add(start);
        list.add(end);
        return list;
    }

    public static Date getLastDayOfHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.HOUR_OF_DAY);
        cal.set(Calendar.HOUR_OF_DAY, value);
        return getDay(cal).getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.toDateTimeString(DateUtils.addDate(DateUtils.getLastDayOfHour(new Date()), 1)));
        System.out.println(DateUtils.toDateTimeString(DateUtils.addDate(DateUtils.getLastDayOfMonth(new Date()), 1)));
        System.out.println(DateUtils.toDateTimeString(DateUtils.addDate(DateUtils.getLastDayOfYear(new Date()), 1)));
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getDay(cal).getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return getDay(cal).getTime();
    }

    public static Date getLastDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, value);
        return getDay(cal).getTime();
    }

    public static Date getFirstDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        return getDay(cal).getTime();
    }

    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static Date addHalfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime();
    }

    /*
     * return (dateStart < dt < dateEnd)
     */
    public static boolean inDate(Date dt, Date dateStart, Date dateEnd) {
        return (DateUtils.compareDate(dateStart, dt) && DateUtils.compareDate(dt, dateEnd));
    }

    /*
     * return (date1 < date2)
     */
    public static boolean compareDate(Date date1, Date date2) {
        return (date1.getTime() < date2.getTime());
    }

    /*
     * 过了时间date return (now > date)
     */
    public static boolean compareNow(Date date) {
        return compareDate(date, new Date());
    }

    /*
     * "yyyy-MM-dd HH:mm:ss" return (dateString1 < dateString2)
     */
    public static boolean compareDate(String dateString1, String dateString2) {
        try {
            Date date1 = sDateTimeFormat.parse(dateString1);
            Date date2 = sDateTimeFormat.parse(dateString2);
            return DateUtils.compareDate(date1, date2);
        } catch (Exception e) {
            return false;
        }
    }

    public static Date addDateByWorkDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            for (int i = 0; i < day; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                if (checkHoliday(calendar)) {
                    i--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar.getTime();
    }

    public static boolean checkHoliday(Calendar calendar) throws Exception {
        // 判断日期是否是周六周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }
        // 判断日期是否是节假日
        /*
         * for (Calendar ca : holidayList) { if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) && ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) { return true; } }
         */
        return false;
    }

    public static Date toSimpleDate(String date) {
        if (Strings.isNullOrEmpty(date)) {
            return null;
        }
        try {
            return sSimpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 两个时间相差距几个小时
     */
    public static long getDistanceTimes(Date date1, Date date2) {
        long day = 0;
        long hour = 0;
        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24) + day * 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hour;
    }

    /**
     * 两个时间相差距多少分钟
     */
    public static long getDistanceMinTimes(Date date1, Date date2) {
        long min =0;
        try {
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            min = diff / (60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    /**
     * 加减某些天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDate(Date date, int amount) {
        if (date == null) {
            throw new NullPointerException();
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    public static long nowBetweenMid() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        return TimeUnit.NANOSECONDS.toSeconds(Duration.between(LocalDateTime.now(), tomorrowMidnight).toNanos());
    }

    private static String cron = "ss mm HH dd MM ? yyyy";

    // 时间点转cron
    public static String date2cron(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(cron);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    // cron转日志
    public static Date cron2date(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(cron);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     * 获取date上个月的第一天
     *
     * @return
     */
    public static Date getFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * date转获取精确到秒的时间戳
     *
     * @return
     */
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    /**
     * 精确到秒的时间戳转dateString
     *
     * @param seconds
     * @return
     */
    public static String getDateStringBySeconds(Long seconds) {
        Date date = new Date(seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str_date = format.format(date);
        return str_date;
    }

    public static Date formatDate(String dateString) {
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
        Date date = null;
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
        }
        return date;
    }

    public static Date formatUsDate(String dateString) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
        Date date = null;
        try {
            date = bartDateFormat.parse(dateString);
        } catch (Exception e) {
        }

        return date;
    }

    public static List<Date> getFirstAndLastOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        Date start = cal.getTime();
        cal.add(Calendar.DAY_OF_WEEK, 7);
        cal.add(Calendar.SECOND, -1);
        // 所在周结束日期
        Date end = cal.getTime();

        List<Date> list = Lists.newArrayList();
        list.add(start);
        list.add(end);
        return list;
    }

    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

}

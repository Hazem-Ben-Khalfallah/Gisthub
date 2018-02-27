package com.blacknebula.gisthub.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public final class DateUtils {

    /**
     * Returns a new Date without time information given date
     *
     * @param date : date for which one process is done
     * @return date with time information set to noon
     * @should throw an exception if date is null
     * @should keep same date but set time to noon
     */
    public static Date dateWithoutTime(Date date) {
        Objects.requireNonNull(date, "date should not be null");
        GregorianCalendar day = new GregorianCalendar();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 12);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }

    /**
     * add days to a date
     *
     * @param date an instance of a date
     * @param days number of days to add
     * @return an instance of a date with the days added
     * @should throw an exception if date is null
     * @should return next day
     * @should return previous day
     */
    public static Date addDays(Date date, int days) {
        Objects.requireNonNull(date, "date should not be null");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * add seconds to a date
     *
     * @param date  an instance of a date
     * @param hours number of hours to add
     * @return an instance of a date with the hours added
     * @should throw an exception if date is null
     * @should return next hour
     * @should return previous previous
     */
    public static Date addHours(Date date, int hours) {
        Objects.requireNonNull(date, "date should not be null");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

    /**
     * add seconds to a date
     *
     * @param date    an instance of a date
     * @param minutes number of minutes to add
     * @return an instance of a date with the minutes added
     * @should throw an exception if date is null
     * @should return next minute
     * @should return previous minute
     */
    public static Date addMinutes(Date date, int minutes) {
        Objects.requireNonNull(date, "date should not be null");
        final Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MINUTE, minutes);
        return now.getTime();
    }

    /**
     * add seconds to a date
     *
     * @param date    an instance of a date
     * @param seconds number of seconds to set
     * @return an instance of a date with the seconds added
     * @should throw an exception if date is null
     * @should return next second
     * @should return previous second
     */
    public static Date addSeconds(Date date, int seconds) {
        Objects.requireNonNull(date, "date should not be null");
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        return c.getTime();
    }

}

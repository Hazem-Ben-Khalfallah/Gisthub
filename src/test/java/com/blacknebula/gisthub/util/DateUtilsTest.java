package com.blacknebula.gisthub.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author hazem
 */
public class DateUtilsTest {
    /**
     * @verifies throw an exception if date is null
     * @see DateUtils#dateWithoutTime(Date)
     */
    @Test
    public void dateWithoutTime_shouldThrowAnExceptionIfDateIsNull() throws Exception {
        try {
            // when
            DateUtils.dateWithoutTime(null);
            Assertions.fail("should throw a nullPointerException");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("date should not be null");
        }
    }

    /**
     * @verifies keep same date but set time to noon
     * @see DateUtils#dateWithoutTime(Date)
     */
    @Test
    public void dateWithoutTime_shouldKeepSameDateButSetTimeToNoon() throws Exception {
        // given
        final Date date = getDate(2017, 12, 29, 15, 30, 23);
        // when
        final Date result = DateUtils.dateWithoutTime(date);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(12);
        Assertions.assertThat(result).hasMinute(0);
        Assertions.assertThat(result).hasSecond(0);
    }

    /**
     * @verifies throw an exception if date is null
     * @see DateUtils#addDays(Date, int)
     */
    @Test
    public void addDays_shouldThrowAnExceptionIfDateIsNull() throws Exception {
        try {
            // when
            DateUtils.addDays(null, 1);
            Assertions.fail("should throw a nullPointerException");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("date should not be null");
        }
    }

    /**
     * @verifies return next day
     * @see DateUtils#addDays(Date, int)
     */
    @Test
    public void addDays_shouldReturnNextDay() throws Exception {
        // given
        final int value = 28;
        final Date date = getDate(2017, 12, value, 15, 30, 23);
        // when
        final Date result = DateUtils.addDays(date, 1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(value + 1);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies return previous day
     * @see DateUtils#addDays(Date, int)
     */
    @Test
    public void addDays_shouldReturnPreviousDay() throws Exception {
        // given
        final int value = 28;
        final Date date = getDate(2017, 12, value, 15, 30, 23);
        // when
        final Date result = DateUtils.addDays(date, -1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(value - 1);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies throw an exception if date is null
     * @see DateUtils#addHours(Date, int)
     */
    @Test
    public void addHours_shouldThrowAnExceptionIfDateIsNull() throws Exception {
        try {
            // when
            DateUtils.addHours(null, 1);
            Assertions.fail("should throw a nullPointerException");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("date should not be null");
        }
    }

    /**
     * @verifies return next hour
     * @see DateUtils#addHours(Date, int)
     */
    @Test
    public void addHours_shouldReturnNextHour() throws Exception {
        // given
        final int value = 15;
        final Date date = getDate(2017, 12, 29, value, 30, 23);
        // when
        final Date result = DateUtils.addHours(date, 1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(value + 1);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies return previous previous
     * @see DateUtils#addHours(Date, int)
     */
    @Test
    public void addHours_shouldReturnPreviousPrevious() throws Exception {
        // given
        final int value = 15;
        final Date date = getDate(2017, 12, 29, value, 30, 23);
        // when
        final Date result = DateUtils.addHours(date, -1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(value - 1);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies throw an exception if date is null
     * @see DateUtils#addMinutes(Date, int)
     */
    @Test
    public void addMinutes_shouldThrowAnExceptionIfDateIsNull() throws Exception {
        try {
            // when
            DateUtils.addMinutes(null, 1);
            Assertions.fail("should throw a nullPointerException");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("date should not be null");
        }
    }

    /**
     * @verifies return next minute
     * @see DateUtils#addMinutes(Date, int)
     */
    @Test
    public void addMinutes_shouldReturnNextMinute() throws Exception {
        // given
        final int value = 30;
        final Date date = getDate(2017, 12, 29, 15, value, 23);
        // when
        final Date result = DateUtils.addMinutes(date, 1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(value + 1);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies return previous minute
     * @see DateUtils#addMinutes(Date, int)
     */
    @Test
    public void addMinutes_shouldReturnPreviousMinute() throws Exception {
        // given
        final int value = 30;
        final Date date = getDate(2017, 12, 29, 15, value, 23);
        // when
        final Date result = DateUtils.addMinutes(date, -1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(value - 1);
        Assertions.assertThat(result).hasSecond(23);
    }

    /**
     * @verifies throw an exception if date is null
     * @see DateUtils#addSeconds(Date, int)
     */
    @Test
    public void addSeconds_shouldThrowAnExceptionIfDateIsNull() throws Exception {
        try {
            // when
            DateUtils.addSeconds(null, 1);
            Assertions.fail("should throw a nullPointerException");
        } catch (NullPointerException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("date should not be null");
        }
    }

    /**
     * @verifies return next second
     * @see DateUtils#addSeconds(Date, int)
     */
    @Test
    public void addSeconds_shouldReturnNextSecond() throws Exception {
        // given
        final int value = 23;
        final Date date = getDate(2017, 12, 29, 15, 30, value);
        // when
        final Date result = DateUtils.addSeconds(date, 1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(value + 1);
    }

    /**
     * @verifies return previous second
     * @see DateUtils#addSeconds(Date, int)
     */
    @Test
    public void addSeconds_shouldReturnPreviousSecond() throws Exception {
        // given
        final int value = 23;
        final Date date = getDate(2017, 12, 29, 15, 30, value);
        // when
        final Date result = DateUtils.addSeconds(date, -1);
        // then
        Assertions.assertThat(result).hasYear(2017);
        Assertions.assertThat(result).hasMonth(12);
        Assertions.assertThat(result).hasDayOfMonth(29);
        Assertions.assertThat(result).hasHourOfDay(15);
        Assertions.assertThat(result).hasMinute(30);
        Assertions.assertThat(result).hasSecond(value - 1);
    }

    private Date getDate(int year, int month, int day, int hours, int minutes, int seconds) {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}

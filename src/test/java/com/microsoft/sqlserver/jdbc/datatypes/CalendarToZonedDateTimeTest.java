/*
 * Microsoft JDBC Driver for SQL Server Copyright(c) Microsoft Corporation All rights reserved. This program is made
 * available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */

package com.microsoft.sqlserver.jdbc.datatypes;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.microsoft.sqlserver.testframework.AbstractTest;

import microsoft.sql.DateTimeOffset;


/*
 * This test is for testing the various conversions/functionalities for temporal datatypes using Calendar/ZoneDateTime.
 */
@RunWith(JUnitPlatform.class)
class CalendarToZonedDateTimeTest extends AbstractTest {

    /*
     * Changing the Timestamp's year to prior to 1884 makes the test fail.
     */
    @Test
    public void testConvertFromTimestamp() {
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf("1884-01-01 12:08:12.081");

        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setTimeZone(TimeZone.getTimeZone("America/Vancouver"));

        ZoneId zid = ZoneId.of("America/Vancouver");

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), ZoneId.of("America/Vancouver"));

        Timestamp t = new Timestamp(cal.getTimeInMillis());
        Timestamp t2 = Timestamp.from(zdt.toInstant());

        int correctOffsetSeconds = TimeZone.getTimeZone("America/Vancouver").getOffset(Calendar.ZONE_OFFSET) / 1000;

        // This logic is required to correctly counterbalance the offset that Instance has with the timezone offset,
        // in order to make ZonedDateTime's behavior identical to Calendar.
        if (zdt.getOffset().getTotalSeconds() != correctOffsetSeconds) {
            zdt = zdt.plusSeconds(correctOffsetSeconds - zdt.getOffset().getTotalSeconds());
        }

        assertEquals(t, t2);
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());
        assertEquals(cal.get(Calendar.YEAR), zdt.getYear());
        assertEquals(cal.get(Calendar.MONTH), zdt.getMonth().getValue() - 1); // Calendar month starts at 0
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.get(Calendar.HOUR_OF_DAY), zdt.getHour());
        assertEquals(cal.get(Calendar.MINUTE), zdt.getMinute());
        assertEquals(cal.get(Calendar.SECOND), zdt.getSecond());
        assertEquals(cal.get(Calendar.MILLISECOND), zdt.getNano() / 1000000);
        assertEquals(cal.get(Calendar.ZONE_OFFSET), zdt.getOffset().getTotalSeconds() * 1000);
    }

    @Test
    public void testConstructZonedDateTimeFromCalendar() {
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2019-09-10" + " " + "12:34:56");
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()),
                cal.getTimeZone().toZoneId());

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());
    }

    @Test
    public void testConvertFromAlternatedCalendarFields() {
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();

        // Modify individual fields from predetermined numbers (presumably passed in as parameter)
        int year = 2000;
        int month = 1;
        int date = 2;
        int hour = 3;
        int minute = 4;
        int second = 5;
        int millis = 278;
        int nanos = 278 * 1000000;
        String timezone = "Africa/Niamey";

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1); // Calendar month starts at 0
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millis);
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        ZonedDateTime zdt = ZonedDateTime.of(year, month, date, hour, minute, second, nanos, ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        assertEquals(cal.get(Calendar.YEAR), zdt.getYear());
        assertEquals(cal.get(Calendar.MONTH), zdt.getMonth().getValue() - 1); // Calendar month starts at 0
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.get(Calendar.DAY_OF_YEAR), zdt.getDayOfYear());
        assertEquals(cal.get(Calendar.HOUR_OF_DAY), zdt.getHour());
        assertEquals(cal.get(Calendar.MINUTE), zdt.getMinute());
        assertEquals(cal.get(Calendar.SECOND), zdt.getSecond());
        assertEquals(cal.get(Calendar.MILLISECOND), zdt.getNano() / 1000000);
        assertEquals(cal.get(Calendar.ZONE_OFFSET), zdt.getOffset().getTotalSeconds() * 1000);
        assertEquals(cal.get(Calendar.ZONE_OFFSET),
                ZoneId.of(timezone).getRules().getOffset(Instant.now()).getTotalSeconds() * 1000);
        assertEquals(cal.getActualMaximum(Calendar.DAY_OF_YEAR),
                zdt.with(TemporalAdjusters.lastDayOfYear()).getDayOfYear());
    }

    @Test
    public void testReturnAsString() {
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2019-09-10" + " " + "12:34:56");
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());

        TimeZone tz = new SimpleTimeZone(0, "GMT");
        ZoneId zid = ZoneId.of("GMT");

        int minutesOffset = 61;

        tz = new SimpleTimeZone(minutesOffset * 60 * 1000, "");
        zid = ZoneOffset.ofTotalSeconds(minutesOffset * 60);

        cal.setTimeZone(tz);

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(ts.getTime()), zid);

        String calString = String.format(Locale.US, "%1$tF %1$tT%2$s %3$c%4$02d:%5$02d", cal, "", '+', 1, 1);
        String zdtString = String.format(Locale.US, "%1$tF %1$tT%2$s %3$c%4$02d:%5$02d", zdt, "", '+', 1, 1);

        assertEquals(calString, zdtString);

        DateTimeOffset dto = microsoft.sql.DateTimeOffset.valueOf(ts, minutesOffset);

        assertEquals(dto.toString(), zdtString);
        assertEquals(dto.toString(), calString);
    }

    @Test
    public void testBuildFromDaysFromCE() {
        int daysSinceBaseDate = 735752;
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.set(1, Calendar.JANUARY, 1 + daysSinceBaseDate + 2, 0, 0, 0);

        ZonedDateTime zdt = ZonedDateTime.of(1, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
        zdt = zdt.plusDays(daysSinceBaseDate);

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());
    }

    @Test
    public void testGregorianCutoff() {
        // prior to Gregorian cutoff
        // Gregorian calendar
        String timezone = "Africa/Niamey";
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf("1066-10-14" + " " + "12:34:56");
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setGregorianChange(new java.util.Date(Long.MIN_VALUE));
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        ZonedDateTime zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        // in the middle of Gregorian cutoff
        ts = java.sql.Timestamp.valueOf("1582-10-10" + " " + "00:00:00");
        cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setGregorianChange(new java.util.Date(Long.MIN_VALUE));
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        // past Gregorian cutoff
        ts = java.sql.Timestamp.valueOf("1582-10-16" + " " + "00:00:00");
        cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setGregorianChange(new java.util.Date(Long.MIN_VALUE));
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        // Default Calendar (similar behavior to cal.setGregorianChange(new java.util.Date(Long.MAX_VALUE));)
        // prior to Gregorian cutoff
        ts = java.sql.Timestamp.valueOf("1066-10-14" + " " + "12:34:56");
        cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth() - 6);
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        // in the middle of Gregorian cutoff
        ts = java.sql.Timestamp.valueOf("1582-10-10" + " " + "00:00:00");
        cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        // past Gregorian cutoff
        ts = java.sql.Timestamp.valueOf("1582-10-16" + " " + "00:00:00");
        cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        cal.setTimeZone(TimeZone.getTimeZone(timezone));

        zdt = ZonedDateTime.ofInstant(ts.toInstant(), ZoneId.of(timezone));

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());
    }

    @Test
    public void testZoneId() {
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf("2019-09-10" + " " + "12:34:56");
        GregorianCalendar cal = new GregorianCalendar(Locale.US);
        cal.clear();
        cal.setTimeInMillis(ts.getTime());

        TimeZone tz = new SimpleTimeZone(0, "UTC");
        ZoneId zid = ZoneId.of("UTC");

        cal.setTimeZone(tz);

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()), zid);

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());
        assertEquals(cal.getTimeZone().toZoneId(), zdt.getZone());

        cal.clear();
        cal.setTimeInMillis(ts.getTime());
        int minutesOffset = 60;

        tz = new SimpleTimeZone(minutesOffset * 60 * 1000, "");
        zid = ZoneOffset.ofTotalSeconds(minutesOffset * 60);

        cal.setTimeZone(tz);

        zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()), zid);

        assertEquals(cal.toInstant(), zdt.toInstant());
        assertEquals(cal.getTimeInMillis(), zdt.toInstant().toEpochMilli());
        assertEquals(cal.get(Calendar.DATE), zdt.getDayOfMonth());

        tz = TimeZone.getDefault();
        zid = ZoneId.systemDefault();

        assertEquals(tz.getID(), zid.getId());
    }
}

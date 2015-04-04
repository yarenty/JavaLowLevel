package com.yarenty.date;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Thread safe date format.
 *
 * @author yarenty
 */
public class SafeSimpleDateFormat {
    private final String _format;
    private static final ThreadLocal<Map<String, SimpleDateFormat>> _dateFormats = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<String, SimpleDateFormat>();
        }
    };

    private SimpleDateFormat getDateFormat(final String format) {
        final Map<String, SimpleDateFormat> formatters = _dateFormats.get();
        SimpleDateFormat formatter = formatters.get(format);
        if (formatter == null) {
            formatter = new SimpleDateFormat(format);
            formatters.put(format, formatter);
        }
        return formatter;
    }

    public SafeSimpleDateFormat(final String format) {
        _format = format;
    }

    public String format(final Date date) {
        return getDateFormat(_format).format(date);
    }

    public String format(final Object date) {
        return getDateFormat(_format).format(date);
    }

    public Date parse(final String day) throws ParseException {
        return getDateFormat(_format).parse(day);
    }

    public void setTimeZone(final TimeZone tz) {
        getDateFormat(_format).setTimeZone(tz);
    }

    public void setCalendar(final Calendar cal) {
        getDateFormat(_format).setCalendar(cal);
    }

    public void setNumberFormat(final NumberFormat format) {
        getDateFormat(_format).setNumberFormat(format);
    }

    public void setLenient(final boolean lenient) {
        getDateFormat(_format).setLenient(lenient);
    }

    public void setDateFormatSymbols(final DateFormatSymbols symbols) {
        getDateFormat(_format).setDateFormatSymbols(symbols);
    }

    public void set2DigitYearStart(final Date date) {
        getDateFormat(_format).set2DigitYearStart(date);
    }
}
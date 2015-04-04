package com.yarenty.date;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Please feel free to experiment - not only wrong data but sometimes number format exceptions...
 * Examples:
 * <pre>
 * Exception in thread "Thread-4" Exception in thread "Thread-3" Exception in thread "Thread-2" Exception in thread "Thread-0" Exception in thread "Thread-13" java.lang.NumberFormatException: multiple points
 * at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:1890)
 * </pre>
 * <pre>
 * Exception in thread "Thread-2" java.lang.NumberFormatException: For input string: ""
 * at java.lanException in thread "Thread-5" java.lang.NumberFormatException: For input string: ".2007E4.2007E4"
 * at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:2043)
 * </pre>
 * <pre>
 * Exception in thread "Thread-0" Exception in thread "Thread-3" Exception in thread "Thread-1" java.lang.NumberFormatException: multiple points
 * at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:1890)
 * </pre>
 * <pre>
 * Exception in thread "Thread-4" Exception in thread "Thread-1" Exception in thread "Thread-9"
 * org.junit.ComparisonFailure: date conversion failed after 0 iterations. expected:<0[4-Apr-1998]> but was:<0[8-Jun-1996]>
 * at org.junit.Assert.assertEquals(Assert.java:115)
 * at com.yarenty.date.SimpleDateTest$1.run(SimpleDateTest.java:56)
 * </pre>
 */
public class SimpleDateTest {

    static final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    static final SafeSimpleDateFormat sdf = new SafeSimpleDateFormat("dd-MMM-yyyy");
    static final String testDate[] = {"01-Jan-1999", "14-Feb-2001", "31-Dec-2007",
            "03-Aug-1993", "15-Mar-2002", "31-Mar-2017", "08-Jun-1996", "14-Feb-2003", "24-Dec-2012",
            "04-Apr-1998", "17-Jun-2004", "30-Nov-2003", "06-Jul-1995", "14-Feb-2005", "29-Dec-2011"};

    /**
     * Test method for SDF.
     */
    @Test
    public void testForErrorsInSimpleDateFormat() {
        final Runnable r[] = new Runnable[testDate.length];
        for (int i = 0; i < r.length; i++) {

            final int i2 = i;

            r[i] = new Runnable() {
                public void run() {
                    try {
                        for (int j = 0; j < 10000000; j++) { // adjust this depending on your processor speed
                            final String str = testDate[i2];
                            String str2;
// synchronized(df)
                            {
                                final Date d = df.parse(str);
                                str2 = df.format(d);
                            }

                            Assert.assertEquals("date conversion failed after " + j + " iterations.", str, str2);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("parse failed");
                    }
                }
            };
            new Thread(r[i]).start();
        }

    }


    /**
     * Test method for SafeSDF.
     */
    @Test
    public void testForSafeSimpleDateFormat() {
        final Runnable r[] = new Runnable[testDate.length];
        for (int i = 0; i < r.length; i++) {

            final int i2 = i;

            r[i] = new Runnable() {
                public void run() {
                    try {
                        for (int j = 0; j < 10000000; j++) { // adjust this depending on your processor speed
                            final String str = testDate[i2];
                            String str2;
// synchronized(df)
                            {
                                final Date d = df.parse(str);
                                str2 = df.format(d);
                            }

                            Assert.assertEquals("SAFE date conversion failed after " + j + " iterations.", str, str2);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("SAFE parse failed");
                    }
                }
            };
            new Thread(r[i]).start();
        }

    }
}
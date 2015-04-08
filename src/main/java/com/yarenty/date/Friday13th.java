package com.yarenty.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;

/**
 * Java 8 new Date Time API.
 * Temporal Adjusters.
 * Created by yarenty on 08/04/15.
 */
public class Friday13th {


    static final TemporalAdjuster friday13Adjuster = temporal -> {
        temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);

        while (temporal.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
            temporal = temporal.plus(1, ChronoUnit.MONTHS);
        }
        return temporal;
    };

    public static void main(final String[] args) {

        final LocalDate today = LocalDate.now();
        final LocalDate nextFriday13 = today.with(friday13Adjuster);

        System.out.printf("Next Friday 13th is on: %s%n", nextFriday13);

    }


}

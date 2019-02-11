package com.example.cst8334project.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * This class contains TypeConverters used by the Room database.
 */
public final class Converters {

    /**
     * Converts the given {@link Long} value to a {@link Date}.
     *
     * @param timestampValue the {@link Long} value to convert to a {@link Date}
     * @return the given {@link Long} value as a {@link Date}
     */
    @TypeConverter
    public static Date timestampToDate(Long timestampValue) {
        return timestampValue == null ? null : new Date(timestampValue);
    }

    /**
     * Converts the given {@link Date} to a {@link Long}.
     *
     * @param date the {@link Date} to convert to a {@link Long}
     * @return the given {@link Date} as a {@link Long}
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * Prevent instantiation.
     */
    private Converters() {
    }
}

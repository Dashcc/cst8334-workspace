package com.example.cst8334project.forms.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Provides constants and utility methods useful for constructing Form objects.
 */
public final class FormUtils {

    /**
     * A String that represents the name of the Intent extra data form object.
     */
    public static final String FORM_INTENT_OBJECT_NAME = "form";

    /**
     * The {@link SimpleDateFormat} used to format the dates for the email attachments.
     */
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    /**
     * The String used to format the hours and minutes displayed for volunteer activity durations.
     */
    public static final String TIME_FORMAT = "%d:%02d";

    /**
     * A String that represents the CSV file extension.
     */
    public static final String CSV_EXTENSION = ".csv";

    /**
     * A String that represents the new line character.
     */
    public static final String NEW_LINE = "\n";

    /**
     * A String that represents a comma with a trailing space.
     */
    public static final String COMMA = ", ";

    /**
     * A String that represents a semi colon with a trailing space.
     */
    public static final String SEMI_COLON = "; ";

    /**
     * A String that represents a colon with a trailing space.
     */
    public static final String COLON = ": ";

    /**
     * Obtain a {@code List<String>} that represents the names of the given collection of {@link FormEnum}s.
     *
     * @param formEnums the {@link FormEnum}s collection
     * @return a {@code List<String>} that represents the names of the given collection of {@link FormEnum}s.
     */
    public static List<String> getEnumNames(Collection<? extends FormEnum> formEnums) {
        List<String> enumNames = new ArrayList<>(formEnums.size());

        for (FormEnum formEnum : formEnums) {
            enumNames.add(formEnum.getName());
        }

        return enumNames;
    }

    /**
     * Obtain the CSV file name, including the timestamp, for the given visit type.
     *
     * @param visitType the type of the visit, as a String
     * @return a String that represents the CSV file name with the timestamp included
     */
    public static String getCSVFileName(String visitType) {
        return "HHH - " + SIMPLE_DATE_FORMAT.format(new Date()) + COLON + visitType;
    }

    /**
     * Replace all the commas in the given String with semi-colons.
     *
     * @param toReplace the String to manipulate
     * @return the original String, with commas instead of semi-colons.
     */
    public static String replaceCommas(String toReplace) {
        return toReplace.replace(COMMA.charAt(0), SEMI_COLON.charAt(0));
    }
    
    /**
     * Prevent instantiation.
     */
    private FormUtils() {
    }
}

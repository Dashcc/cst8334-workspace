package com.example.cst8334project.forms.util;

import com.example.cst8334project.domain.Email;

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
    private static final SimpleDateFormat ATTACHMENT_DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    /**
     * The String used to format the hours and minutes displayed for volunteer activity durations.
     */
    public static final String TIME_FORMAT = "%d:%02d";

    /**
     * A String that represents the CSV file extension.
     */
    private static final String CSV_EXTENSION = ".csv";

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
    private static final String SEMI_COLON = "; ";

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
     * Set the subject, csv attachment file name, and the body for the given Email based on the
     * provided visit type.
     *
     * @param email     the {@link Email}
     * @param visitType the type of the visit
     */
    public static void setEmailProperties(Email email, String visitType) {
        String subject = "HHH" + " - " + visitType + " - " + ATTACHMENT_DATE_FORMAT.format(new Date());
        email.setSubject(subject);
        email.setCsvAttachmentFileName(subject + CSV_EXTENSION);
        email.setBody("Please find attached a form for a " + visitType + " volunteer visit.");
    }

    /**
     * Replace all the commas in the given String with semi-colons.
     *
     * @param toReplace the String to manipulate
     * @return the original String, with semi-colons instead of commas.
     */
    public static String replaceCommas(String toReplace) {
        return toReplace.replace(COMMA.charAt(0), SEMI_COLON.charAt(0));
    }

    /**
     * Returns a user-friendly string representation of the given boolean - {@code true} returns "Yes",
     * while {@code false} returns "No".
     *
     * @param bool the boolean value
     * @return a user-friendly string representation of the given boolean
     */
    public static String replaceBooleanWithYesNo(boolean bool) {
        return bool ? "Yes" : "No";
    }

    public static String convertTimeToString(String timeString) {
        int hours = Integer.valueOf(timeString.split(":")[0]);
        int minutes = Integer.valueOf(timeString.split(":")[1]);

        double time = hours + minutes / (60.0);
        return String.format("%.2f", time);
    }

    /**
     * Prevent instantiation.
     */
    private FormUtils() {
    }
}

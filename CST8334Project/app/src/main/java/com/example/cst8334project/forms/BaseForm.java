package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import java.io.Serializable;
import java.util.List;

import static com.example.cst8334project.forms.util.FormUtils.*;

/**
 * This class represents a base, abstract form.
 */
public abstract class BaseForm implements Serializable {

    /**
     * The headers that will be included in all forms.
     */
    private static final String[] BASE_HEADERS = new String[]{"Name", "Date", "Visit Type", "Student Placement?"};

    /**
     * An enum that represents the type of the volunteer visit.
     */
    public enum FormType implements FormEnum {
        /**
         * A direct visit.
         */
        DIRECT("Direct"),

        /**
         * An indirect visit.
         */
        INDIRECT("Indirect");

        /**
         * The name, as it is rendered on the CSV file, of this {@link FormType}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link FormType}
         */
        FormType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * The name of the volunteer.
     */
    private String name;

    /**
     * The date of the visit.
     */
    private String date;

    /**
     * A boolean flag that indicates whether this visit is a student placement.
     */
    private boolean isStudentPlacement;

    /**
     * The type of the Form (Direct or Indirect).
     */
    protected FormType formType;

    /**
     * Get the String that represents the complete attachment text that is to be written to the CSV
     * file. Note that the method is final, thus it cannot be overridden.
     *
     * @return the String that represents the complete attachment text that is to be written to the CSV
     * file
     */
    public final String getAttachmentText() {
        int numOfRows = getActivitySpecificRows().size();

        StringBuilder csvText = new StringBuilder();

        for (int i = 0; i < numOfRows; i++) {
            String dataLine = getActivitySpecificRows().get(i);

            csvText.append(name)
                    .append(COMMA)
                    .append(date)
                    .append(COMMA)
                    .append(dataLine);

            // Append a new line character if this is not the last line
            if (i < numOfRows - 1) {
                csvText.append(NEW_LINE);
            }
        }

        return csvText.toString();
    }

    /**
     * Get the list of Strings that represents the activity specific rows for the various forms.
     *
     * @return a {@code List<String>} that represents the activity specific rows for the various forms
     */
    protected abstract List<String> getActivitySpecificRows();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStudentPlacement(boolean studentPlacement) {
        isStudentPlacement = studentPlacement;
    }
}

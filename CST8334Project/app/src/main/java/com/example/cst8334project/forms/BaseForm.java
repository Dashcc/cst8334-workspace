package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import java.io.Serializable;

import static com.example.cst8334project.forms.util.FormUtils.*;
import static org.apache.commons.lang3.StringUtils.joinWith;

/**
 * This class represents a base, abstract form.
 */
public abstract class BaseForm implements Serializable {

    /**
     * The headers that will be included in all forms.
     */
    private static final String[] BASE_HEADERS = new String[]{"Name", "Date", "Visit Type"};

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
     * The type of the Form (Direct or Indirect).
     */
    protected FormType formType;

    /**
     * Get the String that represents the column header row of the CSV file. Subclasses should override
     * this method to add headers that are specific to them.
     *
     * @return the String that represents the column headers of the CSV file
     */
    protected String getHeaderRow() {
        return joinWith(COMMA, (Object[]) BASE_HEADERS);
    }

    /**
     * Get the String that represents the data row of the CSV file. Subclasses should override
     * this method to add data that are specific to them.
     *
     * @return the String that represents the data row of the CSV file
     */
    protected String getDataRow() {
        return joinWith(COMMA, name, date, formType.getName());
    }

    /**
     * Get the String that represents the complete attachment text that is to be written to the CSV
     * file. Note that the method is final, thus it cannot be overridden.
     *
     * @return the String that represents the complete attachment text that is to be written to the CSV
     * file
     */
    public final String getAttachmentText() {
        return getHeaderRow() + NEW_LINE + getDataRow();
    }

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
}

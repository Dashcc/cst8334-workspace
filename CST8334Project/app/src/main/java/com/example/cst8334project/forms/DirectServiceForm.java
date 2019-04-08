package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import static com.example.cst8334project.forms.util.FormUtils.*;

/**
 * This class represents a Direct Service form.
 */
public class DirectServiceForm extends BaseForm {

    /**
     * The column header that specifies the type of the Direct Service.
     */
    private static final String DIRECT_SERVICE_HEADER = "Direct Service Type";

    /**
     * An enum that represents the type of the direct service visit.
     */
    public enum DirectServiceType implements FormEnum {
        /**
         * An in-home visit.
         */
        IN_HOME("In Home"),

        /**
         * An in-office visit.
         */
        IN_OFFICE("In Office");

        /**
         * The name, as it is rendered on the CSV file, of this {@link DirectServiceType}
         */
        private String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link DirectServiceType}
         */
        DirectServiceType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * The type of the Direct service.
     */
    private DirectServiceType directServiceType;

    /**
     * Construct an instance of a {@link DirectServiceForm}.
     */
    public DirectServiceForm() {
        this.formType = FormType.DIRECT;
    }

    @Override
    protected String getHeaderRow() {
        return super.getHeaderRow() + COMMA + DIRECT_SERVICE_HEADER;
    }

    @Override
    protected String getDataRow() {
        return super.getDataRow() + COMMA + getDirectServiceType().getName();
    }
    
    protected DirectServiceType getDirectServiceType() {
        return directServiceType;
    }

    protected void setDirectServiceType(DirectServiceType directServiceType) {
        this.directServiceType = directServiceType;
    }
}

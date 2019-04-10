package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import java.util.List;

/**
 * This class represents a Direct Service form.
 */
public class DirectServiceForm extends BaseForm {

    /**
     * An enum that represents the type of the direct service visit.
     */
    public enum DirectServiceType implements FormEnum {
        /**
         * An in-home visit.
         */
        IN_HOME("InHome"),

        /**
         * An in-office visit.
         */
        IN_OFFICE("InOffice");

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
    protected List<String> getActivitySpecificRows() {
        return null;
    }

    protected void setDirectServiceType(DirectServiceType directServiceType) {
        this.directServiceType = directServiceType;
    }
}

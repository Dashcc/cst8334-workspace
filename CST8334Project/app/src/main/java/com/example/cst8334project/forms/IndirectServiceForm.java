package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import org.apache.commons.lang3.tuple.Pair;

import static com.example.cst8334project.forms.util.FormUtils.COMMA;

/**
 * This class represents an Indirect Service form.
 */
public class IndirectServiceForm extends BaseForm {

    /**
     * This enum represents the type of the Indirect service.
     */
    public enum IndirectServiceType implements FormEnum {
        /**
         * Administrative service.
         */
        ADMIN("Admin"),

        /**
         * Board service.
         */
        BOARD("Board"),

        /**
         * Training service.
         */
        TRAINING("Training"),

        /**
         * Fundraising service.
         */
        FUNDRAISING("Fundraising"),

        /**
         * Outreach service.
         */
        OUTREACH("Outreach");

        /**
         * The name, as it is rendered on the CSV file, of this {@link IndirectServiceType}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link IndirectServiceType}
         */
        IndirectServiceType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * A {@link Pair} with a key of the {@link IndirectServiceType} and a value of the time that was spent
     * for this Indirect service.
     */
    private Pair<IndirectServiceType, String> indirectServiceTypePair;

    /**
     * Construct an instance of a {@link IndirectServiceForm}.
     */
    public IndirectServiceForm() {
        setFormType(FormType.INDIRECT);
    }

    /**
     * Set the {@link IndirectServiceType} key and the corresponding time value.
     *
     * @param indirectServiceType the {@link IndirectServiceType}
     * @param time                the time spent at this service
     */
    public void setIndirectServiceTypePair(IndirectServiceType indirectServiceType, String time) {
        this.indirectServiceTypePair = Pair.of(indirectServiceType, time);
    }

    @Override
    protected String getDataRow() {
        String dataString = indirectServiceTypePair.getValue();
        return super.getDataRow() + COMMA + dataString;
    }

    @Override
    protected String getHeaderRow() {
        String headerString = indirectServiceTypePair.getKey().getName();
        return super.getHeaderRow() + COMMA + headerString;
    }
}

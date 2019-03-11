package com.example.cst8334project.forms;

import static com.example.cst8334project.forms.util.FormUtils.*;

import com.example.cst8334project.forms.util.FormEnum;
import com.example.cst8334project.forms.util.FormUtils;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.LinkedHashMap;
import java.util.Map;

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
     * A map with a key of the {@link IndirectServiceType} and a value of the time that was spent
     * for this Indirect service.
     */
    private final Map<IndirectServiceType, String> indirectServiceTypeMap;

    /**
     * Construct an instance of a {@link IndirectServiceForm}.
     */
    public IndirectServiceForm() {
        setFormType(FormType.INDIRECT);
        this.indirectServiceTypeMap = new LinkedHashMap<>();
    }

    /**
     * Add the given {@link IndirectServiceType} and the corresponding time spent for this service
     * to the map.
     *
     * @param type the type of the Indirect service
     * @param time the time spent at the Indirect service
     */
    public void addIndirectServiceType(IndirectServiceType type, String time) {
        indirectServiceTypeMap.put(type, time);
    }

    @Override
    protected String getDataRow() {
        String dataString = join(indirectServiceTypeMap.values(), COMMA);
        return super.getDataRow() + COMMA + dataString;
    }

    @Override
    protected String getHeaderRow() {
        String headerString = join(FormUtils.getEnumNames(indirectServiceTypeMap.keySet()), COMMA);
        return super.getHeaderRow() + COMMA + headerString;
    }
}

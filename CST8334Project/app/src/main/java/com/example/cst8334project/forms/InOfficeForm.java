package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import static com.example.cst8334project.forms.util.FormUtils.*;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class represents an In Office form.
 */
public class InOfficeForm extends DirectServiceForm {

    /**
     * An enum that represents the type of the In Office visit.
     */
    public enum InOfficeType implements FormEnum {
        /**
         * A Spa in-office visit.
         */
        SPA("Spa"),

        /**
         * A Day program in-office visit.
         */
        DAY_PROGRAM("Day Program"),

        /**
         * A CT in-office visit.
         */
        CT("CT"),

        /**
         * A Training in-office visit.
         */
        TRAINING("Training"),

        /**
         * An Outreach in-office visit.
         */
        OUTREACH("Outreach");

        /**
         * The name, as it is rendered on the CSV file, of this {@link InOfficeType}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link InOfficeType}
         */
        InOfficeType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * A map with a key of the {@link InOfficeType} and a value of the time that was spent
     * for this In Office service.
     */
    private final Map<InOfficeType, String> inOfficeTypeMap;

    /**
     * Construct an instance of a {@link InOfficeForm} for the given {@link DirectServiceForm}.
     *
     * @param directServiceForm the {@link DirectServiceForm}
     */
    public InOfficeForm(DirectServiceForm directServiceForm) {
        setName(directServiceForm.getName());
        setDate(directServiceForm.getDate());
        setDirectServiceType(DirectServiceType.IN_OFFICE);
        inOfficeTypeMap = new LinkedHashMap<>();
    }

    public String getInOfficeServiceTypes() {
        return join(inOfficeTypeMap.keySet(), COMMA);
    }

    /**
     * Add the given {@link InOfficeType} and the corresponding time spent for this service
     * to the map.
     *
     * @param inOfficeType the type of the In Office service
     * @param time the time spent at the In Office service
     */
    public void addInOfficeType(InOfficeType inOfficeType, String time) {
        inOfficeTypeMap.put(inOfficeType, time);
    }

    @Override
    public String getDataRow() {
        String dataString = join(inOfficeTypeMap.values(), COMMA);
        return super.getDataRow() + COMMA + dataString;
    }

    @Override
    public String getHeaderRow() {
        String headerString = join(getEnumNames(inOfficeTypeMap.keySet()), COMMA);
        return super.getHeaderRow() + COMMA + headerString;
    }
}

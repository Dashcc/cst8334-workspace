package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import static com.example.cst8334project.forms.util.FormUtils.*;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an In Office form.
 */
public class InOfficeForm extends DirectServiceForm {

    /**
     * The column header that represents the number of persons supported in this In Office visit.
     */
    private static final String NUM_OF_PERSONS_HEADER = "Number of Persons Supported";

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
        OUTREACH("Outreach"),

        /**
         * An Art therapy in-office visit.
         */
        ART_THERAPY("Art Therapy"),

        /**
         * A Music therapy in-office visit.
         */
        MUSIC_THERAPY("Music Therapy");

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
     * The number of persons supported in this In Office visit.
     */
    private String numberOfPersonsSupported;

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
     * @param time         the time spent at the In Office service
     */
    public void addInOfficeType(InOfficeType inOfficeType, String time) {
        inOfficeTypeMap.put(inOfficeType, time);
    }

    public void setNumberOfPersonsSupported(String numberOfPersonsSupported) {
        this.numberOfPersonsSupported = numberOfPersonsSupported;
    }

    @Override
    protected List<String> getActivitySpecificRows() {
        List<String> dataRows = new ArrayList<>();

        for (Map.Entry<InOfficeType, String> inOfficeTypeEntry : inOfficeTypeMap.entrySet()) {
            String activityType = inOfficeTypeEntry.getKey().getName();
            String activityTime = inOfficeTypeEntry.getValue();

            dataRows.add(activityType + COMMA + COMMA + activityTime + COMMA);
        }

        return dataRows;
    }

    @Override
    protected String getType() {
        return "INOFFICE";
    }
}

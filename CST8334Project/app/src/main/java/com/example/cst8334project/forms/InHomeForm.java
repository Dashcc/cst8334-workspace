package com.example.cst8334project.forms;

import com.example.cst8334project.forms.util.FormEnum;

import static com.example.cst8334project.forms.util.FormUtils.*;

import static org.apache.commons.lang3.StringUtils.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents an In Home form.
 */
public class InHomeForm extends DirectServiceForm {

    /**
     * The CSV column headers that are specific to a In Home visit.
     */
    private static final String[] IN_HOME_HEADERS =
            new String[]{"Client Name", "Client Type(s)", "Number of Persons Supported", "Comp Therapies"};

    /**
     * The column header that represents the Notes added by the volunteer for this In Home visit.
     */
    private static final String NOTES_HEADER = "Notes";

    /**
     * An enum that represents the type of the client served at this In Home visit.
     */
    public enum ClientType implements FormEnum {

        /**
         * A palliative client.
         */
        PALLIATIVE("Palliative"),

        /**
         * A caregiver client.
         */
        CAREGIVER("Caregiver"),

        /**
         * A bereaved client.
         */
        BEREAVED("Bereaved");

        /**
         * The name, as it is rendered on the CSV file, of this {@link ClientType}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link ClientType}
         */
        ClientType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * An enum that represents the type of the Comp Therapy provided at this In Home visit.
     */
    public enum CompTherapy implements FormEnum {

        /**
         * A Reiki comp therapy.
         */
        REIKI("Reiki"),

        /**
         * A TT comp therapy.
         */
        TT("TT"),

        /**
         * An aroma comp therapy.
         */
        AROMA("Aroma");

        /**
         * The name, as it is rendered on the CSV file, of this {@link CompTherapy}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link CompTherapy}
         */
        CompTherapy(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * An enum that represents the type of this In Home visit.
     */
    public enum InHomeType implements FormEnum {

        /**
         * A companioning in home visit.
         */
        COMPANIONING("Companioning"),

        /**
         * A respite in home visit.
         */
        RESPITE("Respite"),

        /**
         * A spiritual in home visit.
         */
        SPIRITUAL("Spiritual");

        /**
         * The name, as it is rendered on the CSV file, of this {@link InHomeType}
         */
        private final String name;

        /**
         * Private constructor.
         *
         * @param name the name as its rendered on the CSV file, of this {@link InHomeType}
         */
        InHomeType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * The name of the client served at this In Home visit.
     */
    private String clientName;

    /**
     * The client type(s) for this In Home visit.
     */
    private Set<ClientType> clientTypes;

    /**
     * The number of persons supported in this In Home visit.
     */
    private String numberOfPersonsSupported;

    /**
     * A map with a key of the {@link CompTherapy} and a value of the time that was spent for this
     * comp therapy.
     */
    private final Map<CompTherapy, String> compTherapiesMap;

    /**
     * A map with a key of the {@link InHomeType} and a value of the time that was spent for this
     * In Home service.
     */
    private final Map<InHomeType, String> inHomeTypeMap;

    /**
     * The note that the volunteer can add for this In Home visit.
     */
    private String note;

    /**
     * Construct an instance of a {@link InHomeForm} for the given {@link DirectServiceForm}.
     *
     * @param directServiceForm the {@link DirectServiceForm}
     */
    public InHomeForm(DirectServiceForm directServiceForm) {
        setName(directServiceForm.getName());
        setDate(directServiceForm.getDate());
        setDirectServiceType(DirectServiceType.IN_HOME);
        clientTypes = new LinkedHashSet<>();
        compTherapiesMap = new LinkedHashMap<>();
        inHomeTypeMap = new LinkedHashMap<>();
    }

    /**
     * Add the given {@link ClientType} to the set.
     *
     * @param clientType the type of the client
     */
    public void addClientType(ClientType clientType) {
        clientTypes.add(clientType);
    }

    /**
     * Add the given {@link CompTherapy} and the corresponding time spent for this service
     * to the map.
     *
     * @param compTherapy the type of the comp therapy
     * @param time        the time spent at the comp therapy service
     */
    public void addCompTherapy(CompTherapy compTherapy, String time) {
        compTherapiesMap.put(compTherapy, time);
    }

    /**
     * Add the given {@link InHomeType} and the corresponding time spent for this service
     * to the map.
     *
     * @param inHomeType the type of the in home service
     * @param time       the time spent at the in home service
     */
    public void addInHomeType(InHomeType inHomeType, String time) {
        inHomeTypeMap.put(inHomeType, time);
    }

    @Override
    public String getDataRow() {
        StringBuilder sb = new StringBuilder();

        sb.append(clientName).append(COMMA)
                .append(join(getEnumNames(clientTypes), SEMI_COLON))
                .append(COMMA).append(numberOfPersonsSupported);

        if (!compTherapiesMap.isEmpty()) {
            sb.append(COMMA).append(join(getCompTherapiesData(), SEMI_COLON));
        }

        if (!inHomeTypeMap.isEmpty()) {
            sb.append(COMMA).append(join(inHomeTypeMap.values(), COMMA));
        }

        sb.append(COMMA).append(note);

        return super.getDataRow() + COMMA + sb.toString();
    }

    @Override
    public String getHeaderRow() {
        StringBuilder sb = new StringBuilder();

        sb.append(join(IN_HOME_HEADERS, COMMA));

        if (!inHomeTypeMap.isEmpty()) {
            sb.append(COMMA).append(join(getEnumNames(inHomeTypeMap.keySet()), COMMA));
        }

        sb.append(COMMA).append(NOTES_HEADER);

        return super.getHeaderRow() + COMMA + sb.toString();
    }

    /**
     * Construct a {@code List<String>} that represents the {@link CompTherapy} data for this In
     * Home visit.
     *
     * @return a {@code List<String>} that represents the {@link CompTherapy} data for this In
     * Home visit
     */
    private List<String> getCompTherapiesData() {
        List<String> compTherapiesList = new ArrayList<>();
        for (Map.Entry<CompTherapy, String> compTherapyEntry : compTherapiesMap.entrySet()) {
            String compTherapyType = compTherapyEntry.getKey().getName();
            String compTherapyTime = compTherapyEntry.getValue();

            compTherapiesList.add(compTherapyType + COLON + compTherapyTime);
        }
        return compTherapiesList;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setNumberOfPersonsSupported(String numberOfPersonsSupported) {
        this.numberOfPersonsSupported = numberOfPersonsSupported;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getServiceTypes() {
        return join(getEnumNames(inHomeTypeMap.keySet()), COMMA);
    }
}

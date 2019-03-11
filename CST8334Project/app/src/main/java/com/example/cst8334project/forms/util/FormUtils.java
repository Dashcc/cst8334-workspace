package com.example.cst8334project.forms.util;

import java.util.ArrayList;
import java.util.Collection;
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
    public static final String SEMI_COLON = "; ";

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
     * Prevent instantiation.
     */
    private FormUtils() {
    }
}

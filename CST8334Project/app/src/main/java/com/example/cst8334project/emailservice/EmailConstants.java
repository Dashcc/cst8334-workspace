package com.example.cst8334project.emailservice;

import java.util.Properties;

/**
 * This class defines several constants pertaining to sending emails.
 */
public final class EmailConstants {

    /**
     * The email address that will be used by all volunteers to send emails to the Heart House
     * Hospice.
     */
    public static final String SENDER_EMAIL_ADDRESS = "hearthousehospicevolunteer@gmail.com";

    /**
     * The password corresponding to the email address that will be used by all volunteers to send
     * emails to the Heart House Hospice.
     */
    public static final String SENDER_EMAIL_PASSWORD = "hearthousehospice123";

    /**
     * The receiving email address belonging to the Heart House Hospice.
     */
    public static final String RECIPIENT_EMAIL_ADDRESS = "hearthousehospicestaff@gmail.com";

    /**
     * The SMTP properties that facilitate sending emails.
     */
    public static final Properties EMAIL_PROPERTIES;

    // Set the STMP properties using a static initializer block.
    static {
        EMAIL_PROPERTIES = new Properties();
        EMAIL_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        EMAIL_PROPERTIES.put("mail.smtp.socketFactory.port", "465");
        EMAIL_PROPERTIES.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        EMAIL_PROPERTIES.put("mail.smtp.auth", "true");
        EMAIL_PROPERTIES.put("mail.smtp.port", "465");
        //EMAIL_PROPERTIES.put("mail.debug", "true");
    }

    /**
     * Prevent instantiation.
     */
    private EmailConstants() {
    }
}

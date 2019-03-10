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
     * The email belonging to the Heart House Hospice that is being listened to by this
     * application for new passwords used to authenticate volunteers.
     */
    public static final String PASSWORD_LISTENER_EMAIL_ADDRESS = "hhhchangepwd@gmail.com";

    /**
     * The password for the email address that listens for new passwords.
     */
    public static final String PASSWORD_LISTENER_EMAIL_PASSWORD = "hearthousehospice123";

    /**
     * The SMTP properties that facilitate sending emails.
     */
    public static final Properties SMTP_PROPERTIES;

    /**
     * The IMAP properties that facilitate retrieving emails.
     */
    public static final Properties IMAP_PROPERTIES;

    /**
     * The address for the SMTP server.
     */
    public static final String SMTP_SERVER = "smtp.gmail.com";

    /**
     * The SMTP port number.
     */
    public static final int SMTP_PORT = 465;

    /**
     * The address for the IMAP server.
     */
    public static final String IMAP_SERVER = "imap.gmail.com";

    /**
     * The IMAP port number.
     */
    public static final int IMAP_PORT = 993;

    // Initialize the SMTP and IMAP properties using a static block.
    static {
        SMTP_PROPERTIES = new Properties();
        SMTP_PROPERTIES.put("mail.smtp.host", SMTP_SERVER);
        SMTP_PROPERTIES.put("mail.smtp.socketFactory.port", Integer.toString(SMTP_PORT));
        SMTP_PROPERTIES.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        SMTP_PROPERTIES.put("mail.smtp.auth", "true");
        SMTP_PROPERTIES.put("mail.smtp.port", Integer.toString(SMTP_PORT));

        IMAP_PROPERTIES = new Properties();
        IMAP_PROPERTIES.put("mail.imaps.host", IMAP_SERVER);
        IMAP_PROPERTIES.put("mail.imaps.port", Integer.toString(IMAP_PORT));
    }

    /**
     * The name of the temporary CSV file that will be created and populated for each email. After
     * the email has been sent, the file will be deleted.
     */
    public static final String TEMP_CSV_FILE_NAME = "TEMP.csv";

    /**
     * Prevent instantiation.
     */
    private EmailConstants() {
    }
}

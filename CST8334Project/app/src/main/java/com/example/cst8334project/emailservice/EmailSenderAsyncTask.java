package com.example.cst8334project.emailservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.util.ConnectionUtils;

import java.lang.ref.WeakReference;
import java.util.Date;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import static com.example.cst8334project.emailservice.EmailConstants.*;
import static com.example.cst8334project.util.FileUtils.*;

/**
 * This class is responsible for sending emails in a background thread.
 */
public class EmailSenderAsyncTask extends AsyncTask<Email, Void, Void> {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = EmailSenderAsyncTask.class.getSimpleName();

    /**
     * Provides service level methods for {@link Email}s.
     */
    private final EmailService emailService;

    /**
     * A {@link WeakReference} to the {@link Context} of the current state of the application.
     */
    private final WeakReference<Context> context;

    /**
     * Construct a new {@link EmailSenderAsyncTask} with the given {@link Context}.
     *
     * @param context the {@link Context} of the application; can be provided to this
     *                constructor by setting 'this' as a parameter from a class
     *                that extends {@link android.app.Activity}
     */
    public EmailSenderAsyncTask(Context context) {
        this.context = new WeakReference<>(context);
        this.emailService = new EmailServiceImpl(this.context.get());
    }

    /**
     * This method is responsible for sending email(s) in a background thread. A background thread
     * is required to do so since Android does not permit performing network activity on the main
     * (UI) thread.
     *
     * @param emails the Email(s) to send
     * @return {@code true} if the email(s) were successfully sent, {@code false} otherwise
     */
    @Override
    protected Void doInBackground(Email... emails) {
        // Don't proceed if the device cannot connect to the GMail SMTP server
        if (!ConnectionUtils.canConnectToSMTPServer(context.get())) {
            Log.e(CLASS_NAME, "Could not connect to the Google SMTP server. " +
                    "The email(s) will not be sent.");

            // Add all the emails to the unsent emails data store
            for (Email email : emails) {
                addEmailToUnsentEmails(email);
            }

            return null;
        }

        // Initialize a mail Session and authenticate the login credentials
        Session session = Session.getInstance(SMTP_PROPERTIES, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });

        // Iterate through the emails
        for (Email email : emails) {
            // Create the temporary CSV file and write the attachment text to it
            writeTextToFile(context.get(), TEMP_CSV_FILE_NAME, email.getAttachmentText());

            // Log before sending the email
            Log.i(CLASS_NAME, "*** Attempting to send Email: ***" + email);

            try {
                // Send the email
                Transport.send(getMimeMessageForEmail(session, email));

                Log.i(CLASS_NAME, "Email sent at: " + new Date().toString());

                // Delete the sent email from the unsent email datastore if it exists there
                emailService.deleteSentEmail(email);

            } catch (MessagingException e) {
                Log.e(CLASS_NAME, "Error occurred while attempting to send email: " + email, e);
                addEmailToUnsentEmails(email);
            }

            // Delete the temporary CSV file from the user's directory after the email has been sent
            deleteFileFromStorage(context.get(), TEMP_CSV_FILE_NAME);
        }

        return null;
    }

    /**
     * Construct the {@link MimeMessage} for the given {@link Session} and {@link Email}.
     *
     * @param session the mail {@link Session}
     * @param email   the {@link Email} to convert to a {@link MimeMessage}
     * @return the given {@link Email} as a {@link MimeMessage} object
     * @throws MessagingException if the MimeMessage object could not be constructed
     */
    private MimeMessage getMimeMessageForEmail(Session session, Email email) throws MessagingException {
        // Create the MimeMessage from the mail Session
        MimeMessage mimeMessage = new MimeMessage(session);

        // Set the sender for the email
        mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));

        // Set the recipient for the email
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT_EMAIL_ADDRESS));

        // Set the subject for the email
        mimeMessage.setSubject(email.getSubject());

        // Create the email body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();

        // Set the body of the email
        messageBodyPart.setText(email.getBody());

        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Set the text message part
        multipart.addBodyPart(messageBodyPart);

        // Create the email attachment part
        MimeBodyPart messageAttachmentPart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(getAbsoluteFilePath(context.get(), TEMP_CSV_FILE_NAME));
        messageAttachmentPart.setDataHandler(new DataHandler(dataSource));
        messageAttachmentPart.setFileName(email.getCsvAttachmentFileName());
        multipart.addBodyPart(messageAttachmentPart);

        // Set the body part and the attachment part as a multipart
        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    /**
     * Add the given {@link Email} to the unsent Emails datastore to be sent at a later time
     * when it is possible to do so.
     *
     * @param email the {@link Email} to add to the unsent {@link Email} datastore
     */
    private void addEmailToUnsentEmails(Email email) {
        email.setEmailTimestamp(new Date());
        emailService.addUnsentEmail(email);
    }
}

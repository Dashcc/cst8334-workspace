package com.example.cst8334project.emailservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.cst8334project.userhistoryservice.*;

import java.lang.ref.WeakReference;
import java.util.Date;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import static com.example.cst8334project.emailservice.EmailConstants.*;
import static com.example.cst8334project.util.FileUtils.*;

/**
 * This class is responsible for sending emails in a background thread. It also adds the visit to
 * the user's history.
 */
public class SendEmailActivity extends AsyncTask<Void, Void, Void> {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = SendEmailActivity.class.getSimpleName();

    /**
     * Provides service level methods for {@link Visit}s.
     */
    private final VisitService visitService;

    /**
     * A {@link WeakReference} to the {@link Context} of the current state of the application.
     */
    private final WeakReference<Context> context;

    /**
     * The {@link Email} to be sent.
     */
    private final Email email;

    /**
     * The {@link Visit} corresponding to the {@link Email}.
     */
    private final Visit visit;

    /**
     * Construct a new {@link SendEmailActivity} with the given {@link Context}, the
     * {@link Email} to be sent, and the {@link Visit} to be added to the user's history.
     *
     * @param context the {@link Context} of the application; can be provided to this
     *                constructor by setting 'this' as a parameter from a class
     *                that extends {@link android.app.Activity}
     * @param email   the email to be sent
     * @param visit   the {@link Visit} corresponding to the email
     */
    public SendEmailActivity(Context context, Email email, Visit visit) {
        this.context = new WeakReference<>(context);
        this.email = email;
        this.visit = visit;
        this.visitService = new VisitServiceImpl(this.context.get());
    }

    /**
     * This method is executed before the email is sent. It is responsible for creating the temporary
     * CSV file to be used for the email attachment, write the provided email attachment text to it,
     * and log the email.
     */
    @Override
    protected void onPreExecute() {
        // Create the temporary CSV file and write the attachment text to it
        writeTextToFile(context.get(), TEMP_CSV_FILE_NAME, email.getAttachmentText());

        // Construct the logging message
        String loggingMessage = String.format("************** Sending email ************** %n" +
                                              "TIMESTAMP: %s %n" +
                                              "FROM: %s %n" +
                                              "TO: %s %n" +
                                              "SUBJECT: %s %n" +
                                              "BODY: %s %n" +
                                              "ATTACHMENT FILE NAME: %s %n" +
                                              "ATTACHMENT BODY: %n" +
                                              "%s",
                                              new Date().toString(),
                                              SENDER_EMAIL_ADDRESS,
                                              RECIPIENT_EMAIL_ADDRESS,
                                              email.getSubject(),
                                              email.getBody(),
                                              email.getCsvAttachmentFileName(),
                                              readTextFromFile(context.get(), TEMP_CSV_FILE_NAME));

        // Log before sending the email
        Log.i(CLASS_NAME, loggingMessage);
    }

    /**
     * This method is responsible for sending the email in a background thread. A background thread
     * is required to do so since Android does not permit performing network activity on the main
     * (UI) thread.
     * @param voids the parameters of the task
     * @return null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        // Initialize a mail Session and authenticate the login credentials
        Session session = Session.getInstance(EMAIL_PROPERTIES, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });

        try {
            // Create the MimeMessage object using the provided Session
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

            // Send the email
            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            Log.e(CLASS_NAME, "Error occurred while attempting to send email.", e);
        }

        return null;
    }

    /**
     * This method is executed after the email has been successfully sent. It is responsible for
     * logging the sent email, deleting the temporary CSV file that was created, and adding the
     * {@link Visit} to the user's history.
     *
     * @param aVoid The result of the operation computed by doInBackground.
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        // Log after the email has been sent
        Log.i(CLASS_NAME, "************* Email sent at *************\n" + new Date().toString());

        // Delete the temporary CSV file from the user's directory after the email has been sent
        deleteFileFromStorage(context.get(), TEMP_CSV_FILE_NAME);

        // Add the Visit to the user's history
        visitService.addVisit(visit);
        Toast.makeText(context.get(), "Email sent", Toast.LENGTH_LONG).show();
    }
}

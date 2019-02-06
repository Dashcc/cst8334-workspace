package com.example.cst8334project.emailservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cst8334project.util.FileUtils;

import java.lang.ref.WeakReference;
import java.util.Date;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import static com.example.cst8334project.emailservice.EmailConstants.*;

/**
 * This class is responsible for sending emails in a background thread.
 */
public class SendEmailActivity extends AsyncTask<Void, Void, Void> {

    /**
     * A {@link WeakReference} to the {@link Context} of the current state of the application.
     */
    private final WeakReference<Context> context;

    /**
     * The email to be sent.
     */
    private final Email email;

    /**
     * All-args constructor.
     *
     * @param context the {@link Context} of the application; can be provided to this
     *                constructor by setting 'this' as a parameter from a class
     *                that extends {@link android.app.Activity}
     * @param email   the email to be sent
     */
    public SendEmailActivity(Context context, Email email) {
        this.context = new WeakReference<>(context);
        this.email = email;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //TODO: Implement some kind of indicator before sending the email?
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //TODO: Implement some kind of indicator after sending the email?
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Initialize a mail Session and authenticate the login credentials
        Session session = Session.getDefaultInstance(EMAIL_PROPERTIES, new javax.mail.Authenticator() {
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
            DataSource dataSource = new FileDataSource(email.getCsvAttachmentAbsolutePath());
            messageAttachmentPart.setDataHandler(new DataHandler(dataSource));
            messageAttachmentPart.setFileName(email.getCsvAttachmentFileName());
            multipart.addBodyPart(messageAttachmentPart);

            // Set the body part and the attachment part as a multipart
            mimeMessage.setContent(multipart);

            // Send the email
            Transport.send(mimeMessage);

            // Construct the logging message
            String loggingMessage = String.format("%n Email sent from: %s %n" +
                                                  "to: %s %n" +
                                                  "with subject: %s %n" +
                                                  "body: %s %n" +
                                                  "attachment file name: %s %n" +
                                                  "attachment body: %n" +
                                                  "%s %n" +
                                                  "Sent at %s.",
                                                  SENDER_EMAIL_ADDRESS,
                                                  RECIPIENT_EMAIL_ADDRESS,
                                                  email.getSubject(),
                                                  email.getBody(),
                                                  email.getCsvAttachmentFileName(),
                                                  FileUtils.readTextFromFile(email.getCsvAttachmentAbsolutePath()),
                                                  new Date().toString());

            // Log the sent email
            Log.i("SendEmailActivity", loggingMessage);

        } catch (MessagingException e) {
            Log.e("SendEmailActivity", "Error occurred while attempting to send email.", e);
        }

        return null;
    }

}

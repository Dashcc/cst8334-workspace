package com.example.cst8334project.emailservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

import static com.example.cst8334project.emailservice.EmailConstants.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is responsible for sending emails in a background thread.
 */
public class SendEmailActivity extends AsyncTask<Void, Void, Void> {

    /**
     * A {@link WeakReference} to the {@link Context} of the current state of the application.
     */
    private final WeakReference<Context> context;

    /**
     * The subject of the email to be sent.
     */
    private final String emailSubject;

    /**
     * The body of the email to be sent.
     */
    private final String emailBody;

    /**
     * All-args constructor.
     *
     * @param context      the {@link Context} of the application; can be provided to this
     *                     constructor by setting 'this' as a parameter from a class
     *                     that extends {@link android.app.Activity}
     * @param emailSubject the subject of the email to be sent
     * @param emailBody    the body of the email to be sent
     */
    public SendEmailActivity(Context context, String emailSubject, String emailBody) {
        this.context = new WeakReference<Context>(context);
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
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
            MimeMessage mm = new MimeMessage(session);

            // Set the sender for the email
            mm.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));

            // Set the recipient for the email
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT_EMAIL_ADDRESS));

            // Set the subject for the email
            mm.setSubject(emailSubject);

            // Set the body of the email
            mm.setText(emailBody);

            // Send the email
            Transport.send(mm);

            Log.i("SendMailActivity", "Email sent.");

        } catch (MessagingException e) {
            Log.e("SendMailActivity", "Error occurred while attempting to send email.", e);
        }

        return null;
    }
}

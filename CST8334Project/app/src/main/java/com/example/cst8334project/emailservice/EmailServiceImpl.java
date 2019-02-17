package com.example.cst8334project.emailservice;

import android.content.Context;
import android.util.Log;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.persistence.EmailDAO;
import com.example.cst8334project.persistence.HHHDatabase;

import java.util.List;
import java.util.Objects;

/**
 * The implementation of the {@link EmailService} interface.
 */
public class EmailServiceImpl implements EmailService {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = EmailServiceImpl.class.getSimpleName();

    /**
     * Provides CRUD functionalities for {@link Email}s.
     */
    private final EmailDAO emailDAO;

    /**
     * Construct an instance of {@link EmailServiceImpl} for the given {@link Context}.
     *
     * @param context the {@link Context} of the application
     */
    public EmailServiceImpl(Context context) {
        this.emailDAO = HHHDatabase.getInstance(context).getEmailDAO();
    }

    @Override
    public void addUnsentEmail(Email email) {
        Objects.requireNonNull(email, "Email to be added cannot be null");
        Log.i(CLASS_NAME, "Adding unsent Email to the data store: " + email);
        long id = emailDAO.insertEmail(email);
        Log.i(CLASS_NAME, "The id of the inserted unsent Email is: " + id);
    }

    @Override
    public void deleteSentEmail(Email email) {
        Objects.requireNonNull(email, "Email to be deleted cannot be null");
        Log.i(CLASS_NAME, "Attempting to delete unsent Email from the data store: " + email);
        int numDeleted = emailDAO.deleteEmail(email);
        Log.i(CLASS_NAME, "The email was" + (numDeleted > 0 ? "" : " not") + " deleted.");
    }

    @Override
    public List<Email> findAllUnsentEmails() {
        List<Email> emails = emailDAO.findAllEmails();
        Log.i(CLASS_NAME, "All unsent emails: " + emails);
        return emails;
    }
}

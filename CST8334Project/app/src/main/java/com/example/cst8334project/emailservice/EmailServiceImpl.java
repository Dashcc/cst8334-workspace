package com.example.cst8334project.emailservice;

import android.util.Log;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.persistence.EmailDAO;
import com.example.cst8334project.persistence.HHHDatabase;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The implementation of the {@link EmailService} interface as an enum.
 */
public enum EmailServiceImpl implements EmailService {

    /**
     * The singleton instance for this service's implementation.
     */
    INSTANCE;

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = EmailServiceImpl.class.getSimpleName();

    /**
     * Provides CRUD functionalities for {@link Email}s.
     */
    private final EmailDAO emailDAO;

    /**
     * The {@link ExecutorService} object used to execute {@link EmailDAO} methods on a separate
     * thread.
     */
    private final ExecutorService executor;

    /**
     * Private constructor that constructs the singleton instance of {@link EmailServiceImpl}.
     */
    EmailServiceImpl() {
        this.emailDAO = HHHDatabase.getInstance().getEmailDAO();
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void addUnsentEmail(final Email email) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Objects.requireNonNull(email, "Email to be added cannot be null");
                Log.i(CLASS_NAME, "Adding unsent Email to the data store: " + email);
                long id = emailDAO.insertEmail(email);
                Log.i(CLASS_NAME, "The id of the inserted unsent Email is: " + id);
            }
        });
    }

    @Override
    public void deleteSentEmail(final Email email) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Objects.requireNonNull(email, "Email to be deleted cannot be null");
                Log.i(CLASS_NAME, "Attempting to delete unsent Email from the data store: " + email);
                int numDeleted = emailDAO.deleteEmail(email);
                Log.i(CLASS_NAME, "The email was" + (numDeleted > 0 ? "" : " not") + " deleted.");
            }
        });
    }

    @Override
    public List<Email> findAllUnsentEmails() {
        Future<List<Email>> unsentEmailsFuture = executor.submit(new Callable<List<Email>>() {
            @Override
            public List<Email> call() {
                List<Email> emails = emailDAO.findAllEmails();
                Log.i(CLASS_NAME, "All unsent emails: " + emails);
                return emails;
            }
        });

        try {
            return unsentEmailsFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(CLASS_NAME, "Encountered an error when trying to find all unsent Emails.", e);
            return null;
        }
    }
}

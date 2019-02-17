package com.example.cst8334project.emailservice;

import com.example.cst8334project.domain.Email;

import java.util.List;

/**
 * Provides service level methods for {@link Email}s.
 */
public interface EmailService {

    /**
     * Add the given unsent {@link Email} to the data store.
     *
     * @param email the unsent {@link Email}
     */
    void addUnsentEmail(Email email);

    /**
     * Delete the given sent {@link Email} from the data store.
     *
     * @param email the sent {@link Email}
     */
    void deleteSentEmail(Email email);

    /**
     * Find all unsent {@link Email}s in ascending order of timestamp.
     *
     * @return List of all unsent {@link Email}s in ascending order of timestamp
     */
    List<Email> findAllUnsentEmails();
}

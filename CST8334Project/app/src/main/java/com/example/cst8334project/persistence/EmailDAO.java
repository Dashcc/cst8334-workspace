package com.example.cst8334project.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.cst8334project.domain.Email;

import java.util.List;

/**
 * Provides CRUD functionalities for {@link Email}s.
 */
@Dao
public interface EmailDAO {

    /**
     * Insert the given {@link Email} into the data store.
     *
     * @param email the {@link Email} to insert
     * @return the id of the inserted Email
     */
    @Insert
    long insertEmail(Email email);

    /**
     * Delete the given {@link Email} from the data store.
     *
     * @param email the {@link Email} to delete
     * @return {@code 1} if the {@link Email} was deleted, {@code 0} otherwise
     */
    @Delete
    int deleteEmail(Email email);

    /**
     * Find all {@link Email}s in the data store in ascending order by timestamp.
     *
     * @return {@code List<Email>} in ascending order by timestamp
     */
    @Query("SELECT * From Email ORDER BY timestamp ASC")
    List<Email> findAllEmails();
}

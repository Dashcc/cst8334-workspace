package com.example.cst8334project.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.cst8334project.domain.Visit;

import java.util.List;

/**
 * Provides CRUD functionalities for {@link Visit}s.
 */
@Dao
public interface VisitDAO {

    /**
     * Insert the given {@link Visit} into the data store.
     *
     * @param visit the {@link Visit} to insert into the data store.
     * @return the id of the inserted {@link Visit}
     */
    @Insert
    long insertVisit(Visit visit);

    /**
     * Find all {@link Visit}s in descending order of created date.
     *
     * @return List of all {@link Visit}s in descending order of created date
     */
    @Query("SELECT * FROM visit ORDER BY created_at DESC")
    List<Visit> findAllVisits();

    /**
     * Delete the given {@link Visit} from the data store.
     *
     * @param visit the {@link Visit} to delete from the data store
     */
    @Delete
    void deleteVisit(Visit visit);

    /**
     * Delete the {@link Visit} identified by the given id from the data store.
     *
     * @param visitId the id of the {@link Visit} to delete from the data store.
     */
    @Query("DELETE FROM visit WHERE id = :visitId")
    void deleteVisitById(int visitId);

    /**
     * Update the given {@link Visit} in the data store.
     *
     * @param visit the {@link Visit} to update
     */
    @Update
    void updateVisit(Visit visit);

    /**
     * Find the number of {@link Visit}s in the data store.
     *
     * @return the number of {@link Visit}s in the data store
     */
    @Query("SELECT count(*) FROM visit")
    int visitCount();

    /**
     * Find the {@link Visit} identified by the given unique id.
     *
     * @param visitId the id of the {@link Visit} to retrieve
     * @return the {@link Visit} identified by the given unique id if it exists, null otherwise
     */
    @Query("SELECT * FROM visit WHERE id = :visitId")
    Visit findVisitById(int visitId);

    /**
     * Delete all {@link Visit}s from the data store.
     */
    @Query("DELETE FROM visit")
    void deleteAllVisits();
}

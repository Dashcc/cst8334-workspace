package com.example.cst8334project.userhistoryservice;

import java.util.List;

/**
 * Provides service level methods for {@link Visit}s.
 */
public interface VisitService {

    /**
     * Add a {@link Visit} to the user's history.
     *
     * @param visit the {@link Visit} to add
     */
    void addVisit(Visit visit);

    /**
     * Update a {@link Visit} in the user's history.
     *
     * @param visit the {@link Visit} to update
     */
    void updateVisit(Visit visit);

    /**
     * Delete the {@link Visit} identified by the given id from the user's history.
     *
     * @param visitId the id that uniquely identifies the {@link Visit} to be deleted
     */
    void deleteVisitById(int visitId);

    /**
     * Delete the given {@link Visit} from the user's history.
     *
     * @param visit the {@link Visit} to delete
     */
    void deleteVisit(Visit visit);

    /**
     * Find the {@link Visit} identified by the given id in the user's history.
     *
     * @param visitId the id that uniquely identifies the {@link Visit} to be found
     * @return the {@link Visit} identified by the given id in the user's history if it
     * exists, null otherwise
     */
    Visit findVisitById(int visitId);

    /**
     * Find all {@link Visit}s in the user's history sorted by created date in descending order.
     *
     * @return a {@link List} containing all {@link Visit}s in the user's history sorted by
     * created date in descending order
     */
    List<Visit> findAllVisits();

    /**
     * Find the total number of {@link Visit}s in the user's history.
     *
     * @return the total number of {@link Visit}s in the user's history
     */
    int numberOfVisits();

    /**
     * Clear all {@link Visit}s from the user's history.
     */
    void clearAllVisits();
}

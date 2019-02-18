package com.example.cst8334project.userhistoryservice;

import android.content.Context;
import android.util.Log;

import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.persistence.HHHDatabase;
import com.example.cst8334project.persistence.VisitDAO;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The implementation of the {@link VisitService} interface.
 */
public class VisitServiceImpl implements VisitService {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = VisitServiceImpl.class.getSimpleName();

    /**
     * Provides CRUD functionality for {@link Visit}s.
     */
    private final VisitDAO visitDAO;

    /**
     * Construct an instance of {@link VisitServiceImpl} for the given {@link Context}.
     *
     * @param context the {@link Context} of the application
     */
    public VisitServiceImpl(Context context) {
        this.visitDAO = HHHDatabase.getInstance(context).getVisitDAO();
    }

    @Override
    public void addVisit(Visit visit) {
        cleanVisitFields(visit);
        Date date = new Date();
        visit.setCreatedDate(date);
        visit.setModifiedDate(date);
        Log.i(CLASS_NAME, "Adding Visit to user history: " + visit);
        long id = visitDAO.insertVisit(visit);
        Log.i(CLASS_NAME, "The id of the new Visit is: " + id);
    }

    @Override
    public void updateVisit(Visit visit) {
        Objects.requireNonNull(visit, "Visit to be updated cannot be null.");
        cleanVisitFields(visit);
        visit.setModifiedDate(new Date());
        Log.i(CLASS_NAME, "Updating Visit: " + visit);
        visitDAO.updateVisit(visit);
    }

    @Override
    public void deleteVisitById(int id) {
        Log.i(CLASS_NAME, "Deleting Visit by id: " + id + " from user's history.");
        visitDAO.deleteVisitById(id);
    }

    @Override
    public void deleteVisit(Visit visit) {
        Objects.requireNonNull(visit, "Visit to be deleted cannot be null.");
        Log.i(CLASS_NAME, "Deleting Visit: " + visit + " from user's history.");
        visitDAO.deleteVisit(visit);
    }

    @Override
    public Visit findVisitById(int id) {
        Log.i(CLASS_NAME, "Finding Visit by id: " + id);
        Visit visit = visitDAO.findVisitById(id);
        Log.i(CLASS_NAME, "Visit with id: " + id + " is: " + visit);
        return visit;
    }

    @Override
    public List<Visit> findAllVisits() {
        Log.i(CLASS_NAME, "Fetching all Visits in descending created by date");
        List<Visit> allVisits = visitDAO.findAllVisits();
        Log.i(CLASS_NAME, "List of Visits: " + allVisits);
        return allVisits;
    }

    @Override
    public int numberOfVisits() {
        Log.i(CLASS_NAME, "Fetching the number of Visits in the user's history.");
        int count = visitDAO.visitCount();
        Log.i(CLASS_NAME, "The number of total Visits is: " + count);
        return count;
    }

    @Override
    public void clearAllVisits() {
        visitDAO.deleteAllVisits();
        Log.i(CLASS_NAME, "Cleared all Visits from the user's history.");
    }

    /**
     * "Clean" the fields of the given {@link Visit}. Used before updating or inserting
     * the {@link Visit} into the database.
     *
     * @param visit the {@link Visit} to clean
     */
    private void cleanVisitFields(Visit visit) {
        visit.setUserNote(StringUtils.trimToEmpty(visit.getUserNote()));
    }
}

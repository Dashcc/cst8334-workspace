package com.example.cst8334project.userhistoryservice;

import android.util.Log;

import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.persistence.HHHDatabase;
import com.example.cst8334project.persistence.VisitDAO;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * The implementation of the {@link VisitService} interface as an enum.
 */
public enum VisitServiceImpl implements VisitService {

    /**
     * The singleton instance for this service's implementation.
     */
    INSTANCE;

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = VisitServiceImpl.class.getSimpleName();

    /**
     * Provides CRUD functionality for {@link Visit}s.
     */
    private final VisitDAO visitDAO;

    /**
     * The {@link ExecutorService} object used to execute {@link VisitDAO} methods on a separate
     * thread.
     */
    private final ExecutorService executor;

    /**
     * Private constructor that constructs an instance of {@link VisitServiceImpl}.
     */
    VisitServiceImpl() {
        this.visitDAO = HHHDatabase.getInstance().getVisitDAO();
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void addVisit(final Visit visit) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cleanVisitFields(visit);
                Date date = new Date();
                visit.setCreatedDate(date);
                Log.i(CLASS_NAME, "Adding Visit to user history: " + visit);
                long id = visitDAO.insertVisit(visit);
                Log.i(CLASS_NAME, "The id of the new Visit is: " + id);
            }
        });
    }

    @Override
    public void updateVisit(final Visit visit) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Objects.requireNonNull(visit, "Visit to be updated cannot be null.");
                cleanVisitFields(visit);
                Log.i(CLASS_NAME, "Updating Visit: " + visit);
                visitDAO.updateVisit(visit);
            }
        });
    }

    @Override
    public void deleteVisitById(final int id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i(CLASS_NAME, "Deleting Visit by id: " + id + " from user's history.");
                visitDAO.deleteVisitById(id);
            }
        });
    }

    @Override
    public void deleteVisit(final Visit visit) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Objects.requireNonNull(visit, "Visit to be deleted cannot be null.");
                Log.i(CLASS_NAME, "Deleting Visit: " + visit + " from user's history.");
                visitDAO.deleteVisit(visit);
            }
        });
    }

    @Override
    public Visit findVisitById(final int id) {
        Future<Visit> visitFuture = executor.submit(new Callable<Visit>() {
            @Override
            public Visit call() {
                Log.i(CLASS_NAME, "Finding Visit by id: " + id);
                Visit visit = visitDAO.findVisitById(id);
                Log.i(CLASS_NAME, "Visit with id: " + id + " is: " + visit);
                return visit;
            }
        });

        try {
            return visitFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(CLASS_NAME, "Encountered an error when trying to find Visit by id: " + id, e);
            return null;
        }
    }

    @Override
    public List<Visit> findAllVisits() {
        Future<List<Visit>> visitFutures = executor.submit(new Callable<List<Visit>>() {
            @Override
            public List<Visit> call() {
                Log.i(CLASS_NAME, "Fetching all Visits in descending created by date");
                List<Visit> allVisits = visitDAO.findAllVisits();
                Log.i(CLASS_NAME, "List of Visits: " + allVisits);
                return allVisits;
            }
        });

        try {
            return visitFutures.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(CLASS_NAME, "Encountered an error when trying to find all Visits.", e);
            return Collections.emptyList();
        }
    }

    @Override
    public int numberOfVisits() {
        Future<Integer> visitCountFuture = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                Log.i(CLASS_NAME, "Fetching the number of Visits in the user's history.");
                int count = visitDAO.visitCount();
                Log.i(CLASS_NAME, "The number of total Visits is: " + count);
                return count;
            }
        });

        try {
            return visitCountFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e(CLASS_NAME, "Encountered an error when trying to find the number of Visits.", e);
            return -1;
        }
    }

    @Override
    public void clearAllVisits() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                visitDAO.deleteAllVisits();
                Log.i(CLASS_NAME, "Cleared all Visits from the user's history.");
            }
        });
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

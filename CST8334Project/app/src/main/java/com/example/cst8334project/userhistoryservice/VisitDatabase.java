package com.example.cst8334project.userhistoryservice;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.cst8334project.util.Converters;

/**
 * The database for storing, retrieving, and modifying {@link Visit}s.
 */
@Database(entities = {Visit.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class VisitDatabase extends RoomDatabase {

    /**
     * The name of the database.
     */
    private static final String DATABASE_NAME = "VISITS_DATABASE.db";

    /**
     * The singleton database instance.
     */
    private static volatile VisitDatabase instance;

    /**
     * Get the singleton instance of the {@link VisitDatabase}.
     *
     * @param context the {@link Context} for the application
     * @return the singleton instance of the {@link VisitDatabase}
     */
    public static synchronized VisitDatabase getInstance(Context context) {
        if (instance == null) {
            instance = createDatabase(context);
        }
        return instance;
    }

    /**
     * Create the database using the given {@link Context}.
     *
     * @param context the {@link Context} for the application
     * @return the fully constructed {@link VisitDatabase} instance
     */
    private static VisitDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context, VisitDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    /**
     * Get the {@link VisitDAO} object used to execute CRUD statements for {@link Visit}s.
     *
     * @return the {@link VisitDAO} object
     */
    public abstract VisitDAO getVisitDAO();
}

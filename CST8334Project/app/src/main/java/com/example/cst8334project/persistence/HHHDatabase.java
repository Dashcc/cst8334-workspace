package com.example.cst8334project.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.cst8334project.config.HeartHouseHospiceApp;
import com.example.cst8334project.domain.Email;
import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.util.Converters;

/**
 * The database for storing, retrieving, and modifying the domain objects for this application.
 */
@Database(entities = {Visit.class, Email.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class HHHDatabase extends RoomDatabase {

    /**
     * The name of the database.
     */
    private static final String DATABASE_NAME = "HHH_DATABASE.db";

    /**
     * The singleton database instance.
     */
    private static volatile HHHDatabase instance;

    /**
     * Get the singleton instance of the {@link HHHDatabase}.
     */
    public static synchronized HHHDatabase getInstance() {
        if (instance == null) {
            instance = createDatabase();
        }
        return instance;
    }

    /**
     * Create the database.
     *
     * @return the fully constructed {@link HHHDatabase} instance
     */
    private static HHHDatabase createDatabase() {
        return Room.databaseBuilder(HeartHouseHospiceApp.getAppContext(), HHHDatabase.class, DATABASE_NAME)
                   .fallbackToDestructiveMigration()
                   .build();
    }

    /**
     * Get the {@link VisitDAO} object used to execute CRUD statements for {@link Visit}s.
     *
     * @return the {@link VisitDAO} object
     */
    public abstract VisitDAO getVisitDAO();

    /**
     * Get the {@link EmailDAO} object used to execute CRUD statements for {@link Email}s.
     *
     * @return the {@link EmailDAO} object
     */
    public abstract EmailDAO getEmailDAO();
}

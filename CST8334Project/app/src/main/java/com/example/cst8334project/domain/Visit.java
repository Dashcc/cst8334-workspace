package com.example.cst8334project.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * A class that encapsulates the information stored in the user's visit history.
 */
@Entity
public class Visit {

    /**
     * The unique ID for this Visit.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * The created at date for this Visit entry
     */
    @ColumnInfo(name = "created_at")
    private Date createdDate;

    /**
     * The visit time for this Visit entry
     */
    @ColumnInfo(name = "visit_time")
    private String visitTime;

    /**
     * The number of clients served for this Visit entry
     */
    @ColumnInfo(name = "number_people")
    private String numberPeople;

    /**
     * The note that the user can add for this Visit.
     */
    @ColumnInfo(name = "user_note")
    private String userNote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getVisitTime() {
        return visitTime;
    }
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(String numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}

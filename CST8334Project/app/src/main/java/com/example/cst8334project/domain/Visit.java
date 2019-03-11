package com.example.cst8334project.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static org.apache.commons.lang3.StringUtils.LF;

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
     * The number of clients served for this Visit entry
     */
    @ColumnInfo(name = "service_type")
    private String serviceType;

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


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", serviceType='" + serviceType + '\'' +
                ", userNote='" + userNote + '\'' +
                '}';
    }
}

package com.example.cst8334project.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * This class encapsulates an email message.
 */
@Entity
public class Email {

    /**
     * The unique timestamp for this email.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * The subject of the email.
     */
    @ColumnInfo(name = "subject")
    private String subject;

    /**
     * The body of the email.
     */
    @ColumnInfo(name = "body")
    private String body;

    /**
     * The name to use for the CSV attachment file in the email.
     */
    @ColumnInfo(name = "csv_file_name")
    private String csvAttachmentFileName;

    /**
     * The text for the CSV attachment.
     */
    @ColumnInfo(name = "csv_text")
    private String attachmentText;

    /**
     * The email timestamp.
     */
    @ColumnInfo(name = "timestamp")
    private Date emailTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCsvAttachmentFileName() {
        return csvAttachmentFileName;
    }

    public void setCsvAttachmentFileName(String csvAttachmentFileName) {
        this.csvAttachmentFileName = csvAttachmentFileName;
    }

    public String getAttachmentText() {
        return attachmentText;
    }

    public void setAttachmentText(String attachmentText) {
        this.attachmentText = attachmentText;
    }

    public Date getEmailTimestamp() {
        return emailTimestamp;
    }

    public void setEmailTimestamp(Date emailTimestamp) {
        this.emailTimestamp = emailTimestamp;
    }

    @Override
    public String toString() {
        return "Email{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", csvAttachmentFileName='" + csvAttachmentFileName + '\'' +
                ", attachmentText='" + attachmentText + '\'' +
                ", emailTimestamp=" + emailTimestamp +
                '}';
    }
}

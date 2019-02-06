package com.example.cst8334project.emailservice;

/**
 * This class encapsulates an email message.
 */
public class Email {

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The body of the email.
     */
    private String body;

    /**
     * The absolute path to the CSV attachment file.
     */
    private String csvAttachmentAbsolutePath;

    /**
     * The name to use for the CSV attachment file in the email.
     */
    private String csvAttachmentFileName;

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

    public String getCsvAttachmentAbsolutePath() {
        return csvAttachmentAbsolutePath;
    }

    public void setCsvAttachmentAbsolutePath(String csvAttachmentAbsolutePath) {
        this.csvAttachmentAbsolutePath = csvAttachmentAbsolutePath;
    }

    public String getCsvAttachmentFileName() {
        return csvAttachmentFileName;
    }

    public void setCsvAttachmentFileName(String csvAttachmentFileName) {
        this.csvAttachmentFileName = csvAttachmentFileName;
    }
}

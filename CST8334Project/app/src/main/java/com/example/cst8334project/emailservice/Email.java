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
     * The name of the original CSV attachment file.
     */
    private String csvAttachmentOriginalFileName;

    /**
     * The name to use for the CSV attachment file in the email.
     */
    private String csvAttachmentNewFileName;

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

    public String getCsvAttachmentOriginalFileName() {
        return csvAttachmentOriginalFileName;
    }

    public void setCsvAttachmentOriginalFileName(String csvAttachmentOriginalFileName) {
        this.csvAttachmentOriginalFileName = csvAttachmentOriginalFileName;
    }

    public String getCsvAttachmentNewFileName() {
        return csvAttachmentNewFileName;
    }

    public void setCsvAttachmentNewFileName(String csvAttachmentNewFileName) {
        this.csvAttachmentNewFileName = csvAttachmentNewFileName;
    }
}

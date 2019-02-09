package com.example.cst8334project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cst8334project.emailservice.Email;
import com.example.cst8334project.emailservice.SendEmailActivity;
import com.example.cst8334project.userhistoryservice.Visit;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The subject of the email
        final String emailSubject = "Volunteer Visit - " + new Date().toString();

        // The body of the email
        final String emailBody = "Please find attached the information for a volunteer visit.";

        // The text to write to the CSV file
        final String emailAttachmentText = "Name, Duration, Location\nJohn, 90 minutes, Ottawa";

        // The CSV attachment file name
        final String emailAttachmentFileName = "HHH - " + emailSubject + ".csv";

        // Create the Email object and add the subject, body, and attachment text along
        // with a name for the attachment file
        Email email = new Email();
        email.setSubject(emailSubject);
        email.setBody(emailBody);
        email.setAttachmentText(emailAttachmentText);
        email.setCsvAttachmentFileName(emailAttachmentFileName);

        // Create the Visit object corresponding to the email
        Visit visit = new Visit();
        visit.setUserNote("This visit was OK.");

        // Send the Email and add the Visit to the user history
        new SendEmailActivity(this, email, visit).execute();
    }
}

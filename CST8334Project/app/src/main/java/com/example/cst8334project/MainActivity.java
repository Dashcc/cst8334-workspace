package com.example.cst8334project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cst8334project.emailservice.Email;
import com.example.cst8334project.emailservice.SendEmailActivity;
import com.example.cst8334project.util.FileUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // A CSV file to create
        final String fileName = "test.csv";

        // Write text to the file
        FileUtils.writeTextToFile(this, fileName, "Country,Capital\nCanada,Ottawa");

        // Create the Email object and attach the created file
        Email email = new Email();
        email.setSubject("Subject");
        email.setBody("Body");
        email.setCsvAttachmentOriginalFileName(fileName);
        email.setCsvAttachmentNewFileName("Information for Volunteer John.csv");

        // Send the email
        new SendEmailActivity(this, email).execute();
    }
}

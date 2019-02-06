package com.example.cst8334project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.cst8334project.emailservice.Email;
import com.example.cst8334project.emailservice.SendEmailActivity;
import com.example.cst8334project.util.FileUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email email = new Email();
        email.setSubject("Subject");
        email.setBody("Body");
        email.setCsvAttachmentAbsolutePath(this.getFilesDir().getPath() + "/Data.csv");
        email.setCsvAttachmentFileName("Information for Volunteer John");

        new SendEmailActivity(this, email).execute();
    }
}

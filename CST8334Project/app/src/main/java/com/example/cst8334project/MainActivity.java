package com.example.cst8334project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cst8334project.emailservice.SendEmailActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SendEmailActivity(this, "Test email", "Test body").execute();
    }
}

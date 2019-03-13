package com.example.cst8334project;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.cst8334project.calendar.CalendarActivity;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button timesheet = findViewById(R.id.timesheet);
        Button appointment = findViewById(R.id.appointment);
        Button history = findViewById(R.id.history);

        timesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent intent = new Intent(MainMenu.this, VolunteerInfoActivity.class);
                startActivity(intent);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent intent = new Intent(MainMenu.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent intent = new Intent(MainMenu.this, UserHistoryActivity.class);
                startActivity(intent);
            }
        });
    }

}

package com.example.cst8334project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cst8334project.calendar.CalendarActivity;

public class MainMenu extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupDrawer();

        Button timesheetBtn = findViewById(R.id.mainMenuTimesheet);
        Button apptBtn = findViewById(R.id.mainMenuAppointment);
        Button historyBtn = findViewById(R.id.mainMenuHistory);

        timesheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, VolunteerInfoActivity.class));
            }
        });

        apptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, CalendarActivity.class));
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, UserHistoryActivity.class));
            }
        });
    }
}



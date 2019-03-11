package com.example.cst8334project.calendar;


import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.cst8334project.R;
import com.example.cst8334project.VolunteerInfoActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import android.provider.CalendarContract;

public class CalendarActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private DatePicker datePicker;
    private EditText location;
    private EditText title;
    private EditText duration;
    private EditText description;
    private boolean eventCreated = false;

    private Button createEvent;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1;
    protected static final String ACTIVITY_NAME = "CalendarActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ACTIVITY_NAME, "In OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        timePicker1 = findViewById(R.id.timePicker1);
        datePicker = findViewById(R.id.datePicker1);
        location = findViewById(R.id.location);
        title = findViewById(R.id.type);
        duration = findViewById(R.id.duration);
        description = findViewById(R.id.description);

        createEvent = findViewById(R.id.create_event);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                newEvent();

            }
        });
    }

    @Override
    protected void onStop() {
        Log.i(ACTIVITY_NAME, "In OnStop()");
        eventCreated = true;
        super.onStop();
    }


    @Override
    protected void onResume() {
        Log.i(ACTIVITY_NAME, "In OnResume()");

        if (eventCreated) {
            Intent intent = new Intent(CalendarActivity.this, VolunteerInfoActivity.class);
            startActivity(intent);
        }

        super.onResume();
    }

    private void newEvent() {

        String apptLocation = location.getText().toString();
        String type = title.getText().toString();
        String desc = description.getText().toString();

        Integer endTime;
        try {
            endTime = new Integer(duration.getText().toString());
        } catch (Exception e) {
            endTime = 1;
        }

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();


        Date dtstart;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = day + "-" + month + "-" + year + " " + hour + ":" + min + ":00";  //"31-08-1982 10:20:56";
        try {
            dtstart = sdf.parse(dateInString);
        } catch (ParseException p) {
            dtstart = new Date();
        }

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.CALENDAR_ID, 1)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, dtstart.getTime())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, dtstart.getTime() + 3600000 * endTime)
                .putExtra(CalendarContract.Events.TITLE, type)
                .putExtra(CalendarContract.Events.DESCRIPTION, desc)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, apptLocation)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(CalendarContract.Events.HAS_ALARM, 1);

        startActivityForResult(intent, 1);

    }


}

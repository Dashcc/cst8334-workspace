package com.example.cst8334project.calendar;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.cst8334project.BaseActivity;
import com.example.cst8334project.MainMenu;
import com.example.cst8334project.R;
import com.example.cst8334project.VolunteerInfoActivity;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import static com.example.cst8334project.forms.util.FormUtils.TIME_FORMAT;

public class CalendarActivity extends BaseActivity {

    private EditText location;
    private EditText title;
    private EditText duration;
    private EditText description;

    private int aptMinute, aptHour, aptDay, aptMonth, aptYear;

    private boolean eventCreated = false;

    private Button createEvent;
    protected static final String ACTIVITY_NAME = "CalendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ACTIVITY_NAME, "In OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        setupDrawer();

        location = findViewById(R.id.cal_location);
        title = findViewById(R.id.apt_type);
        duration = findViewById(R.id.cal_duration);
        description = findViewById(R.id.cal_desc);
        createEvent = findViewById(R.id.btn_create_event);

        final Calendar calendar = Calendar.getInstance();

        final TextView editDate = findViewById(R.id.cal_date);

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        aptDay = dayOfMonth;
                        aptMonth = month;
                        aptYear = year;

                        String formattedDate = String.format(Locale.US, "%d/%02d/%02d", year, month + 1, dayOfMonth);
                        editDate.setText(formattedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        final EditText editTime = findViewById(R.id.cal_time);

        editTime.setOnClickListener(new View.OnClickListener() {

            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinutes = calendar.get(Calendar.MINUTE);

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(CalendarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        aptMinute = minute;
                        aptHour = hourOfDay;
                        editTime.setText(String.format(Locale.ENGLISH, TIME_FORMAT, aptHour, aptMinute));

                        Time time = new Time(aptHour, aptMinute, 0);
                        Format formatter = new SimpleDateFormat("h:mm a", Locale.US);
                        String timeText = formatter.format(time);
                        editTime.setText(timeText);
                    }
                }, currentHour, currentMinutes, false);

                timePickerDialog.show();
            }
        });

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
            Intent intent = new Intent(CalendarActivity.this, MainMenu.class);
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
            endTime = Integer.valueOf(duration.getText().toString());
        } catch (Exception e) {
            endTime = 1;
        }


        Date dtstart;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.US);
        String dateInString = aptDay + "-" + aptMonth + "-" + aptYear + " " + aptHour + ":" + aptMinute + ":00";  //"31-08-1982 10:20:56";
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

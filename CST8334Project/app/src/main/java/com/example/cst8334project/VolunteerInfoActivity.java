package com.example.cst8334project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.cst8334project.forms.BaseForm;
import com.example.cst8334project.forms.DirectServiceForm;
import com.example.cst8334project.forms.IndirectServiceForm;
import com.example.cst8334project.forms.util.FormUtils;
import com.example.cst8334project.util.FileUtils;

import java.util.Calendar;

public class VolunteerInfoActivity extends BaseActivity {

    protected static final String ACTIVITY_NAME = "VolunteerInfoActivity";
    private static final String DATE_FORMAT = "%d/%02d/%02d";
    private static final String VOLUN_NAME_KEY = "volunteer_name";

    String name;
    String date;
    EditText editName;
    EditText editDate;
    CheckBox CBStudent;
    Button btnDirect;
    Button btnIndirect;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_info);
        setupDrawer();

        editName = findViewById(R.id.edit_name);
        editDate = findViewById(R.id.edit_date);
        CBStudent = findViewById(R.id.CBStudent);
        btnDirect = findViewById(R.id.direct_btn);
        btnIndirect = findViewById(R.id.indirect_btn);

        // Try to find the name in the Shared Preferences
        name = FileUtils.readFromSharedPreferences(VOLUN_NAME_KEY, null);

        // If the name exists in the Shared Preferences, set its value to the edit text
        if (name != null) {
            editName.setText(name);
        }

        Calendar calendar = Calendar.getInstance();

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        // By default, the date edit text shows the current date
        String today = String.format(DATE_FORMAT, year, month + 1, day);
        editDate.setText(today);

        // Show a DatePickerDialog to let the user select a date
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(VolunteerInfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDayOfMonth) {
                        date = String.format("%d/%02d/%02d", mYear, mMonth + 1, mDayOfMonth);
                        editDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text values for the name and the date
                name = editName.getText().toString();
                date = editDate.getText().toString();

                // Update the volunteer's name in the Shared Preferences
                FileUtils.writeToSharedPreferences(VOLUN_NAME_KEY, name);

                // Create an intent to move to launch the DirectActivity
                Intent intent = new Intent(VolunteerInfoActivity.this, DirectActivity.class);

                // Create a direct service form and set its name and date
                BaseForm form = new DirectServiceForm();
                form.setName(name);
                form.setDate(date);
                form.setStudentPlacement(CBStudent.isChecked());

                // Add the DirectServiceForm object to the intent
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, form);

                // Launch the DirectActivity
                startActivityForResult(intent, 50);
            }
        });

        btnIndirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text values for the name and the date
                name = editName.getText().toString();
                date = editDate.getText().toString();

                // Update the volunteer's name in the Shared Preferences
                FileUtils.writeToSharedPreferences(VOLUN_NAME_KEY, name);

                // Create an intent to move to launch the IndirectActivity
                Intent intent = new Intent(VolunteerInfoActivity.this, IndirectActivity.class);

                // Create an IndirectServiceForm and populate its name and date fields
                BaseForm form = new IndirectServiceForm();
                form.setName(name);
                form.setDate(date);
                form.setStudentPlacement(CBStudent.isChecked());

                // Add the IndirectServiceForm object to the intent and start the activity
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, form);
                startActivity(intent);
            }
        });
    }
}





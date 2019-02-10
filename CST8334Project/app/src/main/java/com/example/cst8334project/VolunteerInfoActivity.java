package com.example.cst8334project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class VolunteerInfoActivity extends Activity {
    ProgressDialog progressDialog;
    String name,date,numberOfPeople,time,note;

    EditText editName, editDate, editPeople, editTime,editNote;
    Button btnSubmit;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6,checkbox7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_info);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        editName = findViewById(R.id.edit_name);
        editDate = findViewById(R.id.edit_date);
        editPeople = findViewById(R.id.edit_people);
        editTime = findViewById(R.id.edit_time);


        checkbox1 = findViewById(R.id.CB1);
        checkbox2 = findViewById(R.id.CB2);
        checkbox3 = findViewById(R.id.CB3);
        checkbox4 = findViewById(R.id.CB4);
        checkbox5 = findViewById(R.id.CB5);
        checkbox6 = findViewById(R.id.CB6);
        checkbox7 = findViewById(R.id.CB7);

        editNote = findViewById(R.id.edit_notes);
        btnSubmit = findViewById(R.id.button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();  // call when button is clicked to validate the input

            }
        });
    }
        private void submitForm(){
             initialize();
             if (!validate()){
                 Toast.makeText(this, "Sign up has Failed", Toast.LENGTH_SHORT).show();
             }
             else {
                 onLoginSuccess();
             }
    }
        public void onLoginSuccess() {

        }
        public boolean validate(){
            boolean valid = true;
            if (name.isEmpty() || name.length() > 30) {
                editName.setError("Please enter valid Name");
                valid = false;
            }
            if (date.isEmpty()) {
                editDate.setError("Please enter valid Date");
                valid = false;
            }
            if (numberOfPeople.isEmpty()) {
                editPeople.setError("Please enter  valid number of people during visit");
                valid = false;

            }
            if (time.isEmpty()) {
                editTime.setError("Please enter valid time during visit ");
                valid = false;

            }
            return valid;
        }

        private void initialize(){
            name = editName.getText().toString().trim();
            date = editDate.getText().toString().trim();
            numberOfPeople = editPeople.getText().toString().trim();
            time = editTime.getText().toString().trim();
            note = editNote.getText().toString().trim();

        }
    }


package com.example.cst8334project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.emailservice.EmailSenderAsyncTask;
import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.userhistoryservice.VisitServiceImpl;

import java.util.Date;

public class VolunteerInfoActivity extends Activity {
    ProgressDialog progressDialog;
    String name,date,numberOfPeople,time,note;
    String inHome = "no";
    String bereav = "no";
    String spirit = "no";
    String tt = "no";
    String reiki = "no";
    String aroma = "no";
    String massage = "no";

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

            // The subject of the email
            final String emailSubject = "Volunteer Visit - " + new Date().toString();

            // The body of the email
            final String emailBody = "Please find attached the information for a volunteer visit.";

            // The text to write to the CSV file
            final String emailAttachmentText = "Name, Date, Number of People, Time, In Home, Bereavement, Spiritual Care, TT, Reiki, Aromatherapy, Hand/Arm Massage, Note\n" +
                    ""+name+", "+date+", "+numberOfPeople+", "+time+", "+inHome+", "+bereav+", "+spirit+", "+tt+", "+reiki+", "+aroma+", "+massage+", "+note;

            // The CSV attachment file name
            final String emailAttachmentFileName = "HHH - " + emailSubject + ".csv";

            // Create the Email object and add the subject, body, and attachment text along
            // with a name for the attachment file
            Email email = new Email();
            email.setSubject(emailSubject);
            email.setBody(emailBody);
            email.setAttachmentText(emailAttachmentText);
            email.setCsvAttachmentFileName(emailAttachmentFileName);

            // Send the Email
            new EmailSenderAsyncTask(this).execute(email);

            // Create the Visit object corresponding to the email
            Visit visit = new Visit();
            visit.setUserNote("This visit was OK.");

            // Add the Visit to the user history
            new VisitServiceImpl(this).addVisit(visit);

            Intent intent = new Intent(VolunteerInfoActivity.this, VolunteerInfoActivity.class);

            startActivity(intent);

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
            if(checkbox1.isChecked()){
                inHome = "yes";
            }

            if(checkbox2.isChecked()){
                bereav = "yes";
            }

            if(checkbox3.isChecked()){
                spirit = "yes";
            }

            if(checkbox4.isChecked()){
                 tt = "yes";
            }

            if(checkbox5.isChecked()){
                 reiki = "yes";;
            }

            if(checkbox6.isChecked()){
                aroma = "yes";
            }

            if(checkbox7.isChecked()){
                massage= "yes";
            }

        }
    }


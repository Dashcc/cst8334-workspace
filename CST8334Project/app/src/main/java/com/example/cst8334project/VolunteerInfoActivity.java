package com.example.cst8334project;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst8334project.emailservice.Email;
import com.example.cst8334project.emailservice.SendEmailActivity;
import com.example.cst8334project.userhistoryservice.Visit;

import java.util.Date;

public class VolunteerInfoActivity extends Activity {

    protected static final String ACTIVITY_NAME = "VolunteerInfoActivity";

    String name;
    String date;
    EditText editName;
    EditText editDate;
    Button btnDirect;
    Button btnIndirect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_info);
        Log.i(ACTIVITY_NAME,"In OnCreate()");


        editName = findViewById(R.id.edit_name);
        editDate = findViewById(R.id.edit_date);
        btnDirect = findViewById(R.id.direct_btn);
        btnIndirect = findViewById(R.id.indirect_btn);


        btnDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerInfoActivity.this, DirectActivity.class);
                startActivityForResult(intent,50);

            }
        });
        btnIndirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(VolunteerInfoActivity.this, IndirectActivity.class);
                startActivityForResult(intent,50);

            }
        });
    }

    protected void onStart() {
                super.onStart();
                Log.i(ACTIVITY_NAME,"In onStart()");
            }

            protected void onResume(){
                super.onResume();
                Log.i(ACTIVITY_NAME,"In onResume()");
            }

            protected void onPause() {
                super.onPause();
                Log.i(ACTIVITY_NAME,"In onPause()");
            }

            protected void onStop(){
                super.onStop();
                Log.i(ACTIVITY_NAME,"In onStop()");
            }

            protected void 	onDestroy(){
                super.onDestroy();
                Log.i(ACTIVITY_NAME,"In onDestroy()");
            }

        }








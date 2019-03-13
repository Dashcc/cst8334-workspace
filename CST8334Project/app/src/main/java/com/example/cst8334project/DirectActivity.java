package com.example.cst8334project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cst8334project.forms.DirectServiceForm;
import com.example.cst8334project.forms.InHomeForm;
import com.example.cst8334project.forms.InOfficeForm;
import com.example.cst8334project.forms.util.FormUtils;

public class DirectActivity extends Activity {

    protected static final String ACTIVITY_NAME = "DirectActivity";

    Button inHome;
    Button inOffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);

        inHome = findViewById(R.id.inHome_btn);
        inOffice = findViewById(R.id.inOffice_btn);

        // Obtain the DirectServiceForm object from the Intent extra
        final DirectServiceForm directServiceForm = (DirectServiceForm) getIntent().getSerializableExtra("form");

        inHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DirectActivity.this, InHomeActivity.class);

                // Construct an InHomeForm object from the DirectServiceForm object and add it to the Intent
                InHomeForm inHomeForm = new InHomeForm(directServiceForm);
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, inHomeForm);

                startActivityForResult(intent,50);

            }
        });
        inOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectActivity.this, InOfficeActivity.class);

                //  Construct an InOfficeForm object from the DirectServiceForm object and add it to the Intent
                InOfficeForm inOfficeForm = new InOfficeForm(directServiceForm);
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, inOfficeForm);

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









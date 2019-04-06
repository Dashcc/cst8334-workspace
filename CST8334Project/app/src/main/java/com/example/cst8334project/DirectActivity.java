package com.example.cst8334project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cst8334project.forms.DirectServiceForm;
import com.example.cst8334project.forms.InHomeForm;
import com.example.cst8334project.forms.InOfficeForm;
import com.example.cst8334project.forms.util.FormUtils;

public class DirectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);

        setupDrawer();

        Button inHome = findViewById(R.id.inHome_btn);
        Button inOffice = findViewById(R.id.inOffice_btn);

        // Obtain the DirectServiceForm object from the Intent extra
        final DirectServiceForm directServiceForm = (DirectServiceForm) getIntent().getSerializableExtra("form");

        inHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DirectActivity.this, InHomeActivity.class);

                // Construct an InHomeForm object from the DirectServiceForm object and add it to the Intent
                InHomeForm inHomeForm = new InHomeForm(directServiceForm);
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, inHomeForm);

                startActivityForResult(intent, 50);

            }
        });

        inOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DirectActivity.this, InOfficeActivity.class);

                //  Construct an InOfficeForm object from the DirectServiceForm object and add it to the Intent
                InOfficeForm inOfficeForm = new InOfficeForm(directServiceForm);
                intent.putExtra(FormUtils.FORM_INTENT_OBJECT_NAME, inOfficeForm);

                startActivityForResult(intent, 50);
            }
        });
    }
}









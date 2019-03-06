package com.example.cst8334project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class IndirectActivity extends Activity {
    String admin = "no";
    String fundraisingEvent = "no";
    String board = "no";
    String training = "no";
    String outreach = "no";
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indirect);


        checkbox1 = findViewById(R.id.CB1);
        checkbox2 = findViewById(R.id.CB2);
        checkbox3 = findViewById(R.id.CB3);
        checkbox4 = findViewById(R.id.CB4);
        checkbox5 = findViewById(R.id.CB5);

        btnSubmit = findViewById(R.id.btn_directSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(IndirectActivity.this, VolunteerInfoActivity.class);
                    startActivityForResult(intent,50);

                submitForm();


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

    public boolean validate() {
        return validate() ;

    }

    private void initialize(){

        if(checkbox1.isChecked()){
            admin = "yes";
        }

        if(checkbox2.isChecked()){
            fundraisingEvent = "yes";
        }

        if(checkbox3.isChecked()){
            board = "yes";
        }

        if(checkbox4.isChecked()){
            training = "yes";
        }

        if(checkbox5.isChecked()){
            outreach = "yes";
        }



    }
}


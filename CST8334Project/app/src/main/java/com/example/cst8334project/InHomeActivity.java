package com.example.cst8334project;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class InHomeActivity extends Activity {

    TimePickerDialog picker;
    String client_name,numberOfPerson,note;
    String Palliative = "no";
    String Caregiver = "no";
    String Bereaved = "no";
    String Reiki = "no";
    String TT = "no";
    String Aroma = "no";
    String Companioning  = "no";
    String Respite = "no";
    String Spiritual = "no";

    EditText editName,editPerson,editNote;
    EditText eReiki,eTT,eAroma,eCompanioning,eRespite,eSpiritual;

    CheckBox CBPalliative,CBCaregiver,CBBereaved,CBReiki,CBTT,CBAroma,CBCompanioning,CBRespite,CBSpiritual;

    int ReikiHour, ReikiMin;
    int TTHour, TTMin;
    int AromaHour, AromaMin;
    int CompanioningHour, CompanioningMin;
    int RespiteHour, RespiteMin;
    int SpiritualHour, SpiritualMin;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhome);

        CBPalliative = findViewById(R.id.CBPalliative);
        CBCaregiver = findViewById(R.id.CBCaregiver);
        CBBereaved = findViewById(R.id.CBBereaved);
        CBReiki = findViewById(R.id.CBReiki);
        CBTT = findViewById(R.id.CBTT);
        CBAroma = findViewById(R.id.CBAroma);
        CBCompanioning = findViewById(R.id.CBCompanioning);
        CBRespite = findViewById(R.id.CBRespite);
        CBSpiritual = findViewById(R.id.CBSpiritual);

        editName = findViewById(R.id.editTextName);
        editPerson = findViewById(R.id.editTextNumPerson);
        editNote = findViewById(R.id.edit_notes);
        eReiki = findViewById(R.id.editTextReiki);
        eTT = findViewById(R.id.editTextTT);
        eAroma = findViewById(R.id.editAroma);
        eCompanioning = findViewById(R.id.editCompanioning);
        eRespite = findViewById(R.id.editRespite);
        eSpiritual = findViewById(R.id.editSpiritual);

        eReiki.setInputType(InputType.TYPE_NULL);
        eTT.setInputType(InputType.TYPE_NULL);
        eAroma.setInputType(InputType.TYPE_NULL);
        eCompanioning.setInputType(InputType.TYPE_NULL);
        eRespite.setInputType(InputType.TYPE_NULL);
        eSpiritual.setInputType(InputType.TYPE_NULL);

        CBReiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBReiki.isChecked()) {
                    eReiki.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eReiki.setText(sHour + ":" + sMinute);
                                ReikiHour = sHour;
                                ReikiMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBTT.isChecked()) {
                    eTT.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eTT.setText(sHour + ":" + sMinute);
                                TTHour = sHour;
                                TTMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBAroma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBAroma.isChecked()) {
                    eAroma.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eAroma.setText(sHour + ":" + sMinute);
                                AromaHour = sHour;
                                AromaMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBCompanioning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBCompanioning.isChecked()) {
                    eCompanioning.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eCompanioning.setText(sHour + ":" + sMinute);
                                CompanioningHour = sHour;
                                CompanioningMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBRespite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBRespite.isChecked()) {
                    eRespite.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eRespite.setText(sHour + ":" + sMinute);
                                RespiteHour = sHour;
                                RespiteMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBSpiritual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBSpiritual.isChecked()) {
                    eSpiritual.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eSpiritual.setText(sHour + ":" + sMinute);
                                SpiritualHour = sHour;
                                SpiritualMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        btnSubmit = findViewById(R.id.btn_inHomeSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InHomeActivity.this, VolunteerInfoActivity.class);
                startActivityForResult(intent, 50);

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
        public void onLoginSuccess(){

                Intent intent = new Intent(InHomeActivity.this, VolunteerInfoActivity.class);

                startActivity(intent);

            }

    public boolean validate(){
        boolean valid = true;
        if (client_name.isEmpty() || client_name.length() > 30) {
            editName.setError("Please enter valid Name");
            valid = false;
        }

        if (numberOfPerson.isEmpty()) {
            editPerson.setError("Please enter  valid number of people during visit");
            valid = false;

        }
        return valid;
    }

    private void initialize(){
        client_name = editName.getText().toString().trim();
        numberOfPerson = editPerson.getText().toString().trim();
        note = editNote.getText().toString().trim();

        if(CBPalliative.isChecked()){
            Palliative = "yes";
        }

        if(CBCaregiver.isChecked()){
            Caregiver = "yes";
        }

        if(CBBereaved.isChecked()){
            Bereaved = "yes";
        }

        if(CBReiki.isChecked()){
            Reiki = "yes";
        }

        if(CBTT.isChecked()){
            TT = "yes";
        }

        if(CBAroma.isChecked()){
           Aroma = "yes";
        }

        if(CBCompanioning.isChecked()){
            Companioning= "yes";
        }
        if(CBRespite.isChecked()){
            Respite= "yes";
        }
        if(CBSpiritual.isChecked()){
            Spiritual= "yes";
        }


    }
}

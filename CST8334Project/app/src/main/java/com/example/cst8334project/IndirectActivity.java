package com.example.cst8334project;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.emailservice.EmailSenderAsyncTask;
import com.example.cst8334project.forms.IndirectServiceForm;
import com.example.cst8334project.forms.util.FormUtils;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Calendar;

public class IndirectActivity extends Activity {

    TimePickerDialog picker;
    RadioButton  radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;

    EditText eText1;
    EditText eText2;
    EditText eText3;
    EditText eText4;
    EditText eText5;

    int adminHour, adminMin;
    int boardHour, boardMin;
    int trainingHour, trainingMin;
    int fundraisingEventHour, fundraisingEventMin;
    int outreachHour, outreachMin;

    IndirectServiceForm indirectServiceForm;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indirect);


        radioButton1 = findViewById(R.id.RB1);
        radioButton2 = findViewById(R.id.RB2);
        radioButton3 = findViewById(R.id.RB3);
        radioButton4 = findViewById(R.id.RB4);
        radioButton5 = findViewById(R.id.RB5);

        eText1 = findViewById(R.id.editText1);
        eText2 = findViewById(R.id.editText2);
        eText3 = findViewById(R.id.editText3);
        eText4 = findViewById(R.id.editText4);
        eText5 = findViewById(R.id.editText5);

        eText1.setInputType(InputType.TYPE_NULL);
        eText2.setInputType(InputType.TYPE_NULL);
        eText3.setInputType(InputType.TYPE_NULL);
        eText4.setInputType(InputType.TYPE_NULL);
        eText5.setInputType(InputType.TYPE_NULL);

        // Get the IndirectServiceForm object from the intent extras
        indirectServiceForm = (IndirectServiceForm) getIntent().getSerializableExtra(FormUtils.FORM_INTENT_OBJECT_NAME);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton1.isChecked()) {
                    eText1.setText("");
                    return;
                }

                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(IndirectActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText1.setText(sHour + ":" + sMinute);
                                adminHour = sHour;
                                adminMin = sMinute;
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton2.isChecked()) {
                    eText2.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(IndirectActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText2.setText(sHour + ":" + sMinute);
                                boardHour = sHour;
                                boardMin = sMinute;
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton3.isChecked()) {
                    eText3.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(IndirectActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText3.setText(sHour + ":" + sMinute);
                                trainingHour = sHour;
                                trainingMin = sMinute;
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton4.isChecked()) {
                    eText4.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(IndirectActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText4.setText(sHour + ":" + sMinute);
                                fundraisingEventHour = sHour;
                                fundraisingEventMin = sMinute;
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioButton5.isChecked()) {
                    eText5.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(IndirectActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText5.setText(sHour + ":" + sMinute);
                                outreachHour = sHour;
                                outreachMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        btnSubmit = findViewById(R.id.btn_directSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Sign up has Failed", Toast.LENGTH_SHORT).show();
        } else {
            onLoginSuccess();
        }
    }

    /**
     * Create the {@link Email} object that corresponds to this form and send the email.
     */
    public void onLoginSuccess() {
        Email email = new Email();
        email.setSubject("HHH InDirect Form");
        email.setBody("Please find attached an InDirect Form data");
        email.setCsvAttachmentFileName("InDirect.csv");
        email.setAttachmentText(indirectServiceForm.getAttachmentText());

        new EmailSenderAsyncTask(this).execute(email);
    }

    /**
     * Validate the {@link IndirectServiceForm} by ensuring that atleast one of the checkboxes is
     * checked.
     * @return {@code true} if at least one of the Indirect Form checkboxes is checked, false otherwise
     */
    public boolean validate() {
        return BooleanUtils.or(new boolean[]{radioButton1.isChecked(), radioButton2.isChecked(),
                radioButton3.isChecked(), radioButton4.isChecked(), radioButton5.isChecked()});
    }

    private void initialize() {
        if (radioButton1.isChecked()) {
            indirectServiceForm.addIndirectServiceType(IndirectServiceForm.IndirectServiceType.ADMIN, eText1.getText().toString());
        }

        if (radioButton2.isChecked()) {
            indirectServiceForm.addIndirectServiceType(IndirectServiceForm.IndirectServiceType.BOARD, eText2.getText().toString());
        }

        if (radioButton3.isChecked()) {
            indirectServiceForm.addIndirectServiceType(IndirectServiceForm.IndirectServiceType.TRAINING, eText3.getText().toString());
        }

        if (radioButton4.isChecked()) {
            indirectServiceForm.addIndirectServiceType(IndirectServiceForm.IndirectServiceType.FUNDRAISING, eText4.getText().toString());
        }

        if (radioButton5.isChecked()) {
            indirectServiceForm.addIndirectServiceType(IndirectServiceForm.IndirectServiceType.OUTREACH, eText5.getText().toString());
        }
    }
}


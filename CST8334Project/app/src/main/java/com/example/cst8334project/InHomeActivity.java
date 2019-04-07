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

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.emailservice.EmailSenderAsyncTask;
import com.example.cst8334project.forms.BaseForm;
import com.example.cst8334project.forms.DirectServiceForm;
import com.example.cst8334project.forms.InHomeForm;
import com.example.cst8334project.forms.util.FormUtils;
import com.example.cst8334project.userhistoryservice.VisitServiceImpl;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Calendar;

import static com.example.cst8334project.forms.util.FormUtils.*;

public class InHomeActivity extends BaseActivity {

    TimePickerDialog picker;
    String client_name, numberOfPerson, note;

    EditText editName, editPerson, editNote;
    EditText eReiki, eTT, eAroma, eCompanioning, eRespite, eSpiritual,eArt, eMusic;


    CheckBox CBPalliative, CBCaregiver, CBBereaved, CBReiki, CBTT, CBAroma, CBCompanioning, CBRespite, CBSpiritual, CBArt, CBMusic;

    int ReikiHour, ReikiMin;
    int TTHour, TTMin;
    int AromaHour, AromaMin;
    int CompanioningHour, CompanioningMin;
    int RespiteHour, RespiteMin;
    int SpiritualHour, SpiritualMin;
    int ArtHour, ArtMin;
    int MusicHour, MusicMin;

    InHomeForm inHomeForm;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhome);

        setupDrawer();

        CBPalliative = findViewById(R.id.CBPalliative);
        CBCaregiver = findViewById(R.id.CBCaregiver);
        CBBereaved = findViewById(R.id.CBBereaved);
        CBReiki = findViewById(R.id.CBReiki);
        CBTT = findViewById(R.id.CBTT);
        CBAroma = findViewById(R.id.CBAroma);
        CBCompanioning = findViewById(R.id.CBCompanioning);
        CBRespite = findViewById(R.id.CBRespite);
        CBSpiritual = findViewById(R.id.CBSpiritual);
        CBArt = findViewById(R.id.CBArt);
        CBMusic = findViewById(R.id.CBMusic);


        editName = findViewById(R.id.editTextName);
        editPerson = findViewById(R.id.editTextNumPerson);
        editNote = findViewById(R.id.edit_notes);
        eReiki = findViewById(R.id.editTextReiki);
        eTT = findViewById(R.id.editTextTT);
        eAroma = findViewById(R.id.editAroma);
        eCompanioning = findViewById(R.id.editCompanioning);
        eRespite = findViewById(R.id.editRespite);
        eSpiritual = findViewById(R.id.editSpiritual);
        eArt = findViewById(R.id.editArt);
        eMusic = findViewById(R.id.editMusic);

        eReiki.setInputType(InputType.TYPE_NULL);
        eTT.setInputType(InputType.TYPE_NULL);
        eAroma.setInputType(InputType.TYPE_NULL);
        eCompanioning.setInputType(InputType.TYPE_NULL);
        eRespite.setInputType(InputType.TYPE_NULL);
        eSpiritual.setInputType(InputType.TYPE_NULL);
        eArt.setInputType(InputType.TYPE_NULL);
        eMusic.setInputType(InputType.TYPE_NULL);

        // Obtain the InHomeForm object from the Intent extra
        inHomeForm = (InHomeForm) getIntent().getSerializableExtra(FormUtils.FORM_INTENT_OBJECT_NAME);

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
                                eReiki.setText(String.format(TIME_FORMAT, sHour, sMinute));
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
                                eTT.setText(String.format(TIME_FORMAT, sHour, sMinute));
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
                                eAroma.setText(String.format(TIME_FORMAT, sHour, sMinute));
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
                                eCompanioning.setText(String.format(TIME_FORMAT, sHour, sMinute));
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
                                eRespite.setText(String.format(TIME_FORMAT, sHour, sMinute));
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
                                eSpiritual.setText(String.format(TIME_FORMAT, sHour, sMinute));
                                SpiritualHour = sHour;
                                SpiritualMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBArt.isChecked()) {
                    eArt.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eArt.setText(String.format(TIME_FORMAT, sHour, sMinute));
                                ArtHour = sHour;
                                ArtMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        CBMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CBMusic.isChecked()) {
                    eMusic.setText("");
                    return;
                }
                final Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minutes = cal.get(Calendar.MINUTE);

                picker = new TimePickerDialog(InHomeActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eMusic.setText(String.format(TIME_FORMAT, sHour, sMinute));
                                MusicHour = sHour;
                                MusicMin = sMinute;

                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        btnSubmit = findViewById(R.id.btn_inHomeSubmit);
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
     * Construct the {@link Email} object that corresponds to the In Home form object and send the
     * email.
     */
    public void onLoginSuccess() {
        inHomeForm.setClientName(client_name);
        inHomeForm.setNumberOfPersonsSupported(numberOfPerson);
        inHomeForm.setNote(note);

        Visit visit = new Visit();
        visit.setServiceType(BaseForm.FormType.DIRECT.getName() + COLON + inHomeForm.getServiceTypes());
        visit.setUserNote(note);
        VisitServiceImpl.INSTANCE.addVisit(visit);

        Email email = new Email();
        email.setSubject(getCSVFileName(DirectServiceForm.DirectServiceType.IN_HOME.getName()));
        email.setBody("Please find attached an In Home Form data");
        email.setCsvAttachmentFileName(email.getSubject() + CSV_EXTENSION);
        email.setAttachmentText(inHomeForm.getAttachmentText());

        new EmailSenderAsyncTask(this).execute(email);
    }

    /**
     * Validate the In Home office form.
     *
     * @return {@code true} if validation succeeded, {@code false} otherwise
     */
    private boolean validate() {
        boolean valid = true;
        if (client_name.isEmpty() || client_name.length() > 30) {
            editName.setError("Please enter valid Name");
            valid = false;
        }

        if (numberOfPerson.isEmpty()) {
            editPerson.setError("Please enter  valid number of people during visit");
            valid = false;

        }

        if (!BooleanUtils.or(new boolean[]{CBPalliative.isChecked(), CBCaregiver.isChecked(), CBBereaved.isChecked()})) {
            CBPalliative.setError("At least one client type must be selected.");
            valid = false;
        }

        return valid;
    }

    private void initialize() {
        client_name = editName.getText().toString().trim();
        numberOfPerson = editPerson.getText().toString().trim();
        note = editNote.getText().toString().trim();

        if (CBPalliative.isChecked()) {
            inHomeForm.addClientType(InHomeForm.ClientType.PALLIATIVE);
        }

        if (CBCaregiver.isChecked()) {
            inHomeForm.addClientType(InHomeForm.ClientType.CAREGIVER);
        }

        if (CBBereaved.isChecked()) {
            inHomeForm.addClientType(InHomeForm.ClientType.BEREAVED);
        }

        if (CBReiki.isChecked()) {
            inHomeForm.addCompTherapy(InHomeForm.CompTherapy.REIKI, eReiki.getText().toString());
        }

        if (CBTT.isChecked()) {
            inHomeForm.addCompTherapy(InHomeForm.CompTherapy.TT, eTT.getText().toString());
        }

        if (CBAroma.isChecked()) {
            inHomeForm.addCompTherapy(InHomeForm.CompTherapy.AROMA, eAroma.getText().toString());
        }

        if (CBCompanioning.isChecked()) {
            inHomeForm.addInHomeType(InHomeForm.InHomeType.COMPANIONING, eCompanioning.getText().toString());
        }

        if (CBRespite.isChecked()) {
            inHomeForm.addInHomeType(InHomeForm.InHomeType.RESPITE, eRespite.getText().toString());
        }

        if (CBSpiritual.isChecked()) {
            inHomeForm.addInHomeType(InHomeForm.InHomeType.SPIRITUAL, eSpiritual.getText().toString());
        }
//        if (CBArt.isChecked()) {
//            inHomeForm.addInHomeType(InHomeForm.InHomeType.ART, eArt.getText().toString());
//        }
//        if (CBMusic.isChecked()) {
//            inHomeForm.addInHomeType(InHomeForm.InHomeType.MUSIC, eMusic.getText().toString());
//        }
    }
}

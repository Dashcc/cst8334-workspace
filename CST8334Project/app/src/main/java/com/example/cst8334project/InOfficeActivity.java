package com.example.cst8334project;

import android.app.Activity;
import android.app.TimePickerDialog;
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
import com.example.cst8334project.forms.InOfficeForm;
import com.example.cst8334project.userhistoryservice.VisitServiceImpl;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Locale;

import static com.example.cst8334project.forms.util.FormUtils.*;

public class InOfficeActivity extends BaseActivity {

    CheckBox[] checkBoxes = new CheckBox[5];
    EditText[] editTexts = new EditText[5];

    InOfficeForm inOfficeForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inoffice);

        setupDrawer();

        int[] checkBoxesIds = new int[]{R.id.CB1, R.id.CB2, R.id.CB3, R.id.CB4, R.id.CB5};
        int[] editTextIds = new int[]{R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5};

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = findViewById(checkBoxesIds[i]);
            editTexts[i] = findViewById(editTextIds[i]);
            editTexts[i].setInputType(InputType.TYPE_NULL);

            final int finalI = i;
            checkBoxes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearErrors();

                    if (!checkBoxes[finalI].isChecked()) {
                        editTexts[finalI].setText("");
                        return;
                    }

                    final Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minutes = cal.get(Calendar.MINUTE);

                    TimePickerDialog picker = new TimePickerDialog(InOfficeActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                    editTexts[finalI].setText(String.format(Locale.ENGLISH, TIME_FORMAT, sHour, sMinute));
                                }
                            }, hour, minutes, true);
                    picker.show();
                }
            });
        }

        inOfficeForm = (InOfficeForm) getIntent().getSerializableExtra(FORM_INTENT_OBJECT_NAME);

        Button btnSubmit = findViewById(R.id.btn_directSubmit);
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
     * Create an {@link Email} and {@link Visit} object that corresponds to the In Office form and send the email.
     */
    public void onLoginSuccess() {
        Visit visit = new Visit();
        visit.setServiceType(BaseForm.FormType.DIRECT.getName() + COLON +
                DirectServiceForm.DirectServiceType.IN_OFFICE.getName());
        visit.setUserNote(inOfficeForm.getInOfficeServiceTypes());
        VisitServiceImpl.INSTANCE.addVisit(visit);

        Email email = new Email();
        email.setSubject("");
        email.setBody("Please find attached an In Office Form data");
        email.setCsvAttachmentFileName(email.getSubject() + CSV_EXTENSION);
        email.setAttachmentText(inOfficeForm.getAttachmentText());

        new EmailSenderAsyncTask(this).execute(email);
    }

    /**
     * Validate the In Office form by ensuring that at least one of the checkboxes on the form
     * is checked.
     *
     * @return {@code true} if at least one of the checkboxes is checked, {@code false} otherwise
     */
    public boolean validate() {
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                if (StringUtils.isNotBlank(editTexts[i].getText().toString())) {
                    return true;
                }
                // If at least one of the checkboxes is selected, but its corresponding time
                // is not provided, display an error message
                editTexts[i].setError("Please enter a time for this service.");
                return false;
            }
        }
        return false;
    }

    private void initialize() {
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                inOfficeForm.addInOfficeType(InOfficeForm.InOfficeType.values()[i], editTexts[i].getText().toString());
            }
        }
    }

    private void clearErrors() {
        for (int i = 0; i < checkBoxes.length; i++) {
            editTexts[i].setError(null);
        }
    }
}


package com.example.cst8334project;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cst8334project.domain.Email;
import com.example.cst8334project.domain.Visit;
import com.example.cst8334project.emailservice.EmailSenderAsyncTask;
import com.example.cst8334project.forms.BaseForm;
import com.example.cst8334project.forms.IndirectServiceForm;
import static com.example.cst8334project.forms.util.FormUtils.*;
import com.example.cst8334project.userhistoryservice.VisitServiceImpl;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

public class IndirectActivity extends BaseActivity {

    RadioButton[] radioButtons = new RadioButton[5];

    EditText[] editTexts = new EditText[5];

    IndirectServiceForm indirectServiceForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indirect);

        setupDrawer();

        radioButtons[0] = findViewById(R.id.RB1);
        radioButtons[1] = findViewById(R.id.RB2);
        radioButtons[2] = findViewById(R.id.RB3);
        radioButtons[3] = findViewById(R.id.RB4);
        radioButtons[4] = findViewById(R.id.RB5);

        editTexts[0] = findViewById(R.id.editText1);
        editTexts[1] = findViewById(R.id.editText2);
        editTexts[2] = findViewById(R.id.editText3);
        editTexts[3] = findViewById(R.id.editText4);
        editTexts[4] = findViewById(R.id.editText5);

        Button btnSubmit = findViewById(R.id.btn_indirectSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        for(EditText editText: editTexts) {
            editText.setInputType(InputType.TYPE_NULL);
        }

        // Get the IndirectServiceForm object from the intent extras
        indirectServiceForm = (IndirectServiceForm) getIntent().getSerializableExtra(FORM_INTENT_OBJECT_NAME);

        for (int i = 0; i < radioButtons.length; i++) {
            final int finalI = i;
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unCheckOtherRadioButtons(finalI);

                    final Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minutes = cal.get(Calendar.MINUTE);

                    TimePickerDialog picker = new TimePickerDialog(IndirectActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                    editTexts[finalI].setText(String.format(TIME_FORMAT, sHour, sMinute));
                                }
                            }, hour, minutes, true);
                    picker.show();
                }
            });
        }
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
        Visit visit = new Visit();
        visit.setServiceType(BaseForm.FormType.INDIRECT.getName());
        visit.setUserNote(indirectServiceForm.getServiceType());
        VisitServiceImpl.INSTANCE.addVisit(visit);

        Email email = new Email();
        email.setSubject(getCSVFileName(BaseForm.FormType.INDIRECT.getName()));
        email.setBody("Please find attached an InDirect Form data");
        email.setCsvAttachmentFileName(email.getSubject() + CSV_EXTENSION);
        email.setAttachmentText(indirectServiceForm.getAttachmentText());

        new EmailSenderAsyncTask(this).execute(email);
    }

    /**
     * Validate the {@link IndirectServiceForm} by ensuring that atleast one of the checkboxes is
     * checked.
     *
     * @return {@code true} if at least one of the Indirect Form checkboxes is checked, false otherwise
     */
    public boolean validate() {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isChecked()) {
                if (StringUtils.isNotBlank(editTexts[i].getText().toString())) {
                    return true;
                }
                // If at least one of the radio buttons is selected, but its corresponding time
                // is not provided, display an error message
                editTexts[i].setError("Please enter a time for this service.");
            }
        }
        return false;
    }

    private void initialize() {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isChecked()) {
                indirectServiceForm.setIndirectServiceTypePair(IndirectServiceForm.IndirectServiceType.values()[i],
                        editTexts[i].getText().toString());
                return;
            }
        }
    }

    private void unCheckOtherRadioButtons(int index) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (i != index) {
                radioButtons[i].setChecked(false);
                editTexts[i].setText("");
                editTexts[i].setError(null);
            }
        }
    }
}


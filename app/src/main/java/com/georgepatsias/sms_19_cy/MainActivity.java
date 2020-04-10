package com.georgepatsias.sms_19_cy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView language_icon = findViewById(R.id.language_change);

        language_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (String.valueOf(language_icon.getTag())) {
                    case "en":
                        LanguageToGreek();
                        language_icon.setImageDrawable(getResources().getDrawable(R.drawable.cy));
                        language_icon.setTag("cy");
                        break;

                    case "cy":
                        LanguageToEnglish();
                        language_icon.setImageDrawable(getResources().getDrawable(R.drawable.uk));
                        language_icon.setTag("en");
                        break;
                    default:
                        // code block
                }
            }
        });


        findViewById(R.id.privacy_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.openWebsite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.georgepatsias.com/pages/contact-george-patsias-freelancer-computer-science-webdesign-webdevelop-software-engineer-android-ios-apple-developer/#fullwidth-block-5e82206238b87";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        findViewById(R.id.cirSEND_SMSButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText postalCode = findViewById(R.id.editTextPostalCode);
                EditText idNumber = findViewById(R.id.editTextIDnumber);
                RadioGroup radioGroup = findViewById(R.id.radioGroup);

                int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();

                if (isEmpty(postalCode) && isEmpty(idNumber)) {
                    postalCode.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.showSoftInput(postalCode, InputMethodManager.SHOW_IMPLICIT);
                    return;
                }

                if (isEmpty(postalCode)) {
                    postalCode.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.showSoftInput(postalCode, InputMethodManager.SHOW_IMPLICIT);
                    return;
                }

                if (isEmpty(idNumber)) {
                    idNumber.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.showSoftInput(idNumber, InputMethodManager.SHOW_IMPLICIT);
                    return;
                }

                String sms_body = getRadioSelection(selectedRadioButtonID) + " " + idNumber.getText().toString() + " " + postalCode.getText().toString();

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra("sms_body", sms_body);
                    intent.setData(Uri.parse("sms:8998"));
                    startActivity(intent);

                } catch (android.content.ActivityNotFoundException anfe) {
                    Log.d("Error", "Cannot open SMS intent!");
                }
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }

    private String getRadioSelection(int rbID) {
        RadioButton selectedRadioButton = (RadioButton) findViewById(rbID);
        String selectedRadioButtonText = selectedRadioButton.getText().toString();
        String selectionID = "";

        switch (selectedRadioButtonText) {
            case "Visiting a pharmacy, a doctor or for a blood donation.":
                selectionID = "1";
                break;
            case "Visiting a store to obtain essential goods or services.":
                selectionID = "2";
                break;
            case "Visiting a bank if a transaction cannot be done online.":
                selectionID = "3";
                break;
            case "Absolutely necessary visits to state services, public-sector services or municipal services.":
                selectionID = "4";
                break;
            case "Visiting persons who are unable to help themselves or are in self-isolation.":
                selectionID = "5";
                break;
            case "Going outdoors for exercise or to walk one’s pet, for two persons at a maximum and the distance must be close to one’s residence.":
                selectionID = "6";
                break;
            case "Attending ceremonies like funerals, weddings or baptisms, provided you are a first-degree or second-degree relative and the gathering must be no more than 10 people at any one time.":
                selectionID = "7";
                break;
            case "Any other reason (generic) for moving outside that may be justified despite the restrictions.":
                selectionID = "8";
                break;
        }

        return selectionID;
    }

    private void LanguageToGreek() {
        TextView main_title = findViewById(R.id.main_title);

        TextInputLayout editTextPostalCode = findViewById(R.id.textInputPostalCode);
        TextInputLayout editTextIDnumber = findViewById(R.id.textInputPasport);


        TextView reason_text = findViewById(R.id.reason_text);
        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);
        RadioButton choice5 = findViewById(R.id.choice5);
        RadioButton choice6 = findViewById(R.id.choice6);
        RadioButton choice7 = findViewById(R.id.choice7);
        RadioButton choice8 = findViewById(R.id.choice8);
        TextView cirSEND_SMSButton = findViewById(R.id.cirSEND_SMSButton);

        main_title.setText(R.string.gr_main_title);
        editTextPostalCode.setHint("Ταχυδρομικός Κώδικας");
        editTextIDnumber.setHint("Διαβατήριο ή Αριθμός Ταυτότητας");
        reason_text.setText(R.string.gr_reason_text);
        choice1.setText(R.string.gr_selection_1);
        choice2.setText(R.string.gr_selection_2);
        choice3.setText(R.string.gr_selection_3);
        choice4.setText(R.string.gr_selection_4);
        choice5.setText(R.string.gr_selection_5);
        choice6.setText(R.string.gr_selection_6);
        choice7.setText(R.string.gr_selection_7);
        choice8.setText(R.string.gr_selection_8);
        cirSEND_SMSButton.setText(R.string.gr_send_button);
    }

    private void LanguageToEnglish() {
        TextView main_title = findViewById(R.id.main_title);
        TextInputLayout editTextPostalCode = findViewById(R.id.textInputPostalCode);
        TextInputLayout editTextIDnumber = findViewById(R.id.textInputPasport);
        TextView reason_text = findViewById(R.id.reason_text);
        TextView choice1 = findViewById(R.id.choice1);
        TextView choice2 = findViewById(R.id.choice2);
        TextView choice3 = findViewById(R.id.choice3);
        TextView choice4 = findViewById(R.id.choice4);
        TextView choice5 = findViewById(R.id.choice5);
        TextView choice6 = findViewById(R.id.choice6);
        TextView choice7 = findViewById(R.id.choice7);
        TextView choice8 = findViewById(R.id.choice8);
        TextView cirSEND_SMSButton = findViewById(R.id.cirSEND_SMSButton);

        main_title.setText(R.string.en_main_title);
        editTextPostalCode.setHint("Postal Code");
        editTextIDnumber.setHint("Passport or ID number");
        reason_text.setText(R.string.en_reason_text);
        choice1.setText(R.string.en_selection_1);
        choice2.setText(R.string.en_selection_2);
        choice3.setText(R.string.en_selection_3);
        choice4.setText(R.string.en_selection_4);
        choice5.setText(R.string.en_selection_5);
        choice6.setText(R.string.en_selection_6);
        choice7.setText(R.string.en_selection_7);
        choice8.setText(R.string.en_selection_8);
        cirSEND_SMSButton.setText(R.string.en_send_button);
    }

}

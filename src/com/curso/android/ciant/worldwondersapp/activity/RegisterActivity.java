package com.curso.android.ciant.worldwondersapp.activity;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.curso.android.ciant.worldwondersapp.entity.User;
import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;
import com.curso.android.ciant.worldwondersapp.integrator.UserSharedPreferences;
import com.example.worldwondersapp.R;

public class RegisterActivity extends Activity{

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordConfirmation;
    private Spinner mSpinnerCountry;
    private AutoCompleteTextView mAutoCompleteLanguage;
    private RadioGroup mRadioGroupGender;
    private CheckBox mCheckBoxNotification;
    private Button mButtonRegister;
    
    private UserSharedPreferences mUserSharedPreferences;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        mUserSharedPreferences = new UserSharedPreferences(this);
        
        mEditTextName = (EditText) findViewById(R.id.edit_text_name);
        mEditTextEmail = (EditText) findViewById(R.id.edit_text_email);
        mEditTextPassword = (EditText) findViewById(R.id.edit_text_password);
        mEditTextPasswordConfirmation = (EditText) findViewById(R.id.edit_text_password_confirmation);
        mSpinnerCountry = (Spinner) findViewById(R.id.spinner_country);
        mAutoCompleteLanguage = (AutoCompleteTextView) findViewById(R.id.auto_complete_language);
        mRadioGroupGender = (RadioGroup) findViewById(R.id.radio_group_gender);
        mCheckBoxNotification = (CheckBox) findViewById(R.id.check_box_notification);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
        	  @Override
              public void onClick(View v) {
        		  if (validateFields()) {
        			  
        			  String name = mEditTextName.getText().toString();
                      String email = mEditTextEmail.getText().toString();
                      String password = mEditTextPassword.getText().toString();
                      String country = mSpinnerCountry.getSelectedItem().toString();
                      String language = mAutoCompleteLanguage.getText().toString();
                      
                      String gender = "";
                      if (mRadioGroupGender.getCheckedRadioButtonId() == R.id.radio_button_male) {
                          gender = Constants.Entity.User.INDICATOR_GENDER_MALE;
                      } else if (mRadioGroupGender.getCheckedRadioButtonId() == R.id.radio_button_female) {
                          gender = Constants.Entity.User.INDICATOR_GENDER_FEMALE;
                      }
                      
                      Integer notification = mCheckBoxNotification.isChecked()? 
                    		  				 Constants.Entity.User.INDICATOR_NOTIFICATION_ON : 
                    		  			     Constants.Entity.User.INDICATOR_NOTIFICATION_OFF;
                      
                      
                      User user = new User();
                      user.setName(name);
                      user.setEmail(email);
                      user.setPassword(password);
                      user.setCountry(country);
                      user.setLanguage(language);
                      user.setGender(gender);
                      user.setNotification(notification);
                      
                      if (mUserSharedPreferences.registerUser(user)){
                    	  Intent intent = new Intent();
                          Bundle bundle = new Bundle();
                          bundle.putString(Constants.Bundle.BUNDLE_USER_EMAIL, email);
                          bundle.putString(Constants.Bundle.BUNDLE_USER_PASSWORD, password);
                          intent.putExtras(bundle);
                          setResult(Activity.RESULT_OK, intent);
                          finish();
                      }
        		  }
        	  }
        });
        
        mSpinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadAutoCompleteLanguage(mSpinnerCountry.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        if (getIntent().hasExtra(Constants.Bundle.BUNDLE_USER_EMAIL)) {
            String email = getIntent().getStringExtra(Constants.Bundle.BUNDLE_USER_EMAIL);
            mEditTextEmail.setText(email);
        }
        
    }
    
    private void loadAutoCompleteLanguage(final String country) {
        int arrayResId = 0;
        if (Constants.Entity.User.TEXT_COUNTRY_BRASIL.equals(country)) {
            arrayResId = R.array.array_language_brasil;
        } else if (Constants.Entity.User.TEXT_COUNTRY_CANADA.equals(country)) {
            arrayResId = R.array.array_language_canada;
        } else if (Constants.Entity.User.TEXT_COUNTRY_CHINA.equals(country)) {
            arrayResId = R.array.array_language_china;
        }

        String[] languageArray = getResources().getStringArray(arrayResId);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList(languageArray));

        mAutoCompleteLanguage.setAdapter(arrayAdapter);
    }
    
    private Boolean validateFields() {

        Boolean isValidFields = Boolean.TRUE;

        if (mEditTextName.getText().toString().isEmpty()) {
            mEditTextName.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        if (mEditTextEmail.getText().toString().isEmpty()) {
            mEditTextEmail.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        String password = mEditTextPassword.getText().toString();
        if (password.isEmpty()) {
            mEditTextPassword.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        String passwordConfirmation = mEditTextPasswordConfirmation.getText().toString();
        if (passwordConfirmation.isEmpty()) {
            mEditTextPasswordConfirmation.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        if (mAutoCompleteLanguage.getText().toString().isEmpty()) {
            mAutoCompleteLanguage.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        if (mRadioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getResources().getString(R.string.msg_required_field_gender), Toast.LENGTH_SHORT).show();
            isValidFields = Boolean.FALSE;
        }

        if (isValidFields) {
            if (!password.equals(passwordConfirmation)) {
                isValidFields = Boolean.FALSE;
                Toast.makeText(this, getResources().getString(R.string.msg_password_confirmation), Toast.LENGTH_SHORT).show();
            }
        }

        return isValidFields;
    }


}

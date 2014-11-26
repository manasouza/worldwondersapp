package com.curso.android.ciant.worldwondersapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.worldwondersapp.R;

public class LoginActivity extends Activity{

	private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail = (EditText) findViewById(R.id.edit_text_email);
        mEditTextPassword = (EditText) findViewById(R.id.edit_text_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonSignUp = (Button) findViewById(R.id.button_signup);
        
        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	if (validateFields()){
            		String email = mEditTextEmail.getText().toString();
                    String password = mEditTextPassword.getText().toString();
            	}
            }
        
        });
        
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	
            }
        });
	}
	
	private Boolean validateFields() {

        Boolean isValidFields = Boolean.TRUE;
        if (mEditTextEmail.getText().toString().isEmpty()) {
            mEditTextEmail.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        if (mEditTextPassword.getText().toString().isEmpty()) {
            mEditTextPassword.setError(getResources().getString(R.string.msg_required_field));
            isValidFields = Boolean.FALSE;
        }

        return isValidFields;
    }
	
}

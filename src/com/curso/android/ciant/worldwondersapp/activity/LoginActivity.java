package com.curso.android.ciant.worldwondersapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.curso.android.ciant.worldwondersapp.entity.User;
import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;
import com.curso.android.ciant.worldwondersapp.integrator.UserSharedPreferences;
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
                    login(email, password);
            	}
            }
        
        });
        
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
            	String email = mEditTextEmail.getText().toString();
            	if (email != null && !email.isEmpty()){
            		Bundle bundle = new Bundle();
            		bundle.putString(Constants.Bundle.BUNDLE_USER_EMAIL, email);
            		it.putExtras(bundle);
            	}
            	startActivityForResult(it, Constants.RequestCode.LOGIN_ACTIVITY);
            }
        });
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK){
			if(requestCode == Constants.RequestCode.LOGIN_ACTIVITY){
				if (data != null){
					String email = data.getStringExtra(Constants.Bundle.BUNDLE_USER_EMAIL);
					String password = data.getStringExtra(Constants.Bundle.BUNDLE_USER_PASSWORD);
					login(email, password);
				}
			}
		}else if (resultCode == Activity.RESULT_CANCELED){
			Toast.makeText(this, getResources().getString(R.string.msg_signup_cancel), Toast.LENGTH_SHORT).show();
		}
	}
	
	private void login(String email, String password){
		UserSharedPreferences mUserSharedPreferences = new UserSharedPreferences(this);
		User user = mUserSharedPreferences.loginUser(email, password);
		
		if(user != null){
			Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
		}else{
			Toast.makeText(LoginActivity.this, getResources().getString(R.string.msg_invalid_login), Toast.LENGTH_SHORT).show();
		}
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

package com.curso.android.ciant.worldwondersapp.integrator;

import com.curso.android.ciant.worldwondersapp.entity.User;
import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserSharedPreferences {

	private SharedPreferences mDBSP;

	private final String BUNDLE_USER_NAME_DB = "user_name_db";
	private final String BUNDLE_USER_EMAIL_DB = "user_email_db";
	private final String BUNDLE_USER_PASSWORD_DB = "user_password_db";

	public UserSharedPreferences(Context context) {
		mDBSP = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public User loginUser(String email, String password) {

		User result = null;
		String userNameDB = mDBSP.getString(BUNDLE_USER_NAME_DB, null);
		String userEmailDB = mDBSP.getString(BUNDLE_USER_EMAIL_DB, null);
		String userPasswordDB = mDBSP.getString(BUNDLE_USER_PASSWORD_DB, null);

		if (userEmailDB != null) {
			if (userEmailDB.equals(email) && userPasswordDB.equals(password)) {
				User user = new User();
				user.setName(userNameDB);
				user.setEmail(userEmailDB);
				result = user;
			}
		}

		return result;
	}

	public Boolean registerUser(User user) {

		SharedPreferences.Editor editor = mDBSP.edit();
		editor.putString(BUNDLE_USER_NAME_DB, user.getName());
		editor.putString(BUNDLE_USER_EMAIL_DB, user.getEmail());
		editor.putString(BUNDLE_USER_PASSWORD_DB, user.getPassword());
		editor.commit();

		return Boolean.TRUE;

	}

	public void logoutUser() {
		SharedPreferences.Editor editor = mDBSP.edit();
		editor.remove(BUNDLE_USER_NAME_DB);
		editor.remove(BUNDLE_USER_EMAIL_DB);
		editor.commit();
	}
	
	public Boolean isUserLogged() {
        Boolean isUserLogged = Boolean.FALSE;
        String userEmail = mDBSP.getString(BUNDLE_USER_EMAIL_DB, null);
        if (userEmail != null && !userEmail.isEmpty()) {
            isUserLogged = Boolean.TRUE;
        }
        return isUserLogged;
    }

}

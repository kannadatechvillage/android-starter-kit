package com.kannadatechvillage.workshop.usersession;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kannadatechvillage.workshop.activities.SplashActivity;
import com.kannadatechvillage.workshop.dp.DbHelper;
import com.kannadatechvillage.workshop.models.UserInfo;


public class UserSession {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;
    // First time logic Check
    public static final String FIRST_TIME = "firsttime";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String PREF_NAME = "UserSessionPref";

    private static final String KEY_NAME = "workshop_user_name";
    private static final String KEY_EMAIL = "workshop_user_email";
    private static final String KEY_MOBiLE = "workshop_user_mobile";
    private static final String KEY_PASSWORDS = "workshop_user_password";



    private volatile static UserSession userSession =null;

    public synchronized static UserSession getInstance(Context context) {
        if (null == userSession)
            userSession =new UserSession(context);
        return userSession ;
    }

    // Constructor
    public UserSession(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(UserInfo userInfo){
//        // Storing login value as TRUE
//        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, userInfo.getName());

        // Storing email in pref
        editor.putString(KEY_EMAIL, userInfo.getEmail());

        // Storing phone number in pref
        editor.putString(KEY_MOBiLE, userInfo.getMobile());

        // Storing image url in pref
          editor.putBoolean(IS_LOGIN, true);
//        editor.putString(KEY_API_KEY,userInfo.getApi_key());

        editor.putString(KEY_PASSWORDS,userInfo.getPassword());
        // commit changes
        editor.commit();
    }


    /**
     * Get stored session data
     * */
    public UserInfo getUserDetails(){
        if (pref.getString(KEY_MOBiLE, null)==null){
            return  null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setName(pref.getString(KEY_NAME, null));
        userInfo.setEmail(pref.getString(KEY_EMAIL, null));
        userInfo.setMobile(pref.getString(KEY_MOBiLE, null));
//        userInfo.setApi_key(pref.getString(KEY_API_KEY, null));
        userInfo.setPassword(pref.getString(KEY_PASSWORDS, null));

         return userInfo;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
//        editor.putBoolean(IS_LOGIN,false);
        editor.clear();
        editor.commit();

//         After logout redirect user to Login Activity
        Intent i = new Intent(context, SplashActivity.class);
//         Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}

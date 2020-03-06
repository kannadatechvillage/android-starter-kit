package com.kannadatechvillage.workshop.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    public String getHeightScreen(){
        return "";
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}

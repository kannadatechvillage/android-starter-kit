package com.kannadatechvillage.workshop.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.kannadatechvillage.workshop.R;

import java.util.Random;

public class Utils {

    static Integer[] Randomcolors = {
            R.color.bg_screen1,
            R.color.bg_screen2,
            R.color.bg_screen3,
            R.color.bg_screen4,
            R.color.green_light,
            R.color.black_overlay,
            R.color.colorPrimary,
            R.color.colorPrimary};

    public String getHeightScreen(){
        return "";
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static int getRandomColor(Context context) {
        int rnd = new Random().nextInt(Randomcolors.length);
        return context.getResources().getColor(Randomcolors[rnd]);
    }

}

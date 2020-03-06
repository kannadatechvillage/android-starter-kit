package com.kannadatechvillage.workshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.kannadatechvillage.workshop.MainActivity;
import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.models.UserInfo;
import com.kannadatechvillage.workshop.usersession.UserSession;

public class SplashActivity extends BaseActivity {


    private static int SPLASH_TIME_OUT = 5000;
    int appLatestVersionCode;
    String updateMessage;
    boolean isUpdateMandatory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.blacklist);

        TextView appname = findViewById(R.id.appname);
        appname.setTypeface(typeface);

        YoYo.with(Techniques.Bounce)
                .duration(7000)
                .playOn(findViewById(R.id.logo));

        YoYo.with(Techniques.FadeInUp)
                .duration(5000)
                .playOn(findViewById(R.id.appname));

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                gotoNextActivity();
//                selectLanguage();
            }
        }, SPLASH_TIME_OUT);
    }

    private void checkAppVerison() {
        PackageInfo pInfo = null;
        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //String version = pInfo.versionName;
        int verCode = pInfo.versionCode;
        if (verCode < appLatestVersionCode && isUpdateMandatory) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setMessage(updateMessage)
                    .setPositiveButton(getResources().getString(R.string.update), new DialogInterface.OnClickListener() {
                        //if the user agrees to upgrade
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "http://play.google.com/store/apps/details";
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
                            int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
                            if (Build.VERSION.SDK_INT >= 21) {
                                flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
                            } else {
                                //noinspection deprecation
                                flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
                            }
                            intent.addFlags(flags);
                            startActivity(intent);
                            finish();

                            //start downloading the file using the download manager

                        }
                    }).setCancelable(false);
            //show the alert message
            builder.create().show();
        } else if (verCode < appLatestVersionCode && !isUpdateMandatory) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setMessage(updateMessage)
                    .setPositiveButton(getResources().getString(R.string.update), new DialogInterface.OnClickListener() {
                        //if the user agrees to upgrade
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "http://play.google.com/store/apps/details";
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
                            int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
                            if (Build.VERSION.SDK_INT >= 21) {
                                flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
                            } else {
                                //noinspection deprecation
                                flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
                            }
                            intent.addFlags(flags);
                            startActivity(intent);
                            finish();

                            //start downloading the file using the download manager

                        }
                    }).setNegativeButton(getResources().getString(R.string.remind_me_later), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    gotoNextActivity();

                }
            }).setCancelable(false);
            //show the alert message
            builder.create().show();
        } else if (verCode == appLatestVersionCode) {
            gotoNextActivity();
        } else {
            gotoNextActivity();
        }
    }

    public void gotoNextActivity() {
        UserSession userSession = UserSession.getInstance(getApplicationContext());
        UserInfo customer = userSession.getUserDetails();
        if (customer == null) {
            startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();

        }
    }
}

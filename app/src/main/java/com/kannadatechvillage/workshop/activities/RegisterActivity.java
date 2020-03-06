package com.kannadatechvillage.workshop.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.dp.DbHelper;
import com.kannadatechvillage.workshop.interfaces.AppConstants;
import com.kannadatechvillage.workshop.models.UserInfo;

import java.util.Date;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, AppConstants {

    Button btn_register,btn_go_back;
    EditText et_name,et_email,et_mobile,et_password,et_cnf_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
    }

    private void initViews() {

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        et_password = findViewById(R.id.et_password);
        et_cnf_password = findViewById(R.id.et_cnf_password);

        btn_register = findViewById(R.id.btn_register);
        btn_go_back = findViewById(R.id.btn_go_back);

        btn_go_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_go_back:
                finish();
                break;
        }
    }

    private void register() {
        if (!validateUser()){
            return;
        }

        DbHelper dbHelper = DbHelper.getInstance(getApplicationContext());
        UserInfo userInfo = new UserInfo();
        userInfo.setName(et_name.getText().toString().trim());
        userInfo.setEmail(et_email.getText().toString().trim());
        userInfo.setMobile(et_mobile.getText().toString().trim());
        userInfo.setPassword(et_password.getText().toString().trim());
        userInfo.setCreatedOn(new Date().getTime());
        userInfo.setUpdatedOn(new Date().getTime());

        int result = dbHelper.insertUser(userInfo);

        if (result==USER_ALREADY_EXISTS){
            Toast.makeText(this, "User Already Exist With this Mobile", Toast.LENGTH_SHORT).show();
        }else if (result==FAILED){
            Toast.makeText(this, "User Creation Failed", Toast.LENGTH_SHORT).show();
        }else if (result==SUCCESS){
            openSuccessDialog();
        }

    }

    public void openSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success");
        builder.setMessage("You account Created Successfully Please Sign In");
        builder.setCancelable(false);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        builder.show();
    }

    private boolean validateUser() {
        if (et_name.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Please Enter name", Toast.LENGTH_SHORT).show();
            return false;
        }else if (et_email.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!isValidEmail(et_email.getText().toString().trim())){
            Toast.makeText(this, "Please Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }else if (et_mobile.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (et_password.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }else if (et_cnf_password.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!et_password.getText().toString().trim().equalsIgnoreCase(et_cnf_password.getText().toString().trim())){
            Toast.makeText(this, "Password and Confirm Passwords are not Same", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}

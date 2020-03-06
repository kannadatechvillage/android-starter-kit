package com.kannadatechvillage.workshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kannadatechvillage.workshop.MainActivity;
import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.dp.DbHelper;
import com.kannadatechvillage.workshop.models.UserInfo;
import com.kannadatechvillage.workshop.usersession.UserSession;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText et_username,et_password;
    Button btn_login,btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void login() {
        if (!validateUser()){
            return;
        }

        DbHelper dbHelper = DbHelper.getInstance(getApplicationContext());
        UserInfo userInfo = dbHelper.validateUser(et_username.getText().toString().trim(),et_password.getText().toString().trim());
        if (userInfo==null){
            Toast.makeText(this, "Either Mobile or Password is Wrong", Toast.LENGTH_SHORT).show();
        }else{
            UserSession session = UserSession.getInstance(getApplicationContext());
            session.createLoginSession(userInfo);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public boolean validateUser(){
        if (et_username.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(LoginActivity.this, "Please Enter Mobile", Toast.LENGTH_SHORT).show();
            return false;
        }else if (et_password.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

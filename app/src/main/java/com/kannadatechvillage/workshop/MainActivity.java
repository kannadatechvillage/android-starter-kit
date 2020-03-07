package com.kannadatechvillage.workshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.kannadatechvillage.workshop.activities.AddCustomerActivity;
import com.kannadatechvillage.workshop.activities.BaseActivity;
import com.kannadatechvillage.workshop.activities.SecondScreenActivity;
import com.kannadatechvillage.workshop.activities.ViewCustomersActivity;
import com.kannadatechvillage.workshop.interfaces.AppConstants;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AppConstants {

    Button btnDemo,btnGoNext,btn_add_customer;
    ImageButton ibDemo;
    ImageView ivDemo;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {

        btnDemo = findViewById(R.id.btnDemo);
        btnGoNext = findViewById(R.id.btnGoNext);
        ivDemo = findViewById(R.id.ivDemo);
        ibDemo = findViewById(R.id.ibDemo);
        btn_add_customer = findViewById(R.id.btn_add_customer);
        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondScreenActivity.class);
                startActivity(intent);
            }
        });

        ivDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ibDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image Button is Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddCustomerActivity.class));
            }
        });

        inflateNavigationDrawer();
    }

    private void inflateNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Toast.makeText(getApplicationContext(), "onResume is Triggered", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            Toast.makeText(getApplicationContext(), "onPause is Triggered", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            Toast.makeText(getApplicationContext(), "onStart is Triggered", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            Toast.makeText(getApplicationContext(), "onRestart is Triggered", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            Toast.makeText(getApplicationContext(), "onDestroy is Triggered", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_view_customers:
                Intent intent = new Intent(MainActivity.this, ViewCustomersActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}

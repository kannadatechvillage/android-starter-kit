package com.kannadatechvillage.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.kannadatechvillage.workshop.activities.SecondScreenActivity;

public class MainActivity extends AppCompatActivity {

    Button btnDemo,btnGoNext;
    ImageButton ibDemo;
    ImageView ivDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        btnDemo = findViewById(R.id.btnDemo);
        btnGoNext = findViewById(R.id.btnGoNext);
        ivDemo = findViewById(R.id.ivDemo);
        ibDemo = findViewById(R.id.ibDemo);


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
}

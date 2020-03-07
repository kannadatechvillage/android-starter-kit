package com.kannadatechvillage.workshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.dp.DbHelper;
import com.kannadatechvillage.workshop.interfaces.AppConstants;
import com.kannadatechvillage.workshop.models.Customer;

public class AddCustomerActivity extends BaseActivity implements View.OnClickListener, AppConstants {

    private RadioButton radio_male,radio_female;
    EditText et_name,et_email,et_mobile,et_address;
    Spinner city_spinner;
    Button btn_add_customer;
    String selectedGender = null;
    String selectedCity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initToolBar();
        initViews();
    }

    private void initViews() {
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        btn_add_customer = findViewById(R.id.btn_add_customer);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        city_spinner = findViewById(R.id.city_spinner);
        et_address = findViewById(R.id.et_address);

        radio_male.setOnClickListener(this);
        radio_female.setOnClickListener(this);
        btn_add_customer.setOnClickListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_list, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        city_spinner.setAdapter(adapter);
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    selectedCity = (String) parent.getItemAtPosition(position);
                }else{
                    selectedCity = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_male:
                selectedGender = "Male";
                break;
            case R.id.radio_female:
                selectedGender = "Female";
                break;
            case R.id.btn_add_customer:
                addCustumer();
                break;

        }
    }

    private void addCustumer() {
        DbHelper dbHelper = DbHelper.getInstance(getApplicationContext());
        Customer customer = new Customer();
        customer.setMobile(et_mobile.getText().toString().trim());
        customer.setName(et_name.getText().toString().trim());
        customer.setEmail(et_email.getText().toString().trim());
        customer.setCity(selectedCity);
        customer.setGender(selectedGender);
        customer.setAddress(et_address.getText().toString().trim());

        int result = dbHelper.insertCustomer(customer);

        if (result==ALREADY_EXISTS){
            Toast.makeText(this, "Customer with this Mobile Already Exists", Toast.LENGTH_SHORT).show();
        }else if (result==FAILED){
            Toast.makeText(this, "Customer Creation Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Customer Created Successfully", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // return super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void initToolBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add New Customer");
    }
}

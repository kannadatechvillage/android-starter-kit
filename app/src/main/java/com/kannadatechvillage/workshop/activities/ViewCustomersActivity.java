package com.kannadatechvillage.workshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.adapters.CustomerListAdapter;
import com.kannadatechvillage.workshop.dp.DbHelper;
import com.kannadatechvillage.workshop.models.Customer;

import java.util.ArrayList;

public class ViewCustomersActivity extends BaseActivity {

    ListView lv_customer;
    CustomerListAdapter customerListAdapter;
    ArrayList<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        initToolBar();
        initViews();
    }

    private void initViews() {
        customers = new ArrayList<>();
        lv_customer = findViewById(R.id.lv_customer);
        customerListAdapter = new CustomerListAdapter(customers,getApplicationContext());
        lv_customer.setAdapter(customerListAdapter);

        getData();

    }

    private void getData() {
        DbHelper dbHelper = DbHelper.getInstance(getApplicationContext());
        customers.addAll(dbHelper.getAllCustomers(null));
        customerListAdapter.notifyDataSetChanged();
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
        getSupportActionBar().setTitle("Customers");
    }
}

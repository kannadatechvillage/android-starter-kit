package com.kannadatechvillage.workshop.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kannadatechvillage.workshop.R;
import com.kannadatechvillage.workshop.models.Customer;
import com.kannadatechvillage.workshop.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends BaseAdapter {

    ArrayList<Customer> customersList;
    Context context;
    LayoutInflater inflater;

    public CustomerListAdapter(ArrayList<Customer> customersList, Context context) {
        this.customersList = customersList;
        this.context = context;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return customersList.size();
    }

    @Override
    public Object getItem(int position) {
        return customersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.customer_list, null);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_email = convertView.findViewById(R.id.tv_email);
        TextView tv_mobile = convertView.findViewById(R.id.tv_mobile);
        TextView tv_profile_indicator = convertView.findViewById(R.id.tv_name_indicator);
        tv_profile_indicator.setText(customersList.get(position).getName().charAt(0)+"");
        GradientDrawable bgShape = (GradientDrawable)tv_profile_indicator.getBackground();
        bgShape.setColor(Utils.getRandomColor(context));

        tv_name.setText(customersList.get(position).getName());
        tv_email.setText(customersList.get(position).getEmail());
        tv_mobile.setText(customersList.get(position).getMobile());

        return convertView;
    }
}

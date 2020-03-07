package com.kannadatechvillage.workshop.dp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kannadatechvillage.workshop.interfaces.AppConstants;
import com.kannadatechvillage.workshop.models.Customer;
import com.kannadatechvillage.workshop.models.UserInfo;

import java.util.ArrayList;
import java.util.Date;

public class DbHelper  extends SQLiteOpenHelper implements AppConstants {

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private volatile static DbHelper dbHelper =null;

    public synchronized static DbHelper getInstance(Context context) {
        if (null == dbHelper)
            dbHelper =new DbHelper(context);
        return dbHelper ;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE_NAME);
    }

    public boolean isUserExists(UserInfo userInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from "+USER_TABLE_NAME+" where "+USER_MOBILE+"='" + userInfo.getMobile() + "'";
        Cursor res =  db.rawQuery(sqlQuery, null);
        if (res.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public int insertUser(UserInfo userInfo){
        if (isUserExists(userInfo)){
            return ALREADY_EXISTS;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,userInfo.getName());
        contentValues.put(USER_EMAIL,userInfo.getEmail());
        contentValues.put(USER_MOBILE,userInfo.getMobile());
        contentValues.put(USER_PASSWORD,userInfo.getPassword());
        contentValues.put(CREATED_ON,new Date().getTime());
        contentValues.put(UPDATED_ON,new Date().getTime());

        contentValues.put(STATUS,"active");

        long result = db.insert(USER_TABLE_NAME, null, contentValues);
        if (result==-1){
            return FAILED;
        }else{
            return SUCCESS;
        }
    }

    public UserInfo validateUser(String mobile, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        UserInfo userInfo = null;
        Cursor res =  db.rawQuery("select * from userinfo where mobile='" + mobile + "'"+" and password ='"+password+"'", null);
        if (res.getCount()>0){
            res.moveToFirst();
            userInfo = new UserInfo();
            userInfo.setEmail(res.getString(res.getColumnIndex(USER_EMAIL)));
            userInfo.setMobile(res.getString(res.getColumnIndex(USER_MOBILE)));
            userInfo.setName(res.getString(res.getColumnIndex(USER_NAME)));
            userInfo.setPassword(res.getString(res.getColumnIndex(USER_PASSWORD)));
            userInfo.setStatus(res.getString(res.getColumnIndex(STATUS)));
            userInfo.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            userInfo.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
        }
        return userInfo;
    }

    public UserInfo getUserByMobile(String mobile){
        SQLiteDatabase db = this.getReadableDatabase();
        UserInfo userInfo = null;
        Cursor res =  db.rawQuery("select * from userinfo where mobile='" + mobile.trim() + "'", null);
//        while ()
        if (res.getCount()>0){
            res.moveToFirst();
            userInfo = new UserInfo();
            userInfo.setEmail(res.getString(res.getColumnIndex(USER_EMAIL)));
            userInfo.setMobile(res.getString(res.getColumnIndex(USER_MOBILE)));
            userInfo.setName(res.getString(res.getColumnIndex(USER_NAME)));
            userInfo.setPassword(res.getString(res.getColumnIndex(USER_PASSWORD)));

            userInfo.setStatus(res.getString(res.getColumnIndex(STATUS)));
            userInfo.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            userInfo.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
        }
        return userInfo;
    }

    public UserInfo getUserById(String mobile){
        SQLiteDatabase db = this.getReadableDatabase();
        UserInfo userInfo = null;
        Cursor res =  db.rawQuery("select * from userinfo where id='" + mobile.trim() + "'", null);
//        while ()
        if (res.getCount()>0){
            res.moveToFirst();
            userInfo = new UserInfo();
            userInfo.setEmail(res.getString(res.getColumnIndex(USER_EMAIL)));
            userInfo.setMobile(res.getString(res.getColumnIndex(USER_MOBILE)));
            userInfo.setName(res.getString(res.getColumnIndex(USER_NAME)));
            userInfo.setPassword(res.getString(res.getColumnIndex(USER_PASSWORD)));

            userInfo.setStatus(res.getString(res.getColumnIndex(STATUS)));
            userInfo.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            userInfo.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
        }
        return userInfo;
    }

    public boolean isCustomerExists(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from "+CUSTOMER_TABLE_NAME+" where "+CUSTOMER_MOBILE+"='" + customer.getMobile() + "'";
        Cursor res =  db.rawQuery(sqlQuery, null);
        if (res.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public int insertCustomer(Customer customer){
        if (isCustomerExists(customer)){
            return ALREADY_EXISTS;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_NAME,customer.getName());
        contentValues.put(CUSTOMER_EMAIL,customer.getEmail());
        contentValues.put(CUSTOMER_MOBILE,customer.getMobile());
        contentValues.put(CUSTOMER_ADDRESS,customer.getAddress());
        contentValues.put(CUSTOMER_GENDER,customer.getGender());
        contentValues.put(CUSTOMER_CITY,customer.getCity());
        contentValues.put(CREATED_ON,new Date().getTime());
        contentValues.put(UPDATED_ON,new Date().getTime());

        contentValues.put(STATUS,"active");

        long result = db.insert(CUSTOMER_TABLE_NAME, null, contentValues);
        if (result==-1){
            return FAILED;
        }else{
            return SUCCESS;
        }
    }

    public ArrayList<Customer> getAllCustomers(String searchString) {
        ArrayList<Customer> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = null;
        if (searchString==null || searchString.equalsIgnoreCase("")){
            res =  db.rawQuery( "select * from "+CUSTOMER_TABLE_NAME, null );
        }else{
            String sqlQuery = "select * from "+CUSTOMER_TABLE_NAME+" WHERE name LIKE ? OR mobile LIKE ? OR email LIKE ?";
            String[] selectionArgs = new String[] { "%"+searchString + "%","%"+searchString + "%","%"+searchString + "%" };
            res =  db.rawQuery( sqlQuery, selectionArgs );
        }
        res.moveToFirst();

        while(res.isAfterLast() == false){

            Customer customer = new Customer();

            customer.setId(res.getInt(res.getColumnIndex("id")));
            customer.setName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            customer.setAddress(res.getString(res.getColumnIndex(CUSTOMER_ADDRESS)));
            customer.setMobile(res.getString(res.getColumnIndex(CUSTOMER_MOBILE)));
            customer.setEmail(res.getString(res.getColumnIndex(CUSTOMER_EMAIL)));
            customer.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            customer.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
            customer.setStatus(res.getString(res.getColumnIndex(STATUS)));

            array_list.add(customer);
            res.moveToNext();
        }

        res.close();
        return array_list;
    }

    public Customer getCustomerByEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Customer customer = null;
        Cursor res =  db.rawQuery("select * from "+CUSTOMER_TABLE_NAME+" where email='" + email.trim() + "'", null);
//        while ()
        if (res.getCount()>0){
            res.moveToFirst();
            customer = new Customer();

            customer.setId(res.getInt(res.getColumnIndex("id")));
            customer.setName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            customer.setAddress(res.getString(res.getColumnIndex(CUSTOMER_ADDRESS)));
            customer.setMobile(res.getString(res.getColumnIndex(CUSTOMER_MOBILE)));
            customer.setEmail(res.getString(res.getColumnIndex(CUSTOMER_EMAIL)));
            customer.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            customer.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
            customer.setStatus(res.getString(res.getColumnIndex(STATUS)));
        }
        return customer;
    }

    public Customer getCustomerById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Customer customer = null;
        Cursor res =  db.rawQuery("select * from "+CUSTOMER_TABLE_NAME+" where id='" + id + "'", null);
//        while ()
        if (res.getCount()>0){
            res.moveToFirst();
            customer = new Customer();

            customer.setId(res.getInt(res.getColumnIndex("id")));
            customer.setName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            customer.setAddress(res.getString(res.getColumnIndex(CUSTOMER_ADDRESS)));
            customer.setMobile(res.getString(res.getColumnIndex(CUSTOMER_MOBILE)));
            customer.setEmail(res.getString(res.getColumnIndex(CUSTOMER_EMAIL)));
            customer.setCreatedOn(res.getLong(res.getColumnIndex(CREATED_ON)));
            customer.setUpdatedOn(res.getLong(res.getColumnIndex(UPDATED_ON)));
            customer.setStatus(res.getString(res.getColumnIndex(STATUS)));
        }
        return customer;
    }

}

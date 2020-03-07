package com.kannadatechvillage.workshop.interfaces;

public interface AppConstants {
    String DATABASE_NAME = "worksho_app.db";


    int SUCCESS = 1;
    int FAILED = -1;
    int ALREADY_EXISTS = -2;

    String USER_NAME = "name";
    String USER_ID = "id";
    String USER_EMAIL = "email";
    String USER_MOBILE = "mobile";
    String USER_PASSWORD = "password";
    String CREATED_ON = "createdOn";
    String UPDATED_ON = "updatedOn";
    String STATUS = "status";

    String USER_TABLE_NAME = "userinfo";
    String CREATE_USER_TABLE = "CREATE TABLE "+USER_TABLE_NAME+"" +
            "(id INTEGER primary key AUTOINCREMENT," +
            "name VARCHAR(50)," +
            "email VARCHAR(100)," +
            "mobile VARCHAR(15)," +
            "password VARCHAR(100)," +
            "address VARCHAR(100)," +
            "status VARCHAR(10),"+
            "createdOn long," +
            "updatedOn long)";

    String CUSTOMER_TABLE_NAME = "customer";
    String CUSTOMER_NAME = "name";
    String CUSTOMER_EMAIL = "email";
    String CUSTOMER_ADDRESS = "address";
    String CUSTOMER_MOBILE = "mobile";
    String CUSTOMER_GENDER = "gender";
    String CUSTOMER_CITY = "city";

    String CREATE_CUSTOMER_TABLE = "CREATE TABLE "+CUSTOMER_TABLE_NAME+"" +
            "(id INTEGER primary key AUTOINCREMENT," +
            "name VARCHAR(50)," +
            "email VARCHAR(100)," +
            "mobile VARCHAR(15)," +
            "address VARCHAR(100)," +
            "gender VARCHAR(10)," +
            "status VARCHAR(10)," +
            "city VARCHAR(20)," +
            "createdOn long," +
            "updatedOn long)";
}

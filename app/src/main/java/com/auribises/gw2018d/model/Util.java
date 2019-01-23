package com.auribises.gw2018d.model;

import android.net.Uri;

// This Util Contains Constants w.r.t. our Database and Table
public class Util {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Customer.db";

    public static final String TAB_NAME = "Customers";
    public static final String CREATE_TAB_QUERY = "create table Customers(" +
            "_ID integer primary key autoincrement," +
            "name varchar(256)," +
            "phone varchar(256)," +
            "email varchar(256)" +
            ")";

    public static final Uri TAB_URI = Uri.parse("content://com.auribises.gw2018d.ccp/"+TAB_NAME);
}

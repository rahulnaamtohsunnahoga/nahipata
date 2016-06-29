package com.tcs.codetest;

import android.provider.BaseColumns;

/**
 * Created by 983798 on 6/28/2016.
 */
public class EmployeeTable implements BaseColumns {


    public  static final String NAME="Name";
    public  static final String DEPT="Department";
    public  static final String PHONE="Phone";


    public  static final String TABLE_NAME="Employee";

    public  static final String CREATE="create table "+TABLE_NAME+" ("
            +_ID+" integer primary key autoincrement, "
            +NAME+" text not null, "
            +DEPT+" text not null, "
            +PHONE+" text not null); ";
}


package com.tcs.codetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 983798 on 6/28/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public  static final String DB_NAME="DATABASE_EMP";
    public  static final int DB_VERSION=1;

    public  static final String[] mColumnNames=new String[]{EmployeeTable.NAME,EmployeeTable.DEPT,EmployeeTable.PHONE,
            EmployeeTable._ID};


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EmployeeTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static long insert(Context context,ContentValues cv) {

        try{
            long res=new DatabaseHelper(context).getWritableDatabase().insert(EmployeeTable.TABLE_NAME, null, cv);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }


    public static Cursor retrieve(Context context) {

        try{
            Cursor res=new DatabaseHelper(context).getWritableDatabase().query(EmployeeTable.TABLE_NAME, mColumnNames, null, null, null, null, null);
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



}


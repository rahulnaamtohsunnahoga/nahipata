package com.tcs.codetest;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by 983798 on 6/28/2016.
 */
public class RetrieveAsyncTask extends AsyncTask<String,Void,Cursor> {

    Context mContext;
    DataRecieved mCallback;

    public RetrieveAsyncTask(Context mContext) {
        this.mContext = mContext;
        mCallback=(DataRecieved)mContext;
        execute();
    }

    @Override
    protected Cursor doInBackground(String... params) {
        Cursor res= DatabaseHelper.retrieve(mContext);
        return res;
    }


    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        int length=cursor.getCount();
        HomeScreen.sEmployeeList.clear();
        cursor.moveToFirst();
        if(length==0){
            Toast.makeText(mContext, "Nothing to Display", Toast.LENGTH_SHORT).show();
        }
        else{

            for(int i=0;i<length;i++) {
                Employees emp = new Employees();

                emp.setmID(cursor.getString(cursor.getColumnIndex(EmployeeTable._ID)));
                emp.setmName(cursor.getString(cursor.getColumnIndex(EmployeeTable.NAME)));
                emp.setmDept(cursor.getString(cursor.getColumnIndex(EmployeeTable.DEPT)));
                emp.setmPhone(cursor.getString(cursor.getColumnIndex(EmployeeTable.PHONE)));

                HomeScreen.sEmployeeList.add(emp);
                cursor.moveToNext();

            }
        }

        mCallback.onListEdited();

    }

}

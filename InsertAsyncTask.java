package com.tcs.codetest;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by 983798 on 6/28/2016.
 */
public class InsertAsyncTask extends AsyncTask<String,Void,Long> {

    Context mContext;
    ContentValues mCValues;
    DataRecieved mCallback;

    public InsertAsyncTask(Context mContext, ContentValues mCValues,DataRecieved mCallback) {
        this.mContext = mContext;
        this.mCallback=(DataRecieved)mContext;
        this.mCValues = mCValues;
        execute();
    }

    @Override
    protected Long doInBackground(String... params) {
        long res= DatabaseHelper.insert(mContext, mCValues);
        return res;
    }


    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if(aLong>0){
            Toast.makeText(mContext, "Successfully added Employee", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext,"Error adding Employee",Toast.LENGTH_SHORT).show();
        }
    }
}


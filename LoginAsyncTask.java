package com.tcs.codetest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by 983798 on 6/28/2016.
 */
public class LoginAsyncTask  extends AsyncTask<String,Integer,String> {
    Context mContext;
    MyCallBackInterface mCallback;
    ProgressDialog mProgressDialog;

    public LoginAsyncTask(Context contxt,MyCallBackInterface callback) {
        mContext=contxt;
        mCallback=callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog=new ProgressDialog(mContext);
        mProgressDialog.setMessage("Retrieving...");
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String str=new ConnectionClass().getData(params[0]);
        return str;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressDialog.setProgress(values[0]);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        mCallback.dataReceived(s);

    }


}


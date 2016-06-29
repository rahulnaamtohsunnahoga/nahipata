package com.tcs.codetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements MyCallBackInterface{

    EditText mUsername;
    EditText mPassword;
    Button mLogBtn;

    private int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername=(EditText) findViewById(R.id.user_tv);
        mPassword=(EditText)findViewById(R.id.pass_tv);
        mLogBtn=(Button)findViewById(R.id.log_button);
        mLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter username!!", Toast.LENGTH_SHORT).show();
                } else if (mPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter password!!", Toast.LENGTH_SHORT).show();
                } else{
                    new LoginAsyncTask(LoginActivity.this,LoginActivity.this).execute("http://172.26.53.11:8080/employee.json");
                }
            }
        });
    }

    @Override
    public void dataReceived(String str) {
        try {
            JSONObject object=new JSONObject(str);
            String firstJsonKey="Sample";
            JSONArray arrayOfUsers=object.getJSONArray(firstJsonKey);
            for(int i=0;i<arrayOfUsers.length();i++){
                JSONObject arrayObject=arrayOfUsers.getJSONObject(i);
                String user=arrayObject.optString("key for username");
                String pass=arrayObject.optString("key for password");
                if(user.equals(mUsername)&& pass.equals(mPassword)){
                    check=1;
                    break;
                }
            }

            if (check==1){
                Toast.makeText(LoginActivity.this,"Logging in",Toast.LENGTH_SHORT).show();
                Intent logIntent=new Intent(LoginActivity.this,HomeScreen.class);
                startActivity(logIntent);
            }
            else {
                Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                mUsername.setText("");
                mPassword.setText("");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

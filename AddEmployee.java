package com.tcs.codetest;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployee extends AppCompatActivity implements DataRecieved {

    Toolbar toolbar;
    EditText mName;
    EditText mDept;
    EditText mPhone;

    Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        toolbar=(Toolbar)findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

        mName=(EditText)findViewById(R.id.ed_Name);
        mDept=(EditText)findViewById(R.id.ed_Dept);
        mPhone=(EditText)findViewById(R.id.ed_Phone);


        mAdd=(Button)findViewById(R.id.add_button);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName.getText().toString().isEmpty() || mDept.getText().toString().isEmpty() || mPhone.getText().toString().isEmpty()) {
                    Toast.makeText(AddEmployee.this, "Fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(EmployeeTable.NAME, mName.getText().toString());
                    cv.put(EmployeeTable.DEPT, mDept.getText().toString());
                    cv.put(EmployeeTable.PHONE, mPhone.getText().toString());


                    new InsertAsyncTask(AddEmployee.this,cv,AddEmployee.this);

                }
            }
        });

    }

    @Override
    public void onListEdited() {
        Intent homeIntent=new Intent(AddEmployee.this,HomeScreen.class);
        startActivity(homeIntent);
    }
}

package com.tcs.codetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity implements DataRecieved{

    public static List<Employees> sEmployeeList=new ArrayList<>();

    Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    EmployeeAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HomeScreen");


        mRecyclerView=(RecyclerView)findViewById(R.id.recycler);
        mLayoutManager=new LinearLayoutManager(HomeScreen.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new EmployeeAdapter(HomeScreen.this);
        mRecyclerView.setAdapter(mAdapter);

        new RetrieveAsyncTask(HomeScreen.this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_emp) {
            Intent addIntent=new Intent(HomeScreen.this,AddEmployee.class);
            startActivity(addIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onListEdited() {
        mAdapter.notifyDataSetChanged();

    }
}

package com.tcs.codetest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 983798 on 6/28/2016.
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    DataRecieved mCallback;
    TextView mName;
    TextView mDept;
    TextView mPhone;

    public EmployeeAdapter(DataRecieved mCallback) {
        this.mCallback = mCallback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.employee_layout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder viewHolder, int i) {
        mName.setText(HomeScreen.sEmployeeList.get(i).getmName());
        mDept.setText(HomeScreen.sEmployeeList.get(i).getmDept());
        mPhone.setText(HomeScreen.sEmployeeList.get(i).getmPhone());
    }

    @Override
    public int getItemCount() {
        return HomeScreen.sEmployeeList.size();
    }


}

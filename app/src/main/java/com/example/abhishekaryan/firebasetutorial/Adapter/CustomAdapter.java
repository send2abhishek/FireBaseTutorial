package com.example.abhishekaryan.firebasetutorial.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.abhishekaryan.firebasetutorial.R;

import com.example.abhishekaryan.firebasetutorial.utils.DataModel;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<DataModel> data;
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapter(Context context) {
        this.context = context;
        data=new ArrayList<>();
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public DataModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.listview_layout,parent,false);

        TextView email=(TextView) view.findViewById(R.id.listview_layout_email);
        TextView name=(TextView) view.findViewById(R.id.listview_layout_name);

        DataModel dataModel=getItem(position);
        email.setText(dataModel.getEmail());
        name.setText(dataModel.getName());

        return view;
    }
    public void AddItem(DataModel dataModel){

        data.add(new DataModel(dataModel.getId(),dataModel.getName(),dataModel.getEmail()));
    }
}

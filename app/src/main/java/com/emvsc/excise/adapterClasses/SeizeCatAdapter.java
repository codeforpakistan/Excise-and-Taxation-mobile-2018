package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.emvsc.excise.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class SeizeCatAdapter extends ArrayAdapter<String> {

    ArrayList<String> id;
    ArrayList<String> name ;
    Map<String, Boolean> check = new HashMap<>();
    Context context;

    public SeizeCatAdapter(Context context,ArrayList<String> id,ArrayList<String> name) {
        super(context, R.layout.seize_cat_adpater, id);
        this.id = id;
        this.name = name;
        this.context = context;
        for(int i = 0 ; i< id.size(); i++)
        {
            check.put(id.get(i), false);
        }
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.seize_cat_adpater,null,true);
        TextView seizeName = convertView.findViewById(R.id.seize_name);
        final CheckBox seize_checkbox = convertView.findViewById(R.id.seize_checkbox);
        if (convertView != null){
                    seize_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            check.put(id.get(position), b);
                            Log.e("CheckBox", "onCheckedChanged : "+b );
                            Log.e("CheckBox Data", "data : "+check );

                }
            });


        }
        seizeName.setText(name.get(position));
        return convertView;
    }
    public ArrayList<String> getSeizeId() {
        ArrayList<String> data = new ArrayList<>();
        for(int i =0 ; i < id.size() ; i++)
        {
            if(check.get(id.get(i)) == Boolean.TRUE) {
                data.add(""+id.get(i));
                Log.e("check data", "getId: "+data );
            }
        }
        return data;
    }
    public ArrayList<String> getSeizeName() {
        ArrayList<String> data = new ArrayList<>();
        for(int i =0 ; i < id.size() ; i++)
        {
            if(check.get(id.get(i)) == Boolean.TRUE) {
                data.add(""+name.get(i));
                Log.e("check data", "getId: "+data );
            }
        }
        return data;
    }
}

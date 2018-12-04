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
import com.emvsc.excise.modelClasses.FormBAccessories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class UncheckedAdapter extends ArrayAdapter<FormBAccessories.AccessoriesUnchecked> {

    List<FormBAccessories.AccessoriesUnchecked> mUncheckedList;
    Context context;
    Map<String, Boolean> check = new HashMap<>();

    public UncheckedAdapter(Context context, List<FormBAccessories.AccessoriesUnchecked> mUncheckedList) {
        super(context, R.layout.unchecked_adpater, mUncheckedList);
        this.mUncheckedList = mUncheckedList;
        this.context = context;
        for(int i = 0 ; i< mUncheckedList.size(); i++)
        {

            check.put(mUncheckedList.get(i).getId(), false);
        }
        Log.e("check size", check.toString() );
    }
    //Holder Class
    private class ViewHolder{
        TextView unchecked_accessory_name;
        CheckBox formb_check;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.unchecked_adpater,null);
        holder = new ViewHolder();
        holder.unchecked_accessory_name = convertView.findViewById(R.id.unchecked_accessory_name);
        holder.formb_check = convertView.findViewById(R.id.formb_check);

        holder.unchecked_accessory_name.setText(mUncheckedList.get(position).getName());
        Log.e("uncheck name", mUncheckedList.get(position).getName());
        Log.e("uncheck id", mUncheckedList.get(position).getId());
        Log.e("uncheck status", ""+mUncheckedList.get(position).getStatus());
        if (convertView != null){
            holder.formb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    check.put(mUncheckedList.get(position).getId(), b);
                    Log.e("CheckBox", "onCheckedChanged : "+b );
                    Log.e("CheckBox Data", "data : "+check );
                }
            });
        }

        return convertView;
    }

    public ArrayList<String> getId() {
        ArrayList<String> data = new ArrayList<>();
        for(int i =0 ; i < mUncheckedList.size() ; i++)
        {
            if(check.get(mUncheckedList.get(i).getId()) == Boolean.TRUE) {
                data.add(""+mUncheckedList.get(i).getId());
                Log.e("check data", "getId: "+data );
            }
        }

        return data;
    }
}

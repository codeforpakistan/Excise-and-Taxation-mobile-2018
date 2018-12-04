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
import java.util.List;
import java.util.Map;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class VehicleAccessoriesAdapter extends ArrayAdapter<String> {

    ArrayList<String> id ;
    ArrayList<String> name ;
    Context context;
    Map<String, Boolean> check = new HashMap<>();

    public VehicleAccessoriesAdapter(Context context, ArrayList<String> id, ArrayList<String> name) {
        super(context, R.layout.vehicle_assories_adpater, id);
        this.id = id;
        this.name = name;
        this.context = context;
        for(int i = 0 ; i< id.size(); i++)
        {

            check.put(id.get(i), false);
        }
    }

    //Holder Class
    private class ViewHolder{
        TextView accessory_id, accessoryName;
        CheckBox checkBox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.vehicle_assories_adpater,null);
        holder = new ViewHolder();
        holder.accessory_id = (TextView) convertView.findViewById(R.id.accessory_id);
        holder.accessoryName = (TextView) convertView.findViewById(R.id.accessory_name);
        holder.checkBox = convertView.findViewById(R.id.accessory_checkbox);

        holder.accessory_id.setText(id.get(position));
        holder.accessoryName.setText(name.get(position));
        if (convertView != null){
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    check.put(id.get(position), b);
                    Log.e("CheckBox", "onCheckedChanged : "+b );
                    Log.e("CheckBox Data", "data : "+check);
                }
            });
        }
        return convertView;
    }
    public ArrayList<String> getId() {
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
}

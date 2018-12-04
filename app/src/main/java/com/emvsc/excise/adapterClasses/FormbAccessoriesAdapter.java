package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.emvsc.excise.R;

import com.emvsc.excise.modelClasses.WhmSeizeVehicleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class FormbAccessoriesAdapter extends ArrayAdapter<WhmSeizeVehicleData.VehicleAccessory> {

    List<WhmSeizeVehicleData.VehicleAccessory> list;
    Context context;

    public FormbAccessoriesAdapter(Context context, List<WhmSeizeVehicleData.VehicleAccessory> list) {
        super(context, R.layout.formb_accessories_adpater, list);
        this.context = context;
        this.list = list;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.formb_accessories_adpater,null,true);
        TextView accessory_name = convertView.findViewById(R.id.accessory_name);

        accessory_name.setText(list.get(position).getAccessoryname());

        return convertView;
    }
}

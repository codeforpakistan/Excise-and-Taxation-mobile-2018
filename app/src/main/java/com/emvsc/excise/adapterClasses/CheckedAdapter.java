package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.emvsc.excise.R;
import com.emvsc.excise.modelClasses.FormBAccessories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class CheckedAdapter extends ArrayAdapter<FormBAccessories.AccessoriesChecked> {

    List<FormBAccessories.AccessoriesChecked> checkList ;
    Context context;

    public CheckedAdapter(Context context, List<FormBAccessories.AccessoriesChecked> checkList) {
        super(context, R.layout.checked_adpater, checkList);
        this.checkList = checkList;
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.checked_adpater,null,true);
        TextView checked_accessory_name = convertView.findViewById(R.id.checked_accessory_name);


        checked_accessory_name.setText(checkList.get(position).getName());

        return convertView;
    }
}

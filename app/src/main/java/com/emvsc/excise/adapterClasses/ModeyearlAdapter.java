package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.emvsc.excise.R;

import java.util.ArrayList;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class ModeyearlAdapter extends ArrayAdapter<String> {

    ArrayList<String> id;
    ArrayList<String> name ;
    Context context;

    public ModeyearlAdapter(Context context, ArrayList<String> id, ArrayList<String> name) {
        super(context, R.layout.model_year_adpater, id);
        this.id = id;
        this.name = name;
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.model_year_adpater,null,true);
        TextView model_year_name = convertView.findViewById(R.id.model_year_name);

        model_year_name.setText(name.get(position));

        return convertView;
    }
}

package com.emvsc.excise.adapterClasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.emvsc.excise.R;
import com.emvsc.excise.modelClasses.WhmSeizeInspectorVehicleData;
import com.emvsc.excise.modelClasses.WhmSeizeVehicleData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahzaib on 20-Jul-18.
 */

public class SeizeInspectorAdapter extends RecyclerView.Adapter<SeizeInspectorAdapter.MyViewHolder> {
    private List<WhmSeizeInspectorVehicleData> vehicleList;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seize_inspector_adapter, parent, false);
        return new MyViewHolder(view);
    }

    public SeizeInspectorAdapter(List<WhmSeizeInspectorVehicleData> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WhmSeizeInspectorVehicleData whmSeizeInspectorVehicleData = vehicleList.get(position);
        String formNo = whmSeizeInspectorVehicleData.getFormserialno();
        String seizeCat = whmSeizeInspectorVehicleData.getSeizedtype();
        String chasis_no = whmSeizeInspectorVehicleData.getChasisno();
        String eto_name = whmSeizeInspectorVehicleData.getDistrictname();
        String time = whmSeizeInspectorVehicleData.getSiezedtime();
        String date = whmSeizeInspectorVehicleData.getDatesiezeddate();
        holder.inspector_form_no.setText("Form A # " + formNo);
        holder.inspector_chasis_no.setText("Chassis # "+ chasis_no);
        holder.inspector_eto_name.setText("From ETO: "+ eto_name);
        holder.inspector_seize_cat.setText(seizeCat);
        holder.inspector_time.setText(time);


        String [] dateParts = date.split("-");
        String dayString = dateParts[0];
        String monthString = dateParts[1];
        String yearString = dateParts[2];
        holder.inspector_day.setText(dayString);
        holder.inspector_month.setText(monthString);
        holder.inspector_year.setText(yearString);
        Log.e("dayString", "onBindViewHolder: "+dayString );
        Log.e("monthString", "onBindViewHolder: "+monthString );
        Log.e("yearString", "onBindViewHolder: "+yearString );




    }


    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void setFilter(List<WhmSeizeInspectorVehicleData> list){
        vehicleList = new ArrayList<>();
        vehicleList.addAll(list);
        notifyDataSetChanged();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //defining views
        TextView inspector_day, inspector_month, inspector_year, inspector_form_no, inspector_chasis_no, inspector_seize_cat, inspector_time, inspector_eto_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            inspector_day = itemView.findViewById(R.id.inspector_day);
            inspector_month = itemView.findViewById(R.id.inspector_month);
            inspector_year = itemView.findViewById(R.id.inspector_year);
            inspector_form_no = itemView.findViewById(R.id.inspector_form_no);
            inspector_chasis_no = itemView.findViewById(R.id.inspector_chasis_no);
            inspector_seize_cat = itemView.findViewById(R.id.inspector_seize_cat);
            inspector_time = itemView.findViewById(R.id.inspector_time);
            inspector_eto_name = itemView.findViewById(R.id.inspector_eto_name);
        }
    }
}

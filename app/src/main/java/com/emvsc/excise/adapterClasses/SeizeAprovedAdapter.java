package com.emvsc.excise.adapterClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.emvsc.excise.R;
import com.emvsc.excise.interfaceClasses.OnItemClickListener;
import com.emvsc.excise.javaClasses.FormBActivity;
import com.emvsc.excise.javaClasses.FormBDetailsActivity;
import com.emvsc.excise.modelClasses.SeizedVechile;
import com.emvsc.excise.modelClasses.WhmSeizeVehicle;
import com.emvsc.excise.modelClasses.WhmSeizeVehicleData;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shahzaib on 20-Jul-18.
 */

public class SeizeAprovedAdapter extends RecyclerView.Adapter<SeizeAprovedAdapter.MyViewHolder> {
    List<WhmSeizeVehicleData> vehicleList;
    WhmSeizeVehicleData mWhmSeizeVehicleData;
    List<WhmSeizeVehicleData.VehicleImage> imageList = new ArrayList<>();
    private final OnItemClickListener listener;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seize_approved_adapter, parent, false);
        return new MyViewHolder(view);
    }

    public SeizeAprovedAdapter(List<WhmSeizeVehicleData> vehicleList, OnItemClickListener listener) {
        this.vehicleList = vehicleList;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mWhmSeizeVehicleData = new WhmSeizeVehicleData(vehicleList.get(position).getVehicle_status(),vehicleList.get(position).getMobilesquadno(), vehicleList.get(position).getUsername(), vehicleList.get(position).getDistrictname(), vehicleList.get(position).getSeizedtype(), vehicleList.get(position).getDatesiezeddate(), vehicleList.get(position).getSiezedtime(), vehicleList.get(position).getFormserialno(), vehicleList.get(position).getSeizedlocationlat(), vehicleList.get(position).getDrivername(), vehicleList.get(position).getSeizedlocationlong(), vehicleList.get(position).getChasisno(), vehicleList.get(position).getEngineno(), vehicleList.get(position).getVechileregistrationno(), vehicleList.get(position).getMakename(), vehicleList.get(position).getSubmakename(), vehicleList.get(position).getModelyear(), vehicleList.get(position).getVehicletype(), vehicleList.get(position).getDrivercnicno(), vehicleList.get(position).getDrivermobileno(), vehicleList.get(position).getDriveraddress(), vehicleList.get(position).getVechileownername(), vehicleList.get(position).getVechileownercnic(), vehicleList.get(position).getVechileownermobileno(), vehicleList.get(position).getVechileid(), vehicleList.get(position).getBodybuildname(), vehicleList.get(position).getColorname(), vehicleList.get(position).getTransmission(), vehicleList.get(position).getAssembely(), vehicleList.get(position).getWheelnumber(), vehicleList.get(position).getEnginetype(), vehicleList.get(position).getVehicleengineCapcaity(), vehicleList.get(position).getMileage(), vehicleList.get(position).getVechicledescription(), vehicleList.get(position).getApprovedDate(), vehicleList.get(position).getApprovedTime());
        String status = mWhmSeizeVehicleData.getVehicle_status();
        String formNo = mWhmSeizeVehicleData.getFormserialno();
        String seizeCat = mWhmSeizeVehicleData.getSeizedtype();
        String chasis_no = mWhmSeizeVehicleData.getChasisno();
        String district_name = mWhmSeizeVehicleData.getDistrictname();
        String date = mWhmSeizeVehicleData.getDatesiezeddate();
        String time = mWhmSeizeVehicleData.getSiezedtime();
        imageList = vehicleList.get(position).getVehicleImages();
        holder.approve_form_no.setText("Form A # " + formNo);
        holder.approve_chasis_no.setText("Chassis # " + chasis_no);
        holder.approve_eto_name.setText("ETO: "+district_name.toLowerCase());
        holder.seize_time.setText(time);
        holder.approve_seize_cat.setText(seizeCat);
        holder.veh_status.setText(status);
        holder.bind(vehicleList.get(position), listener);

        String [] dateParts = date.split("-");
        String dayString = dateParts[0];
        String monthString = dateParts[1];
        String yearString = dateParts[2];
        holder.approve_day.setText(dayString);
        holder.approve_month.setText(monthString);
        holder.approve_year.setText(yearString);

    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public void setFilter(List<WhmSeizeVehicleData> list){
        vehicleList = new ArrayList<>();
        vehicleList.addAll(list);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //defining views
        TextView veh_status, approve_day, approve_month, approve_year, approve_form_no, approve_chasis_no, approve_seize_cat, seize_time, approve_eto_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            veh_status = itemView.findViewById(R.id.veh_status);
            approve_day = itemView.findViewById(R.id.approve_day);
            approve_month = itemView.findViewById(R.id.approve_month);
            approve_year = itemView.findViewById(R.id.approve_year);
            approve_form_no = itemView.findViewById(R.id.approve_form_no);
            approve_chasis_no = itemView.findViewById(R.id.approve_chasis_no);
            approve_seize_cat = itemView.findViewById(R.id.approve_seize_cat);
            seize_time = itemView.findViewById(R.id.seize_time);
            approve_eto_name = itemView.findViewById(R.id.approve_eto_name);
        }
        public void bind(final WhmSeizeVehicleData item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

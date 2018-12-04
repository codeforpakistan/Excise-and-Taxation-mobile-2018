package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.airbnb.lottie.animation.content.Content;
import com.emvsc.excise.R;
import com.emvsc.excise.dbClasses.DbConstants;
import com.emvsc.excise.dbClasses.DbHelper;
import com.emvsc.excise.modelClasses.SeizedVechile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by shahzaib on 20-Jul-18.
 */

public class VehicleHistoryAdapter extends RecyclerView.Adapter<VehicleHistoryAdapter.MyViewHolder> {
    ArrayList<HashMap<String, String>> mMapArrayList = new ArrayList<>();
    private List<SeizedVechile> vehicleList;
    public static String strSeparator = "__,__";
    Context mContext;
    DbHelper mDbHelper;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_history_adapter, parent, false);
        return new MyViewHolder(view);
    }

    public VehicleHistoryAdapter(Context ctx, List<SeizedVechile> vehicleList) {
        this.vehicleList = vehicleList;
        this.mContext = ctx;
        mDbHelper = new DbHelper(ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SeizedVechile seizedVechile = vehicleList.get(position);
        String formNo = seizedVechile.getFormserialno();
        String districtId = seizedVechile.getDistrictId();
        String seizeCat = seizedVechile.getVehicleseizeCategory();
        String seizeTime = seizedVechile.getSiezedtime();
        String date = seizedVechile.getDatesiezeddate();
        holder.form_no.setText("Form A # " + formNo);
        holder.time.setText(seizeTime);
        holder.seize_cat.setText(convertStringToArray(seizeCat).toString().replace("[", "").replace("]", ""));
        mMapArrayList = mDbHelper.getSpecificDistrictData(districtId);
        for (int i = 0; i < mMapArrayList.size(); i++) {
            holder.district.setText(mMapArrayList.get(i).get(DbConstants.DISTRICT_NAME));

        }

        String [] dateParts = date.split("-");
        String dayString = dateParts[0];
        String monthString = dateParts[1];
        String yearString = dateParts[2];
        holder.day.setText(dayString);
         holder.month.setText(monthString);
        holder.year.setText(yearString);
        Log.e("dayString", "onBindViewHolder: "+dayString );
        Log.e("monthString", "onBindViewHolder: "+monthString );
        Log.e("yearString", "onBindViewHolder: "+yearString );

    }


    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //defining views
        TextView day, month, year, form_no, seize_cat, district, time;
        public MyViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            month = itemView.findViewById(R.id.month);
            year = itemView.findViewById(R.id.year);
            form_no = itemView.findViewById(R.id.form_no);
            seize_cat = itemView.findViewById(R.id.seize_cat);
            district = itemView.findViewById(R.id.district);
            time = itemView.findViewById(R.id.time);
        }
    }

    public static List<String> convertStringToArray(String str){
        return Arrays.asList(str.split(strSeparator));
    }

    public void removeItem(){
        notifyDataSetChanged();
    }


}

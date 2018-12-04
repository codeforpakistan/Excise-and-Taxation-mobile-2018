package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emvsc.excise.R;

import com.emvsc.excise.modelClasses.DistrictData;



import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class DistrictAdapter extends ArrayAdapter<DistrictData> implements Filterable {

    public Context context;
    private ArrayList<DistrictData> mOriginalValues; // Original Values
    private ArrayList<DistrictData> mDisplayedValues;    // Values to be displayed
    public DistrictAdapter(Context context, ArrayList<DistrictData> mOriginalValues ) {
        super(context, R.layout.district_adpater, mOriginalValues);
        this.context = context;
        this.mOriginalValues = mOriginalValues;
        this.mDisplayedValues = mOriginalValues;
    }

    public class DistrictDataHolder
    {
        TextView name;
        RelativeLayout container;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DistrictDataHolder holder ;
        if(convertView==null)
        {
            holder = new DistrictDataHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.district_adpater, parent, false);
            holder.name = convertView.findViewById(R.id.district_name);
            holder.container = convertView.findViewById(R.id.container);
            convertView.setTag(holder);
        }
        else
        {
            holder=(DistrictDataHolder) convertView.getTag();
        }

        holder.name.setText(mDisplayedValues.get(position).getDistrictname());
     /*   holder.container.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(context, mDisplayedValues.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;

    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<DistrictData>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<DistrictData> FilteredArrList = new ArrayList<DistrictData>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).districtname;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new DistrictData(mOriginalValues.get(i).districtid,mOriginalValues.get(i).districtname));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    public ArrayList<DistrictData> getList(){
        return mDisplayedValues;
    }

}

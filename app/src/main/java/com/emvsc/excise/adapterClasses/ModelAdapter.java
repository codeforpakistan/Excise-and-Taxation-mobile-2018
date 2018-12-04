package com.emvsc.excise.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.emvsc.excise.R;
import com.emvsc.excise.modelClasses.MakeData;
import com.emvsc.excise.modelClasses.ModelData;

import java.util.ArrayList;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class ModelAdapter extends ArrayAdapter<ModelData> implements Filterable {

    public Context context;
    private ArrayList<ModelData> mOriginalValues; // Original Values
    private ArrayList<ModelData> mDisplayedValues;    // Values to be displayed
    public ModelAdapter(Context context, ArrayList<ModelData> mOriginalValues ) {
        super(context, R.layout.make_adpater, mOriginalValues);
        this.context = context;
        this.mOriginalValues = mOriginalValues;
        this.mDisplayedValues = mOriginalValues;
    }

    public class ModelDataHolder
    {
        TextView name;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ModelDataHolder holder ;
        if(convertView==null)
        {
            holder = new ModelDataHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.make_adpater, parent, false);
            holder.name = convertView.findViewById(R.id.make_name);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ModelDataHolder) convertView.getTag();
        }

        holder.name.setText(mDisplayedValues.get(position).getSubmakename());
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

                mDisplayedValues = (ArrayList<ModelData>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<ModelData> FilteredArrList = new ArrayList<>();

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
                        String data = mOriginalValues.get(i).submakename;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new ModelData(mOriginalValues.get(i).submakeid, mOriginalValues.get(i).submakename));
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

    public ArrayList<ModelData> getList(){
        return mDisplayedValues;
    }

}

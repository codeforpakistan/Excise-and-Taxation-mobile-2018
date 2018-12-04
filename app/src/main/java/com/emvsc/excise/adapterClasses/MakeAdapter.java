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

import java.util.ArrayList;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class MakeAdapter extends ArrayAdapter<MakeData> implements Filterable {

    public Context context;
    private ArrayList<MakeData> mOriginalValues; // Original Values
    private ArrayList<MakeData> mDisplayedValues;    // Values to be displayed
    public MakeAdapter(Context context, ArrayList<MakeData> mOriginalValues ) {
        super(context, R.layout.make_adpater, mOriginalValues);
        this.context = context;
        this.mOriginalValues = mOriginalValues;
        this.mDisplayedValues = mOriginalValues;
    }

    public class MakeDataHolder
    {
        TextView name;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MakeDataHolder holder ;
        if(convertView==null)
        {
            holder = new MakeDataHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.make_adpater, parent, false);
            holder.name = convertView.findViewById(R.id.make_name);
            convertView.setTag(holder);
        }
        else
        {
            holder=(MakeDataHolder) convertView.getTag();
        }

        holder.name.setText(mDisplayedValues.get(position).getMakename());
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

                mDisplayedValues = (ArrayList<MakeData>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<MakeData> FilteredArrList = new ArrayList<MakeData>();

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
                        String data = mOriginalValues.get(i).makename;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new MakeData(mOriginalValues.get(i).makeid ,mOriginalValues.get(i).makename));
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

    public ArrayList<MakeData> getList(){
        return mDisplayedValues;
    }

}


package com.emvsc.excise.fragmentClasses;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.SeizeInspectorAdapter;
import com.emvsc.excise.interfaceClasses.WhmSeizedApi;
import com.emvsc.excise.modelClasses.CustomMessageEvent2;
import com.emvsc.excise.modelClasses.RefreshTab1;
import com.emvsc.excise.modelClasses.WhmSeizeInspectorVehicle;
import com.emvsc.excise.modelClasses.WhmSeizeInspectorVehicleData;
import com.emvsc.excise.utilClasses.Config;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TwoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView.LayoutManager mLayoutManager;
    SeizeInspectorAdapter seizeInspectorAdapter;
    List<WhmSeizeInspectorVehicleData> list;
    ImageView frame_two_no_record;
    FrameLayout mFrameLayout;
    LinearLayout internet_tap2;

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, container, false);
        setUi(v);
        EventBus.getDefault().register(this);
        return v;
    }
    private void setUi(View v) {
        mFrameLayout = v.findViewById(R.id.fragment_two);
        mRecyclerView = v.findViewById(R.id.seize_inspector_list);
        mSwipeRefreshLayout = v.findViewById(R.id.seize_inspector_swipe);
        mSwipeRefreshLayout.setRefreshing(true);
        frame_two_no_record = v.findViewById(R.id.frame_two_no_record);
        internet_tap2 = v.findViewById(R.id.internet_tap2);
        internet_tap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadInspectorList();
            }
        });
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadInspectorList();
            }
        });

                if (!isNetworkAvailable()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            frame_two_no_record.setVisibility(View.GONE);
                            internet_tap2.setVisibility(View.VISIBLE);


                        }
                    });
                }else {

                    loadInspectorList();

                }



    }
    public void loadInspectorList() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WhmSeizedApi whmSeizedApi = retrofit.create(WhmSeizedApi.class);
        Call<WhmSeizeInspectorVehicle> call = whmSeizedApi.getInsepctorList();
        call.enqueue(new Callback<WhmSeizeInspectorVehicle>() {
            @Override
            public void onResponse(Call<WhmSeizeInspectorVehicle> call, Response<WhmSeizeInspectorVehicle> response) {
                final WhmSeizeInspectorVehicle whmSeizeInspectorVehicle =  response.body();
                int success = whmSeizeInspectorVehicle.getSuccess();
                Log.e("Success", "tab 2: "+success );
                if (response.isSuccessful()){
                    if (success == 1){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list = whmSeizeInspectorVehicle.getSeizedData();
                                int list_size = list.size();
                                internet_tap2.setVisibility(View.GONE);
                                CustomMessageEvent2 customMessageEvent = new CustomMessageEvent2();
                                customMessageEvent.setTotalInspectorReports(String.valueOf(list.size()));
                                EventBus.getDefault().post(customMessageEvent);
                                seizeInspectorAdapter = new SeizeInspectorAdapter(list);
                                mRecyclerView.setAdapter(seizeInspectorAdapter);
                                seizeInspectorAdapter.notifyDataSetChanged();
                                mSwipeRefreshLayout.setRefreshing(false);
                                runLayoutAnimation(mRecyclerView);
                            }
                        });

                    }else if (success == 0){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                frame_two_no_record.setVisibility(View.VISIBLE);

                                mSwipeRefreshLayout.setRefreshing(false);
                                internet_tap2.setVisibility(View.GONE);
                            }
                        });
                    }
                    else {

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    frame_two_no_record.setVisibility(View.VISIBLE);
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    internet_tap2.setVisibility(View.GONE);
                                }
                            });

                    }
                }
            }

            @Override
            public void onFailure(Call<WhmSeizeInspectorVehicle> call, Throwable t) {
                Log.e("Failure", "onFailure: "+t.toString() );
                Snackbar snackbar = Snackbar.make(mFrameLayout, "No Internet connection", Snackbar.LENGTH_SHORT);
                snackbar.show();


            }
        });
    }

    public void beginSearch(String query) {
        Log.e("QueryFragment", query);
        final List<WhmSeizeInspectorVehicleData> filterModelList = filter(list, query);
        seizeInspectorAdapter.setFilter(filterModelList);

    }
    private List<WhmSeizeInspectorVehicleData> filter(List<WhmSeizeInspectorVehicleData> list, String query){
        query = query.toLowerCase();
        final List<WhmSeizeInspectorVehicleData> filterList = new ArrayList<>();
        for (WhmSeizeInspectorVehicleData model : list){
            final String  form_no = model.getFormserialno().toLowerCase().trim();
            final String  chasis_no = model.getChasisno().toLowerCase().trim();
            final String  seize_type = model.getSeizedtype().toLowerCase().trim();
            final String  district_name = model.getDistrictname().toLowerCase().trim();
            if (form_no.startsWith(query)){
                filterList.add(model);
            }else if (chasis_no.startsWith(query)){
                filterList.add(model);
            }else if (seize_type.contains(query)){
                filterList.add(model);
            }else if (district_name.contains(query)){
                filterList.add(model);
            }
        }
        return filterList;
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadInspectorList();
    }
    @Subscribe
    public void RefreshEvent(RefreshTab1 event){
        if (event.getRefreshStatus() == 1){
            loadInspectorList();
        }else {

        }

    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected())
        {
            isAvailable = true;

        }
        return isAvailable;
    }
}


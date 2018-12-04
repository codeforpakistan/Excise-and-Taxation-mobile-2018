package com.emvsc.excise.interfaceClasses;

import com.emvsc.excise.modelClasses.Make;
import com.emvsc.excise.modelClasses.WhmSeizeInspectorVehicle;
import com.emvsc.excise.modelClasses.WhmSeizeVehicle;
import com.emvsc.excise.utilClasses.Config;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shahzaib on 14-Aug-18.
 */

public interface WhmSeizedApi {
    @GET(Config.WhmApprovedList)
    Call<WhmSeizeVehicle> getApprovedList();

    @GET(Config.WhmInspectorList)
    Call<WhmSeizeInspectorVehicle> getInsepctorList();
}

package com.emvsc.excise.interfaceClasses;

import com.emvsc.excise.modelClasses.Make;
import com.emvsc.excise.modelClasses.Model;
import com.emvsc.excise.utilClasses.Config;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shahzaib on 02-Aug-18.
 */

public interface ModelApi {
    @GET(Config.ModelList)
    Call<Model> getModelList();
}

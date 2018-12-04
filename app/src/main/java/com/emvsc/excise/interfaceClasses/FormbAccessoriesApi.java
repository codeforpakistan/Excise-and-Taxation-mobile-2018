package com.emvsc.excise.interfaceClasses;

import com.emvsc.excise.modelClasses.Accessories;
import com.emvsc.excise.modelClasses.FormBAccessories;
import com.emvsc.excise.modelClasses.Login;
import com.emvsc.excise.utilClasses.Config;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by shahzaib on 02-Aug-18.
 */

public interface FormbAccessoriesApi {
    @FormUrlEncoded
    @POST(Config.FORMBACCESSORIESList)
    Call<FormBAccessories> retriveList(@FieldMap Map<String, String> map);
}

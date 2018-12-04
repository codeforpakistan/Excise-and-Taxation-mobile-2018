package com.emvsc.excise.interfaceClasses;


import com.emvsc.excise.modelClasses.Login;
import com.emvsc.excise.utilClasses.Config;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @FormUrlEncoded
    @POST(Config.Login)
    Call<Login> savePost(@FieldMap Map<String, String> map);
}

package com.emvsc.excise.interfaceClasses;


import com.emvsc.excise.modelClasses.FormbPost;
import com.emvsc.excise.modelClasses.SeizePostData;
import com.emvsc.excise.utilClasses.Config;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface SeizedVehicleApi {
    //@FormUrlEncoded
    @Multipart
    @POST(Config.PostSezied)
    Call<List<SeizePostData>> uploadFiles(@PartMap Map<String, RequestBody> data, @PartMap Map<String, RequestBody> seizeCatdata, @PartMap Map<String, RequestBody> accessdata, @Part List<MultipartBody.Part> files);

    @Multipart
    @POST(Config.FormBPost)
    Call<List<FormbPost>> formbData(@PartMap Map<String, RequestBody> data, @PartMap Map<String, RequestBody> accessdata, @Part List<MultipartBody.Part> files);
}

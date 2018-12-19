package com.emvsc.excise.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.VehicleHistoryAdapter;
import com.emvsc.excise.dbClasses.DbHelper;
import com.emvsc.excise.interfaceClasses.SeizedVehicleApi;
import com.emvsc.excise.modelClasses.SeizePostData;
import com.emvsc.excise.modelClasses.SeizedVechile;
import com.emvsc.excise.utilClasses.Config;
import com.emvsc.excise.utilClasses.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_ACCESSORIES;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_BODY_BUILD;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_CAT_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_CAT_NAME;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_CHASIS_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_CURRENT_LATITUDE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_CURRENT_LONGITUDE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DATE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DESCRIPTION;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DISTRICT_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DRIVER_ADDRESS;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DRIVER_CNIC;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DRIVER_MOB_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_DRIVER_NAME;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_ENGINE_CAPICITY;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_ENGINE_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_ENGINE_TYPE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_FORM_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE1;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE2;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE3;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE4;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE5;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE6;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE7;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_IMAGE8;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_MAKE_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_MODEL_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_MODEL_YEAR;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_SQUAD_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_TIME;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_USER_ID;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_ASSEMBELY;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_COLOR;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_MILEAGE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_OWNER_CNIC;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_OWNER_MOB_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_OWNER_NAME;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_REG_NO;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_TRANSMISSION;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_TYPE;
import static com.emvsc.excise.dbClasses.DbConstants.SEIZE_VEHICLE_WEEHLES;

public class VehicleHistoryActivity extends AppCompatActivity {
    DbHelper mDbHelper = new DbHelper(this);
    private static final String TAG = "VehicleHistroy";
    private RecyclerView mRecyclerView;
    SeizedVechile mSeizedVechile;
    //SwipeRefreshLayout swipe;
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    List<SeizedVechile> seizeHistoryList = new ArrayList<>();
    HashMap<String, RequestBody> postMap = new HashMap<>();
    RecyclerView.LayoutManager mLayoutManager;
    List<String> accessorieslist = new ArrayList<>();
    List<String> seizelist = new ArrayList<>();
    String seizeID;
    String formNo;
    String seizeCatId;
    String seizeCatName;
    String seizeDistrict;
    String driverName;
    String driverCnic;
    String driverMobileNo;
    String driverAddress;
    String vehOwnerName;
    String vehOwnerCnic;
    String vehOwnerMobileNo;
    String squadNo = "123";
    String chasisNo;
    String engineNo;
    String vehRegNo;
    String seizeDate;
    String seizeTime;
    String makeId;
    String modelId;
    String modelYear;
    String vehicleType;
    String bodyBuild;
    String vehicleColor;
    String vehicleTransmission;
    String vehicleAssembely;
    String vehicleWheels;
    String engineType;
    String engineCapicity;
    String vehicleMileage;
    String description;
    String userId;
    String currentLat;
    String currentLng;
    String accessories;
    String image1 = "empty";
    String image2 = "empty";
    String image3 = "empty";
    String image4 = "empty";
    String image5 = "empty";
    String image6 = "empty";
    String image7 = "empty";
    String image8 = "empty";
    Uri mUri1, mUri2, mUri3, mUri4, mUri5, mUri6, mUri7, mUri8;
    public static String strSeparator = "__,__";
    Map<String, RequestBody> accessoriesParts = new HashMap<>();
    Map<String, RequestBody> seizeParts = new HashMap<>();
    List<MultipartBody.Part> fileslist = new ArrayList<>();
    FloatingActionButton sync_btn;
    ImageView no_record_layout;
    VehicleHistoryAdapter vehicleHistoryAdapter;
    RelativeLayout veh_history;
    AwesomeProgressDialog mAwesomeProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_history);
        setUI();

    }

    private void setUI() {
        //binding view
        veh_history = findViewById(R.id.veh_history);
        mRecyclerView = findViewById(R.id.hisoty_list);
        sync_btn = findViewById(R.id.sync_btn);
        //progress dialog
        mAwesomeProgressDialog = new AwesomeProgressDialog(this);
       // swipe = findViewById(R.id.swipe);
        no_record_layout = findViewById(R.id.seize_history_img);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
      //  swipe.setRefreshing(true);
       // swipe.setOnRefreshListener(null);
        loadData();
        sync_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: "+list.size() );
                if (!isNetworkAvailable()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new AwesomeErrorDialog(VehicleHistoryActivity.this)
                                        .setTitle("No Internet Connection")
                                        .setMessage("Please check your wifi connection")
                                        .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                        .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                        .setCancelable(false)
                                        .setButtonText("check")
                                        .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                        .setButtonText(getString(R.string.dialog_ok_button))
                                        .setErrorButtonClick(new Closure() {
                                            @Override
                                            public void exec() {
                                                // click

                                            }
                                        })
                                        .show();
                            }
                        });


                    }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAwesomeProgressDialog.setTitle("Syncing Data")
                                    .setMessage("Please Wait")
                                    .setColoredCircle(R.color.colorPrimaryDark)
                                    .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                    .setCancelable(false)
                                    .show();
                            syncData();
                        }
                    });



                    }



            }
        });

    }
    private void loadData() {
                seizeHistoryList.clear();
                list = mDbHelper.getSizeData();
        Log.e(TAG, "list size: "+list.size());
                if (list.size() > 0){
            Log.e(TAG, "list size: "+list.size());
            no_record_layout.setVisibility(View.GONE);
            sync_btn.setVisibility(View.VISIBLE);
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                seizeID = map.get(SEIZE_ID);
                formNo = map.get(SEIZE_FORM_NO);
                seizeCatId = map.get(SEIZE_CAT_ID);
                seizeCatName = map.get(SEIZE_CAT_NAME);
                seizeDistrict = map.get(SEIZE_DISTRICT_ID);
                driverName = map.get(SEIZE_DRIVER_NAME);
                driverCnic = map.get(SEIZE_DRIVER_CNIC);
                driverMobileNo = map.get(SEIZE_DRIVER_MOB_NO);
                driverAddress = map.get(SEIZE_DRIVER_ADDRESS);
                vehOwnerName = map.get(SEIZE_VEHICLE_OWNER_NAME);
                vehOwnerCnic = map.get(SEIZE_VEHICLE_OWNER_CNIC);
                vehOwnerMobileNo = map.get(SEIZE_VEHICLE_OWNER_MOB_NO);
                squadNo = map.get(SEIZE_SQUAD_NO);
                chasisNo = map.get(SEIZE_CHASIS_NO);
                engineNo = map.get(SEIZE_ENGINE_NO);
                vehRegNo = map.get(SEIZE_VEHICLE_REG_NO);
                seizeDate = map.get(SEIZE_DATE);
                seizeTime = map.get(SEIZE_TIME);
                makeId = map.get(SEIZE_MAKE_ID);
                modelId = map.get(SEIZE_MODEL_ID);
                modelYear = map.get(SEIZE_MODEL_YEAR);
                vehicleType = map.get(SEIZE_VEHICLE_TYPE);
                bodyBuild = map.get(SEIZE_BODY_BUILD);
                vehicleColor = map.get(SEIZE_VEHICLE_COLOR);
                vehicleTransmission = map.get(SEIZE_VEHICLE_TRANSMISSION);
                vehicleAssembely = map.get(SEIZE_VEHICLE_ASSEMBELY);
                vehicleWheels = map.get(SEIZE_VEHICLE_WEEHLES);
                engineType = map.get(SEIZE_ENGINE_TYPE);
                engineCapicity = map.get(SEIZE_ENGINE_CAPICITY);
                vehicleMileage = map.get(SEIZE_VEHICLE_MILEAGE);
                description = map.get(SEIZE_DESCRIPTION);
                userId = map.get(SEIZE_USER_ID);
                currentLat = map.get(SEIZE_CURRENT_LATITUDE);
                currentLng = map.get(SEIZE_CURRENT_LONGITUDE);
                accessories = map.get(SEIZE_ACCESSORIES);
                image1 = map.get(SEIZE_IMAGE1);
                image2 = map.get(SEIZE_IMAGE2);
                image3 = map.get(SEIZE_IMAGE3);
                image4 = map.get(SEIZE_IMAGE4);
                image5 = map.get(SEIZE_IMAGE5);
                image6 = map.get(SEIZE_IMAGE6);
                image7 = map.get(SEIZE_IMAGE7);
                image8 = map.get(SEIZE_IMAGE8);
                Log.e(TAG, "image1: "+ image1);
                Log.e(TAG, "image2: "+ image2);
                Log.e(TAG, "image3: "+ image3);
                Log.e(TAG, "image4: "+ image4);
                Log.e(TAG, "image5: "+ image5);
                Log.e(TAG, "image6: "+ image6);
                Log.e(TAG, "image7: "+ image7);
                Log.e(TAG, "image8: "+ image8);
                if (!image1.equals("empty")){
                    Log.e(TAG, "path: "+image1);
                    mUri1 = Uri.parse(image1);
                    Log.e(TAG, "mUri1: "+ mUri1);
                    File file1 = FileUtils.getFile(VehicleHistoryActivity.this, mUri1);
                    //File file1 = new File(mUri1.toString());
                    fileslist.add(prepareFilePart("files[]", file1));
                } if (!image2.equals("empty")){
                    Log.e(TAG, "path1: "+image2);
                    mUri2 = Uri.parse(image2);

                    File file2 = FileUtils.getFile(VehicleHistoryActivity.this, mUri2);
                   fileslist.add(prepareFilePart("files[]", file2));
                } if (!image3.equals("empty")){
                    Log.e(TAG, "path2: "+image3);
                    mUri3 = Uri.parse(image3);
                    File file3 = FileUtils.getFile(VehicleHistoryActivity.this, mUri3);
                   // File file3 = new File(mUri3.toString());

                    fileslist.add(prepareFilePart("files[]", file3));
                } if (!image4.equals("empty")){
                    Log.e(TAG, "path4: "+image4);
                    mUri4 = Uri.parse(image4);
                    File file4 = FileUtils.getFile(VehicleHistoryActivity.this, mUri4);
                    //File file4 = new File(mUri4.toString());

                    fileslist.add(prepareFilePart("files[]", file4));
                } if (!image5.equals("empty")){
                    Log.e(TAG, "path5: "+image5);
                    mUri5 = Uri.parse(image5);
                    File file5 = FileUtils.getFile(VehicleHistoryActivity.this, mUri5);
                    //File file5 = new File(mUri5.toString());

                    fileslist.add(prepareFilePart("files[]", file5));
                } if (!image6.equals("empty")){
                    Log.e(TAG, "path6: "+image6);
                    mUri6 = Uri.parse(image6);
                    File file6 = FileUtils.getFile(VehicleHistoryActivity.this, mUri6);
                    //File file6 = new File(mUri6.toString());

                    fileslist.add(prepareFilePart("files[]", file6));
                } if (!image7.equals("empty")){
                    Log.e(TAG, "path7: "+image7);
                    mUri7 = Uri.parse(image7);
                    File file7 = FileUtils.getFile(VehicleHistoryActivity.this, mUri7);
                    //File file7 = new File(mUri7.toString());

                    fileslist.add(prepareFilePart("files[]", file7));
                } if (!image8.equals("empty")){
                    Log.e(TAG, "path8: "+image8);
                    mUri8 = Uri.parse(image8);
                    File file8 = FileUtils.getFile(VehicleHistoryActivity.this, mUri8);
                   // File file8 = new File(mUri8.toString());

                    fileslist.add(prepareFilePart("files[]", file8));
                }
                mSeizedVechile = new SeizedVechile(seizeDistrict, seizeCatName, formNo, seizeDate, seizeTime);
                seizeHistoryList.add(mSeizedVechile);
                Log.e(TAG, "accessories: "+ accessories);
                accessorieslist = convertStringToArray(accessories);
                seizelist = convertStringToArray(seizeCatId);
                //fileslist = convertStringToArray2(images);
                Log.e(TAG, "accessorieslist: "+ accessorieslist);
                Log.e(TAG, "accessorieslist size: "+ accessorieslist.size());
                for (int i1 = 0; i1 < accessorieslist.size(); i1++) {
                    accessoriesParts.put("access[]"+i1, createPartFromString(accessorieslist.get(i1)));
                }
                for (int i1 = 0; i1 < seizelist.size(); i1++) {
                    seizeParts.put("seizecat[]"+i1, createPartFromString(seizelist.get(i1)));
                }

                postMap.put(SEIZE_FORM_NO, createPartFromString(formNo));
                Log.e(TAG, "formNo: "+formNo );
                postMap.put(SEIZE_DISTRICT_ID, createPartFromString(seizeDistrict));
                Log.e(TAG, "seizeDistrict: "+seizeDistrict );
                postMap.put(SEIZE_DRIVER_NAME, createPartFromString(driverName));
                Log.e(TAG, "driverName: "+driverName );
                postMap.put(SEIZE_DRIVER_CNIC, createPartFromString(driverCnic));
                Log.e(TAG, "driverCnic: "+driverCnic );
                postMap.put(SEIZE_DRIVER_MOB_NO, createPartFromString(driverMobileNo));
                Log.e(TAG, "SEIZE_DRIVER_MOB_NO: "+driverMobileNo );
                postMap.put(SEIZE_DRIVER_ADDRESS, createPartFromString("kllk"));
                Log.e(TAG, "vehDriverAddress: "+"kllk" );
                postMap.put(SEIZE_VEHICLE_OWNER_NAME, createPartFromString(vehOwnerName));
                Log.e(TAG, "vehOwnerName: "+vehOwnerName );
                postMap.put(SEIZE_VEHICLE_OWNER_CNIC, createPartFromString(vehOwnerCnic));
                Log.e(TAG, "vehOwnerCnic: "+vehOwnerCnic );
                postMap.put(SEIZE_VEHICLE_OWNER_MOB_NO, createPartFromString(vehOwnerMobileNo));
                Log.e(TAG, "vehOwnerMobileNo: "+vehOwnerMobileNo );
                postMap.put(SEIZE_SQUAD_NO, createPartFromString(squadNo));


                postMap.put(SEIZE_CHASIS_NO, createPartFromString(chasisNo));
                Log.e(TAG, "chasisNo: "+chasisNo );
                postMap.put("registration_district", createPartFromString("2"));

                postMap.put(SEIZE_ENGINE_NO, createPartFromString(engineNo));
                Log.e(TAG, "engineNo: "+engineNo );
                postMap.put(SEIZE_VEHICLE_REG_NO, createPartFromString(vehRegNo));
                Log.e(TAG, "vehRegNo: "+vehRegNo );
                postMap.put(SEIZE_DATE, createPartFromString(seizeDate));
                Log.e(TAG, "seizeDate: "+seizeDate );
                postMap.put(SEIZE_TIME, createPartFromString(seizeTime));
                Log.e(TAG, "seizeTime: "+seizeTime );
                postMap.put(SEIZE_MAKE_ID, createPartFromString(makeId));
                Log.e(TAG, "makeId: "+makeId );
                postMap.put(SEIZE_MODEL_ID, createPartFromString(modelId));
                Log.e(TAG, "modelId: "+modelId );
                postMap.put(SEIZE_MODEL_YEAR, createPartFromString(modelYear));
                Log.e(TAG, "modelYear: "+modelYear );
                postMap.put(SEIZE_VEHICLE_TYPE, createPartFromString(vehicleType));
                Log.e(TAG, "vehicleType: "+vehicleType );
                postMap.put(SEIZE_BODY_BUILD, createPartFromString(bodyBuild));
                Log.e(TAG, "bodyBuild: "+bodyBuild );
                postMap.put(SEIZE_VEHICLE_COLOR, createPartFromString(vehicleColor));
                Log.e(TAG, "vehicleColor: "+vehicleColor );
                postMap.put(SEIZE_VEHICLE_TRANSMISSION, createPartFromString(vehicleTransmission));
                Log.e(TAG, "vehicleTransimission: "+engineNo );
                postMap.put(SEIZE_VEHICLE_ASSEMBELY, createPartFromString(vehicleAssembely));
                Log.e(TAG, "vehicleAssembely: "+vehicleAssembely );
                postMap.put(SEIZE_VEHICLE_WEEHLES, createPartFromString(vehicleWheels));
                Log.e(TAG, "vehicleWheels: "+vehicleWheels );
                postMap.put(SEIZE_ENGINE_TYPE, createPartFromString(engineType));
                Log.e(TAG, "engineType: "+engineType );
                postMap.put(SEIZE_ENGINE_CAPICITY, createPartFromString(engineCapicity));
                Log.e(TAG, "engineCapicity: "+engineCapicity );
                postMap.put(SEIZE_VEHICLE_MILEAGE, createPartFromString(vehicleMileage));
                Log.e(TAG, "vehicleMileage: "+vehicleMileage );
                postMap.put(SEIZE_DESCRIPTION, createPartFromString(description));
                Log.e(TAG, "description: "+description );
                postMap.put(SEIZE_USER_ID, createPartFromString(userId));
                Log.e(TAG, "userId: "+userId );
               postMap.put(SEIZE_CURRENT_LATITUDE, createPartFromString(currentLat));
                Log.e(TAG, "currentLat: "+currentLat );
                postMap.put(SEIZE_CURRENT_LONGITUDE, createPartFromString(currentLng));
                Log.e(TAG, "currentLng: "+currentLng);

            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    vehicleHistoryAdapter = new VehicleHistoryAdapter(VehicleHistoryActivity.this, seizeHistoryList);
                    mRecyclerView.setAdapter(vehicleHistoryAdapter);
                    vehicleHistoryAdapter.notifyDataSetChanged();
                    //swipe.setRefreshing(false);
                }
            });
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seizeHistoryList.clear();
                    no_record_layout.setVisibility(View.VISIBLE);
                    sync_btn.setVisibility(View.GONE);
                    }
            });
        }
        }
    private void syncData() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        SeizedVehicleApi seizedVehicleApi = retrofit.create(SeizedVehicleApi.class);
        Call<List<SeizePostData>> call = seizedVehicleApi.uploadFiles(postMap, seizeParts, accessoriesParts, fileslist);
        call.enqueue(new Callback<List<SeizePostData>>() {
            @Override
            public void onResponse(Call<List<SeizePostData>> call, Response<List<SeizePostData>> response) {
                Log.e(TAG, "onResponse: "+ response);
                Log.e(TAG, "sucess: "+ response.body().get(0).getSuccess());
                if (response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAwesomeProgressDialog.hide();
                            Snackbar snackbar = Snackbar
                                    .make(veh_history, "Data Sync Successfully", Snackbar.LENGTH_SHORT);
                            View view = snackbar.getView();
                            view.setBackgroundColor(getResources().getColor(R.color.dialogSuccessBackgroundColor));
                            snackbar.show();
                            mDbHelper.deleteSeizeData(seizeID);
                            vehicleHistoryAdapter.notifyDataSetChanged();
                            loadData();



                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAwesomeProgressDialog.hide();
                            Snackbar snackbar = Snackbar
                                    .make(veh_history, "Failed to syncing data", Snackbar.LENGTH_SHORT);
                            View view = snackbar.getView();
                            view.setBackgroundColor(getResources().getColor(R.color.dialogErrorBackgroundColor));
                            snackbar.show();

                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<SeizePostData>> call, Throwable t) {
                Log.e("response data", t.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAwesomeProgressDialog.hide();
                        Snackbar snackbar = Snackbar
                                .make(veh_history, "Failed to syncing data", Snackbar.LENGTH_SHORT);
                        View view = snackbar.getView();
                        view.setBackgroundColor(getResources().getColor(R.color.dialogErrorBackgroundColor));
                        snackbar.show();

                    }
                });


            }
        });



    }
    @NonNull
    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }

    public static List<String> convertStringToArray(String str){
        return Arrays.asList(str.split(strSeparator));
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, File file) {
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(FileUtils.getMimeType(file)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected())
        {
            isAvailable = true;

        }
        return isAvailable;
    }
}

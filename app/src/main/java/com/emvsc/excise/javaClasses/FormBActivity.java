package com.emvsc.excise.javaClasses;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.CheckedAdapter;
import com.emvsc.excise.adapterClasses.UncheckedAdapter;
import com.emvsc.excise.interfaceClasses.FormbAccessoriesApi;
import com.emvsc.excise.interfaceClasses.SeizedVehicleApi;
import com.emvsc.excise.modelClasses.FormBAccessories;
import com.emvsc.excise.modelClasses.FormbPost;
import com.emvsc.excise.utilClasses.Config;
import com.emvsc.excise.utilClasses.NonScrollListView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import static com.emvsc.excise.utilClasses.Prefences.USER_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_PREF;

public class FormBActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private static final int REQUEST_TAKE_PHOTO1 = 4001;
    private static final int REQUEST_TAKE_PHOTO2 = 4002;
    private static final int REQUEST_TAKE_PHOTO3 = 4003;
    private static final int REQUEST_TAKE_PHOTO4 = 4004;
    private static final int REQUEST_TAKE_PHOTO5 = 4005;
    private static final int REQUEST_TAKE_PHOTO6 = 4006;
    private static final int REQUEST_TAKE_PHOTO7 = 4007;
    private static final int REQUEST_TAKE_PHOTO8 = 4008;
    private static final int MY_CAMERA_PERMISSION_CODE1 = 5001;
    private static final int MY_CAMERA_PERMISSION_CODE2 = 5002;
    private static final int MY_CAMERA_PERMISSION_CODE3 = 5003;
    private static final int MY_CAMERA_PERMISSION_CODE4 = 5004;
    private static final int MY_CAMERA_PERMISSION_CODE5 = 5005;
    private static final int MY_CAMERA_PERMISSION_CODE6 = 5006;
    private static final int MY_CAMERA_PERMISSION_CODE7 = 5007;
    private static final int MY_CAMERA_PERMISSION_CODE8 = 5008;
    private static final int MY_LOCATION_PERMISSION_CODE = 6001;
    private EditText seize_formb_no_label, seize_formb_no;
    private EditText formb_seize_date, formb_seize_time;
    private EditText formb_location;
    private EditText formb_description_label, formb_description_et;
    private ImageView formb_imageview1, formb_imageview2, formb_imageview3, formb_imageview4,
            formb_imageview5, formb_imageview6, formb_imageview7, formb_imageview8;
    private LinearLayout formb_image1_delete, formb_image2_delete, formb_image3_delete, formb_image4_delete,
            formb_image5_delete, formb_image6_delete, formb_image7_delete, formb_image8_delete;
    private ImageView formb_attachement_btn1, formb_attachement_btn2, formb_attachement_btn3, formb_attachement_btn4,
            formb_attachement_btn5, formb_attachement_btn6, formb_attachement_btn7, formb_attachement_btn8;
    private NonScrollListView checkedList, uncheckList;
    private Button formb_submit_btn;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;

    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    Double currentLatitude = 0.0;
    Double currentLongitude = 0.0;
    Map<String, RequestBody> dataMap = new HashMap<>();
    File file1, file2, file3, file4, file5, file6, file7, file8;
    Uri uri1, uri2, uri3, uri4, uri5, uri6, uri7, uri8;
    List<MultipartBody.Part> fileParts = new ArrayList<>();
    MultipartBody.Part part1, part2, part3, part4, part5, part6, part7, part8;
    String userID;
    private SharedPreferences userSharedPreferences;
    AwesomeProgressDialog mAwesomeProgressDialog;
    String veh_id;
    TextView formb_expand;
    UncheckedAdapter uncheckedAdapter;
    RelativeLayout formb_next_btn_1, formb_back_btn_1;
    LinearLayout formb_layout1,formb_layout2;
    //Animation
    Animation LeftSwipe;
    Animation RightSwipe;
    ScrollView formb_scroll;

    StateProgressBar formb_stat_progress;
    ProgressBar formb_access_progress;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_b);
        locationSetting();
        setUi();
        loadFormbAccessories();
    }
    private void setUi() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Form B");
        Intent intent = getIntent();
        veh_id = intent.getStringExtra("vehicle_id");
        userSharedPreferences = getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        userID = userSharedPreferences.getString(USER_ID, "No Data");
        formb_access_progress = findViewById(R.id.formb_access_progress);
        formb_stat_progress = findViewById(R.id.formb_stat_progress);
        formb_scroll = findViewById(R.id.formb_scroll);
        formb_layout1 = findViewById(R.id.formb_layout1);
        formb_layout2 = findViewById(R.id.formb_layout2);
        seize_formb_no_label = findViewById(R.id.seize_formb_no_label);
        seize_formb_no = findViewById(R.id.seize_formb_no);
        seize_formb_no.addTextChangedListener(new GenericTextWatcher(seize_formb_no));

        formb_seize_date = findViewById(R.id.formb_seize_date);
        formb_seize_time = findViewById(R.id.formb_seize_time);

        formb_location = findViewById(R.id.formb_location);
       // formb_expandableLayout = findViewById(R.id.formb_expandableLayout);
        formb_expand = findViewById(R.id.formb_expand);
        formb_expand.setOnClickListener(this);

        formb_description_label = findViewById(R.id.formb_description_label);
        formb_description_et = findViewById(R.id.formb_description_et);

        formb_attachement_btn1 = findViewById(R.id.formb_attachement_btn1);
        formb_attachement_btn1.setOnClickListener(this);
        formb_attachement_btn2 = findViewById(R.id.formb_attachement_btn2);
        formb_attachement_btn2.setOnClickListener(this);
        formb_attachement_btn3 = findViewById(R.id.formb_attachement_btn3);
        formb_attachement_btn3.setOnClickListener(this);
        formb_attachement_btn4 = findViewById(R.id.formb_attachement_btn4);
        formb_attachement_btn4.setOnClickListener(this);
        formb_attachement_btn5 = findViewById(R.id.formb_attachement_btn5);
        formb_attachement_btn5.setOnClickListener(this);
        formb_attachement_btn6 = findViewById(R.id.formb_attachement_btn6);
        formb_attachement_btn6.setOnClickListener(this);
        formb_attachement_btn7 = findViewById(R.id.formb_attachement_btn7);
        formb_attachement_btn7.setOnClickListener(this);
        formb_attachement_btn8 = findViewById(R.id.formb_attachement_btn8);
        formb_attachement_btn8.setOnClickListener(this);

        formb_imageview1 = findViewById(R.id.formb_imageview1);
        formb_imageview1.setOnClickListener(this);
        formb_imageview2 = findViewById(R.id.formb_imageview2);
        formb_imageview2.setOnClickListener(this);
        formb_imageview3 = findViewById(R.id.formb_imageview3);
        formb_imageview3.setOnClickListener(this);
        formb_imageview4 = findViewById(R.id.formb_imageview4);
        formb_imageview4.setOnClickListener(this);
        formb_imageview5 = findViewById(R.id.formb_imageview5);
        formb_imageview5.setOnClickListener(this);
        formb_imageview6 = findViewById(R.id.formb_imageview6);
        formb_imageview6.setOnClickListener(this);
        formb_imageview7 = findViewById(R.id.formb_imageview7);
        formb_imageview7.setOnClickListener(this);
        formb_imageview8 = findViewById(R.id.formb_imageview8);
        formb_imageview8.setOnClickListener(this);

        formb_image1_delete = findViewById(R.id.formb_image1_delete);
        formb_image1_delete.setOnClickListener(this);
        formb_image2_delete = findViewById(R.id.formb_image2_delete);
        formb_image2_delete.setOnClickListener(this);
        formb_image3_delete = findViewById(R.id.formb_image3_delete);
        formb_image3_delete.setOnClickListener(this);
        formb_image4_delete = findViewById(R.id.formb_image4_delete);
        formb_image4_delete.setOnClickListener(this);
        formb_image5_delete = findViewById(R.id.formb_image5_delete);
        formb_image5_delete.setOnClickListener(this);
        formb_image6_delete = findViewById(R.id.formb_image6_delete);
        formb_image6_delete.setOnClickListener(this);
        formb_image7_delete = findViewById(R.id.formb_image7_delete);
        formb_image7_delete.setOnClickListener(this);
        formb_image8_delete = findViewById(R.id.formb_image8_delete);
        formb_image8_delete.setOnClickListener(this);
        formb_next_btn_1 = findViewById(R.id.formb_next_btn_1);
        formb_next_btn_1.setOnClickListener(this);
        formb_back_btn_1 = findViewById(R.id.formb_back_btn_1);
        formb_back_btn_1.setOnClickListener(this);

        checkedList = findViewById(R.id.checked_list);
        uncheckList = findViewById(R.id.unchecked_list);

        formb_submit_btn = findViewById(R.id.formb_submit_btn);
        formb_submit_btn.setOnClickListener(this);

        mAwesomeProgressDialog = new AwesomeProgressDialog(this);

        //for animcation
        LeftSwipe = AnimationUtils.loadAnimation(this, R.anim.left_slide);
        RightSwipe = AnimationUtils.loadAnimation(this, R.anim.right_slide);

        setCurentTime();

    }
    private void loadFormbAccessories() {
        formb_access_progress.setVisibility(View.VISIBLE);
        HashMap<String, String> map = new HashMap<>();
        map.put("vehicle_id", veh_id);
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
        FormbAccessoriesApi formbAccessoriesApi = retrofit.create(FormbAccessoriesApi.class);
        Call<FormBAccessories> call = formbAccessoriesApi.retriveList(map);
        call.enqueue(new Callback<FormBAccessories>() {
            @Override
            public void onResponse(Call<FormBAccessories> call, Response<FormBAccessories> response) {
                if (response.isSuccessful()){
                    int success = response.body().getSuccess();
                    Log.e("success", "onResponse: "+success );
                    Log.e("check response: ", response.body().getAccessoriesChecked().toString());
                    Log.e("uncheck response: ", response.body().getAccessoriesUnchecked().toString());
                    final List<FormBAccessories.AccessoriesChecked> checkList = response.body().getAccessoriesChecked();
                    final List<FormBAccessories.AccessoriesUnchecked> uncheck_List = response.body().getAccessoriesUnchecked();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            checkedList.setVisibility(View.VISIBLE);
                            uncheckList.setVisibility(View.VISIBLE);
                            CheckedAdapter checkedAdapter = new CheckedAdapter(FormBActivity.this, checkList);
                            checkedList.setAdapter(checkedAdapter);
                            checkedAdapter.notifyDataSetChanged();
                            uncheckedAdapter = new UncheckedAdapter(FormBActivity.this, uncheck_List);
                            uncheckList.setAdapter(uncheckedAdapter);
                            uncheckedAdapter.notifyDataSetChanged();
                            formb_access_progress.setVisibility(View.GONE);

                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<FormBAccessories> call, final Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("onFailure", t.toString() );
                        formb_access_progress.setVisibility(View.GONE);
                    }
                });


            }
        });
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.formb_next_btn_1:
                if (TextUtils.isEmpty(seize_formb_no.getText().toString())){

                    seize_formb_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    seize_formb_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                    seize_formb_no.setError("Enter form b no");
                }else {

                    formb_stat_progress.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                    formb_layout1.setVisibility(View.GONE);
                    formb_layout2.setVisibility(View.VISIBLE);
                    formb_layout2.startAnimation(LeftSwipe);
                    formb_scroll.scrollTo(0,0);
                }


                break;

                case R.id.formb_back_btn_1:
                    formb_stat_progress.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                    formb_layout1.setVisibility(View.VISIBLE);
                formb_layout2.setVisibility(View.GONE);
                    formb_layout1.startAnimation(RightSwipe);
                    formb_scroll.scrollTo(0,0);
                break;
            case R.id.formb_attachement_btn1:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE1);
                } else {
                    dispatchTakePictureIntent1();

                }
                break;
            case R.id.formb_attachement_btn2:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE2);
                } else {
                    dispatchTakePictureIntent2();

                }
                break;
            case R.id.formb_attachement_btn3:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE3);
                } else {
                    dispatchTakePictureIntent3();

                }
                break;
            case R.id.formb_attachement_btn4:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE4);
                } else {
                    dispatchTakePictureIntent4();

                }
                break;
            case R.id.formb_attachement_btn5:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE5);
                } else {
                    dispatchTakePictureIntent5();

                }
                break;
            case R.id.formb_attachement_btn6:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE6);
                } else {
                    dispatchTakePictureIntent6();

                }                break;
            case R.id.formb_attachement_btn7:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE7);
                } else {
                    dispatchTakePictureIntent7();

                }                break;
            case R.id.formb_attachement_btn8:
                if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_CAMERA_PERMISSION_CODE8);
                } else {
                    dispatchTakePictureIntent8();

                }
                break;

            case R.id.formb_image1_delete:
                removeImage(1);
                break;
            case R.id.formb_image2_delete:
                removeImage(2);
                break;
            case R.id.formb_image3_delete:
                removeImage(3);
                break;
            case R.id.formb_image4_delete:
                removeImage(4);
                break;
            case R.id.formb_image5_delete:
                removeImage(5);
                break;
            case R.id.formb_image6_delete:
                removeImage(6);
                break;
            case R.id.formb_image7_delete:
                removeImage(7);
                break;
            case R.id.formb_image8_delete:
                removeImage(8);
                break;

            case R.id.formb_imageview1:
                showImagePreview(uri1);
                break;
            case R.id.formb_imageview2:
                showImagePreview(uri2);
                break;
            case R.id.formb_imageview3:
                showImagePreview(uri3);
                break;
            case R.id.formb_imageview4:
                showImagePreview(uri4);
                break;
            case R.id.formb_imageview5:
                showImagePreview(uri5);
                break;
            case R.id.formb_imageview6:
                showImagePreview(uri6);
                break;
            case R.id.formb_imageview7:
                showImagePreview(uri7);
                break;
            case R.id.formb_imageview8:
                showImagePreview(uri8);
                break;
            case R.id.formb_submit_btn:
                String formb = seize_formb_no.getText().toString();
                String description = formb_description_et.getText().toString();
                Log.e("formb", formb );
                Log.e("description", description );
                if (TextUtils.isEmpty(formb)){
                    seize_formb_no_label.setError(null);
                    seize_formb_no_label.requestFocus();
                    seize_formb_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                    seize_formb_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                }else if (fileParts.size()<7){

                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.formb_layout), "Capture atleast 7 pictures", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else {
                    dataMap.put("form_bno", createPartFromString(formb));
                    dataMap.put("vechicle_id", createPartFromString(veh_id));
                        dataMap.put("description", createPartFromString(description));

                    Log.e("TAG", "PostData: "+dataMap );
                    Log.e("TAG", "PostImg: "+fileParts.toString() );
                    Log.e("TAG", "PostImgsize: "+fileParts.size() );
                    if (!userID.equals("No Data")) {
                        dataMap.put("user_id", createPartFromString(userID));
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(FormBActivity.this)
                                    .setMessage("Are you sure to submit form")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mAwesomeProgressDialog.setTitle("Uploading Data")
                                                    .setMessage("Please Wait")
                                                    .setColoredCircle(R.color.colorPrimaryDark)
                                                    .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                                    .setCancelable(false)
                                                    .show();
                                            postData();

                                        }

                                    })
                                    .setNegativeButton("No", null)
                                    .show();

                        }
                    });
                }
                break;

        }
    }

    private void postData() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(2, TimeUnit.MINUTES)
                            .readTimeout(2, TimeUnit.MINUTES)
                            .writeTimeout(2, TimeUnit.MINUTES)
                            .build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Config.BaseUrl)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    SeizedVehicleApi seizedVehicleApi = retrofit.create(SeizedVehicleApi.class);
                    ArrayList <String> accesslist = uncheckedAdapter.getId();
                    Log.e("formb accesslist", "list size: "+accesslist.size());
                    Log.e("data", accesslist.toString());
                    Map<String, RequestBody> accessoriesParts = new HashMap<>();
                    for(int i =0 ;i<accesslist.size();i++) {
                        accessoriesParts.put("formbaccess[]"+i, createPartFromString(accesslist.get(i)));
                    }
                    Call<List<FormbPost>> call = seizedVehicleApi.formbData(dataMap, accessoriesParts, fileParts);
                    call.enqueue(new Callback<List<FormbPost>>() {
                        @Override
                        public void onResponse(Call<List<FormbPost>> call, Response<List<FormbPost>> response) {
                            Log.e("response", response.toString());
                            int success = response.body().get(0).getSuccess();
                            if (response.isSuccessful()){
                                if (success == 1){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mAwesomeProgressDialog.hide();
                                            new AwesomeSuccessDialog(FormBActivity.this)
                                                    .setTitle("Success")
                                                    .setMessage("You have submitted the form successfully")
                                                    .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                                                    .setDialogIconAndColor(R.drawable.ic_success, R.color.white)
                                                    .setCancelable(false)
                                                    .setPositiveButtonText(getString(R.string.dialog_ok_button))
                                                    .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                                                    .setPositiveButtonTextColor(R.color.white)
                                                    .setPositiveButtonClick(new Closure() {
                                                        @Override
                                                        public void exec() {
                                                            //click
                                                            Intent intent = new Intent(FormBActivity.this,WhmActivity.class);
                                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });

                                }else if (success == 0){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mAwesomeProgressDialog.hide();
                                            new AwesomeErrorDialog(FormBActivity.this)
                                                    .setTitle("WhOops")
                                                    .setMessage("Something went wrong please try again!")
                                                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                                    .setCancelable(false).setButtonText(getString(R.string.dialog_ok_button))
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
                                }

                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAwesomeProgressDialog.hide();
                                        new AwesomeErrorDialog(FormBActivity.this)
                                                .setTitle("WhOops")
                                                .setMessage("Something went wrong please try again!")
                                                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                                .setCancelable(false).setButtonText(getString(R.string.dialog_ok_button))
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
                            }
                        }

                        @Override
                        public void onFailure(Call<List<FormbPost>> call, Throwable t) {
                            Log.e("response data", t.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mAwesomeProgressDialog.hide();
                                    new AwesomeErrorDialog(FormBActivity.this)
                                            .setTitle("WhOops")
                                            .setMessage("Something went wrong please try again!")
                                            .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                            .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                            .setCancelable(false).setButtonText(getString(R.string.dialog_ok_button))
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


                        }
                    });

                }
            });





    }

    private void setCurentTime() {
        Date currentTimeStamp = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
        String currentDate = sdf.format(currentTimeStamp);
        String currentTime = sdf2.format(currentTimeStamp);
        formb_seize_date.setText(currentDate);
        formb_seize_time.setText(currentTime);
        dataMap.put("date", createPartFromString(currentDate));
        dataMap.put("time", createPartFromString(currentTime));
    }

    private void locationSetting() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(FormBActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FormBActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_PERMISSION_CODE);
        } else {
            checkLocation();
        }
    }

    private boolean checkLocation() {
        if (!isLocationEnabled()) {
            showAlert();
        }

        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocation();
    }

    private void showAlert() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enable Location")
                    .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                            "use this app")
                    .setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                            EnableGPSAutoMatically();
                        }
                    });

            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }

    }

    private void EnableGPSAutoMatically() {
        GoogleApiClient googleApiClient = null;
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            // **************************
            builder.setAlwaysShow(true); // this is the key ingredient
            // **************************

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result
                            .getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            //toast("Success");
                            // All location settings are satisfied. The client can
                            // initialize location
                            // requests here.
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            //toast("GPS is not on");
                            // Location settings are not satisfied. But could be
                            // fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling
                                // startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(FormBActivity.this, 1000);

                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //toast("Setting change not allowed");
                            // Location settings are not satisfied. However, we have
                            // no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }

    private void dispatchTakePictureIntent1() {
            // call implicit intent to launch camera
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(getPackageManager()) != null){
                //initi file variblae with null
                file1 = null;
                try{
                    file1 = createImageFile();
                }catch (IOException e){
                    Log.e("tag", "IOException: "+e );
                }
                // Continue only if the File was successfully created
                if (file1 != null) {
                    uri1 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file1);
                    // by this point we have the camera photo on disk
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO1);
                }
            }

        }

    private void dispatchTakePictureIntent2() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file2 = null;
            try{
                file2 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file2 != null) {
                uri2 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file2);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri2);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO2);
            }
        }

    }

    private void dispatchTakePictureIntent3() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file3 = null;
            try{
                file3 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file3 != null) {
                uri3 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file3);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri3);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO3);
            }
        }

    }

    private void dispatchTakePictureIntent4() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file4 = null;
            try{
                file4 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file4 != null) {
                uri4 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file4);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri4);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO4);
            }
        }

    }

    private void dispatchTakePictureIntent5() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file5 = null;
            try{
                file5 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file5 != null) {
                uri5 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file5);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri5);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO5);
            }
        }

    }

    private void dispatchTakePictureIntent6() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file6 = null;
            try{
                file6 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file6 != null) {
                uri6 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file6);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri6);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO6);
            }
        }

    }

    private void dispatchTakePictureIntent7() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file7 = null;
            try{
                file7 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file7 != null) {
                uri7 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file7);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri7);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO7);
            }
        }

    }

    private void dispatchTakePictureIntent8() {
        // call implicit intent to launch camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            //initi file variblae with null
            file8 = null;
            try{
                file8 = createImageFile();
            }catch (IOException e){
                Log.e("tag", "IOException: "+e );
            }
            // Continue only if the File was successfully created
            if (file8 != null) {
                uri8 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file8);
                // by this point we have the camera photo on disk
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri8);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO8);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            switch (requestCode){
            case REQUEST_TAKE_PHOTO1:
                if(uri1 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri1, 8);
                        file1 = createImageFile();
                        uri1 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file1);
                        file1.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file1);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview1.setVisibility(View.VISIBLE);
                        formb_imageview1.setImageBitmap(bmp);
                        formb_image1_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn1.setVisibility(View.GONE);
                        part1 = prepareFilePart("files[]", file1, uri1);
                        fileParts.add(part1);


                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO2:
                if(uri2 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri2, 8);
                        file2 = createImageFile();
                        uri2 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file2);
                        file2.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file2);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview2.setVisibility(View.VISIBLE);
                        formb_imageview2.setImageBitmap(bmp);
                        formb_image2_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn2.setVisibility(View.GONE);
                        part2 = prepareFilePart("files[]", file2, uri2);
                        fileParts.add(part2);


                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO3:
                if(uri3 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri3, 8);
                        file3 = createImageFile();
                        uri3 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file3);
                        file3.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file3);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview3.setVisibility(View.VISIBLE);
                        formb_imageview3.setImageBitmap(bmp);
                        formb_image3_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn3.setVisibility(View.GONE);
                        part3 = prepareFilePart("files[]", file3, uri3);
                        fileParts.add(part3);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO4:
                if(uri4 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri4, 8);
                        file4 = createImageFile();
                        uri4 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file4);
                        file4.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file4);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview4.setVisibility(View.VISIBLE);
                        formb_imageview4.setImageBitmap(bmp);
                        formb_image4_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn4.setVisibility(View.GONE);
                        part4 = prepareFilePart("files[]", file4, uri4);
                        fileParts.add(part4);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO5:
                if(uri5 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri5, 8);
                        file5 = createImageFile();
                        uri5 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file5);
                        file5.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file5);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview5.setVisibility(View.VISIBLE);
                        formb_imageview5.setImageBitmap(bmp);
                        formb_image5_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn5.setVisibility(View.GONE);
                        part5 = prepareFilePart("files[]", file5, uri5);
                        fileParts.add(part5);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO6:
                if(uri6 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri6, 8);
                        file6 = createImageFile();
                        uri6 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file6);
                        file6.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file6);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview6.setVisibility(View.VISIBLE);
                        formb_imageview6.setImageBitmap(bmp);
                        formb_image6_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn6.setVisibility(View.GONE);
                        part6 = prepareFilePart("files[]", file6, uri6);
                        fileParts.add(part6);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO7:
                if(uri7 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri7, 8);
                        file7 = createImageFile();
                        uri7 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file7);
                        file7.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file7);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview7.setVisibility(View.VISIBLE);
                        formb_imageview7.setImageBitmap(bmp);
                        formb_image7_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn7.setVisibility(View.GONE);
                        part7 = prepareFilePart("files[]", file7, uri7);
                        fileParts.add(part7);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

            case REQUEST_TAKE_PHOTO8:
                if(uri8 != null){
                    Bitmap bmp;
                    try {
                        bmp = getDownsampledBitmap(uri8, 8);
                        file8 = createImageFile();
                        uri8 = FileProvider.getUriForFile(this,"com.emvsc.excise.fileprovider", file8);
                        file8.createNewFile();


//Convert bitmap to byte array
                        Bitmap bitmap = bmp;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                        FileOutputStream fos = new FileOutputStream(file8);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                        formb_imageview8.setVisibility(View.VISIBLE);
                        formb_imageview8.setImageBitmap(bmp);
                        formb_image8_delete.setVisibility(View.VISIBLE);
                        formb_attachement_btn8.setVisibility(View.GONE);
                        part8 = prepareFilePart("files[]", file8, uri8);
                        fileParts.add(part8);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("IOException: ", e.toString());
                    }
                }
                break;

        }
        }catch (Exception e){

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent1();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent2();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE3) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent3();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE4) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent4();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent5();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE6) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent6();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE7) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent7();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }else if (requestCode == MY_CAMERA_PERMISSION_CODE8) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               dispatchTakePictureIntent8();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
        else if (requestCode == MY_LOCATION_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocation();
            } else {
                Toast.makeText(this, "location permission denied", Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {

            currentLatitude = mLocation.getLatitude();
            currentLongitude = mLocation.getLongitude();
            dataMap.put("lat", createPartFromString(String.valueOf(currentLatitude)));
            dataMap.put("long", createPartFromString(String.valueOf(currentLongitude)));
            //displayAddress(currentLatitude, currentLongitude);

        } else {
        }
    }

    private void displayAddress(Double currentLatitude, Double currentLongitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(currentLatitude, currentLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            if (address.equals("") || address.equals(null)){
                formb_location.setText(knownName);
            }else {
                formb_location.setText(address);
            }
        } catch (IOException e) {
            Log.e("IOException", ""+e.toString());
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("TAG", "Connection failed. Error: " + connectionResult.getErrorCode());

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onLocationChanged(Location location) {

        // You can now create a LatLng Object for use with maps
        Log.e("TAG", "longitude: " + location.getLongitude());
        Log.e("TAG", "latitude: " + location.getLatitude());

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        dataMap.put("lat", createPartFromString(String.valueOf(currentLatitude)));
        dataMap.put("long", createPartFromString(String.valueOf(currentLongitude)));
        //displayAddress(currentLatitude, currentLongitude);
    }

    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName =   timeStamp + "_";
        File storageDir1 = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir1      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
       // mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private Bitmap getDownsampledBitmap(Uri uri, int sampleSize) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options outDimens = getBitmapDimensions(uri);


            bitmap = downsampleBitmap(uri, sampleSize);

        } catch (Exception e) {
            //handle the exception(s)
        }

        return bitmap;
    }
    private BitmapFactory.Options getBitmapDimensions(Uri uri) throws FileNotFoundException, IOException {
        BitmapFactory.Options outDimens = new BitmapFactory.Options();
        outDimens.inJustDecodeBounds = true; // the decoder will return null (no bitmap)

        InputStream is= getContentResolver().openInputStream(uri);
        // if Options requested only the size will be returned
        BitmapFactory.decodeStream(is, null, outDimens);
        is.close();

        return outDimens;
    }
    private Bitmap downsampleBitmap(Uri uri, int sampleSize) throws FileNotFoundException, IOException {
        Bitmap resizedBitmap;
        BitmapFactory.Options outBitmap = new BitmapFactory.Options();
        outBitmap.inJustDecodeBounds = false; // the decoder will return a bitmap
        outBitmap.inSampleSize = sampleSize;

        InputStream is = getContentResolver().openInputStream(uri);
        resizedBitmap = BitmapFactory.decodeStream(is, null, outBitmap);
        is.close();

        return resizedBitmap;
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, File file, Uri uri) {
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(uri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private void removeImage(int id) {
        switch (id){
            case 1:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview1.setImageResource(android.R.color.transparent);
                                formb_attachement_btn1.setVisibility(View.VISIBLE);
                                formb_image1_delete.setVisibility(View.GONE);
                                formb_imageview1.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part1)
                                    {
                                        fileParts.remove(i);
                                        Log.e("Removed: ", ""+part1 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 2:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview2.setImageResource(android.R.color.transparent);
                                formb_attachement_btn2.setVisibility(View.VISIBLE);
                                formb_image2_delete.setVisibility(View.GONE);
                                formb_imageview2.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part2)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part2 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 3:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview3.setImageResource(android.R.color.transparent);
                                formb_attachement_btn3.setVisibility(View.VISIBLE);
                                formb_image3_delete.setVisibility(View.GONE);
                                formb_imageview3.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part3)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part3);
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 4:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview4.setImageResource(android.R.color.transparent);
                                formb_attachement_btn4.setVisibility(View.VISIBLE);
                                formb_image4_delete.setVisibility(View.GONE);
                                formb_imageview4.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part4)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part4 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 5:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview5.setImageResource(android.R.color.transparent);
                                formb_attachement_btn5.setVisibility(View.VISIBLE);
                                formb_image5_delete.setVisibility(View.GONE);
                                formb_imageview5.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part5)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part5 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 6:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview6.setImageResource(android.R.color.transparent);
                                formb_attachement_btn6.setVisibility(View.VISIBLE);
                                formb_image6_delete.setVisibility(View.GONE);
                                formb_imageview6.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part6)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part6);
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 7:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview7.setImageResource(android.R.color.transparent);
                                formb_attachement_btn7.setVisibility(View.VISIBLE);
                                formb_image7_delete.setVisibility(View.GONE);
                                formb_imageview7.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part7)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part7 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 8:
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to remove image")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                formb_imageview8.setImageResource(android.R.color.transparent);
                                formb_attachement_btn8.setVisibility(View.VISIBLE);
                                formb_image8_delete.setVisibility(View.GONE);
                                formb_imageview8.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part8)
                                    {
                                        fileParts.remove(i);
                                        Log.e("TAG", "Removed: "+part8 );
                                    }
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }


    }
    private void showImagePreview(final Uri uri) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog previewDilaog = new Dialog(FormBActivity.this, R.style.dialog_theme);
                previewDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                previewDilaog.setCancelable(true);
                previewDilaog.setContentView(R.layout.image_preview_dialog);
                previewDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                ImageView preview_image = previewDilaog.findViewById(R.id.preview_image);
                //Bitmap bmp = getDownsampledBitmap(uri, 0);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOException: ", e.toString() );
                }
                preview_image.setImageBitmap(bitmap);
                previewDilaog.show();
            }
        });
    }

    private class GenericTextWatcher implements TextWatcher {
        private View view;
        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch(view.getId()){
                case R.id.seize_formb_no:
                    if (seize_formb_no.getText().length()<4){
                        seize_formb_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        seize_formb_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                        seize_formb_no.setError("Enter 4 digits");
                    }else {
                        seize_formb_no_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        seize_formb_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;
            }

        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to cancel form")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to cancel form")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

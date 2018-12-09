package com.emvsc.excise.javaClasses;

import android.Manifest;
import android.app.Activity;
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
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.AssembelyAdapter;
import com.emvsc.excise.adapterClasses.BodyBuildAdapter;
import com.emvsc.excise.adapterClasses.ColorAdapter;
import com.emvsc.excise.adapterClasses.DistrictAdapter;
import com.emvsc.excise.adapterClasses.EngineTypeAdapter;
import com.emvsc.excise.adapterClasses.MakeAdapter;
import com.emvsc.excise.adapterClasses.ModelAdapter;
import com.emvsc.excise.adapterClasses.ModeyearlAdapter;
import com.emvsc.excise.adapterClasses.RegDistrictAdapter;
import com.emvsc.excise.adapterClasses.SeizeCatAdapter;
import com.emvsc.excise.adapterClasses.TrasnsmissionAdapter;
import com.emvsc.excise.adapterClasses.VehicleAccessoriesAdapter;
import com.emvsc.excise.adapterClasses.VehicleTypeAdapter;
import com.emvsc.excise.adapterClasses.WheelsAdapter;
import com.emvsc.excise.dbClasses.DbConstants;
import com.emvsc.excise.dbClasses.DbHelper;
import com.emvsc.excise.interfaceClasses.SeizedVehicleApi;
import com.emvsc.excise.modelClasses.DistrictData;
import com.emvsc.excise.modelClasses.MakeData;
import com.emvsc.excise.modelClasses.ModelData;
import com.emvsc.excise.modelClasses.RegisterNoDistricts;
import com.emvsc.excise.modelClasses.SeizePostData;
import com.emvsc.excise.utilClasses.Config;
import com.emvsc.excise.utilClasses.FileUtils;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

import id.zelory.compressor.Compressor;
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
import static com.emvsc.excise.utilClasses.Prefences.SPECIAL_SQUAD;
import static com.emvsc.excise.utilClasses.Prefences.USER_DISTRICT_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_PREF;

public class SeizeActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener{

    DbHelper mDbHelper = new DbHelper(this);

    private static final String TAG = "SeizeActivity";

    private static final int REQUEST_TAKE_PHOTO1 = 1001;
    private static final int REQUEST_TAKE_PHOTO2 = 1002;
    private static final int REQUEST_TAKE_PHOTO3 = 1003;
    private static final int REQUEST_TAKE_PHOTO4 = 1004;
    private static final int REQUEST_TAKE_PHOTO5 = 1005;
    private static final int REQUEST_TAKE_PHOTO6 = 1006;
    private static final int REQUEST_TAKE_PHOTO7 = 1007;
    private static final int REQUEST_TAKE_PHOTO8 = 1008;

    private static final int MY_CAMERA_PERMISSION_CODE1 = 2001;
    private static final int MY_CAMERA_PERMISSION_CODE2 = 2002;
    private static final int MY_CAMERA_PERMISSION_CODE3 = 2003;
    private static final int MY_CAMERA_PERMISSION_CODE4 = 2004;
    private static final int MY_CAMERA_PERMISSION_CODE5 = 2005;
    private static final int MY_CAMERA_PERMISSION_CODE6 = 2006;
    private static final int MY_CAMERA_PERMISSION_CODE7 = 2007;
    private static final int MY_CAMERA_PERMISSION_CODE8 = 2008;
    private static final int MY_LOCATION_PERMISSION_CODE = 2009;

    private ArrayList<String> engineTypeArrayId = new ArrayList<>();
    private ArrayList<String> engineTypeArrayName = new ArrayList<>();
    private ArrayList<String> wheelsArrayId = new ArrayList<>();
    private ArrayList<String> wheelsArrayName = new ArrayList<>();
    private ArrayList<String> assembArrayId = new ArrayList<>();
    private ArrayList<String> assembArrayName = new ArrayList<>();
    private ArrayList<String> transArrayId = new ArrayList<>();
    private ArrayList<String> transArrayName = new ArrayList<>();
    private ArrayList<String> colorArrayId = new ArrayList<>();
    private ArrayList<String> colorArrayName = new ArrayList<>();
    private ArrayList<String> bodyBuildArrayId = new ArrayList<>();
    private ArrayList<String> bodyBuildArrayName = new ArrayList<>();
    private ArrayList<String> vehicletypeArrayId = new ArrayList<>();
    private ArrayList<String> vehicletypeArrayName = new ArrayList<>();
    private ArrayList<String> modelYearArrayId = new ArrayList<>();
    private ArrayList<String> modelYearArrayName = new ArrayList<>();
    private ArrayList<ModelData> modelList = new ArrayList<>();
    private ArrayList<MakeData> makeList = new ArrayList<>();
    private ArrayList<String> seizeArrayId = new ArrayList<>();
    private ArrayList<String> seizeArrayName = new ArrayList<>();
    private ArrayList<DistrictData> districtList = new ArrayList<>();
    private ArrayList<String> accessoriesIdArr = new ArrayList<>();
    private ArrayList<String> accessoriesNameArr = new ArrayList<>();

    private ArrayList<RegisterNoDistricts.RegistationDistrict> regDistrictList = new ArrayList<>();


    private String seizeId, seizeName, districtId, districtName, seizeAddress, makeId = "", makeName, modelId, modelName,
            modelYearId, modelYearName, vehicletypeId, vehicletypeName,
            bodyBuildId, bodyBuildName, colorId, colorName, transId, transName,
            assembId, assembName, wheelsId, wheelsName, engineTypeId, engineTypeName, reg_district_id;

    private EditText seize_cat_label, seize_cat_spinner,
            seize_form_no_label, seize_form_no,
            seize_district_label, seize_district_spinner,
            driver_name_label, driver_name_et,
            driver_cnic_label, driver_cnic_et,
            driver_mobile_label, driver_mobile_et,
            driver_address_label, driver_address_et,
            owner_label, owner_name_et,
            owner_cnic_label, owner_cnic_et,
            owner_mobile_label, owner_mobile_no,
            squad_no_label, mobile_squard_no;

    private EditText seize_location_tv,
            chasis_no_label, vehicle_chasis_no,
            engine_no_label, vehicle_engine_no,
            vehicle_reg_label, vehicle_reg_no,
            seize_address_label, seize_address_et,
            vehicle_reg_district,
            seize_date,
            seize_time,
            make_label, make_et,
            model_label, model_et,
            model_year_label, year_et,
            vehicle_type_label, vehicle_type_et,
            body_build_label, body_build_et,
            color_label, color_et,
            transmission_label, vehicle_transmission_spinner,
            assembely_label, assembely_spinner,
            wheels_label, wheels_spinner,
            engine_type_label, engine_type_spinner,
            engine_capicity_label, engine_capicity_et,
            mileage_label, mileage_et,
            description_label, description_et, vehicle_reg_district_label;

    //next button 1
    RelativeLayout nxtBtn1, nxtBtn2, nxtBtn3, back_btn_1, back_btn2, back_btn3;

    LinearLayout image1_delete, image2_delete, image3_delete, image4_delete, image5_delete, image6_delete, image7_delete, image8_delete;

    ImageView attachement_btn1, imageview1, attachement_btn2, imageview2, attachement_btn3, imageview3, attachement_btn4,
            imageview4, attachement_btn5, imageview5, attachement_btn6, imageview6, attachement_btn7, imageview7, attachement_btn8, imageview8;

    Button submit_btn;

    //input forms
    LinearLayout seize_layout, form1, form2, form3, form4;

    // accessories list
    private NonScrollListView accessoriesList;
    ScrollView scroll;

    //Animation
    Animation LeftSwipe;
    Animation RightSwipe;
    StateProgressBar stateProgressBar;
    AwesomeProgressDialog mAwesomeProgressDialog;
    File file1, file2, file3, file4, file5, file6, file7, file8;
    File compressedfile1, compressedfile2, compressedfile3, compressedfile4, compressedfile5, compressedfile6, compressedfile7, compressedfile8;
    Uri uri1, uri2, uri3, uri4, uri5, uri6, uri7, uri8;
    String mCurrentPhotoPath;
    List<MultipartBody.Part> fileParts = new ArrayList<>();
    Map<String, RequestBody> accessoriesParts = new HashMap<>();
    Map<String, RequestBody> seizeCatParts = new HashMap<>();

    Map<String, RequestBody> mMap = new HashMap<>();
    private SharedPreferences userSharedPreferences;
    String userID = "";
    MultipartBody.Part part1, part2, part3, part4, part5, part6, part7, part8;

    String formNo;
    String seizedCat;
    String seizedDistrict;
    String driverName;
    String driverCnic;
    String driverMobile;
    String driverAddress;
    String ownerName;
    String ownerCnic;
    String ownerMobileNo;
    String ownerSquadNo;
    String chasisNo;
    String engineNo;
    String vehRegNo;
    String vehRegDistrict;
    String currentDate;
    String currentTime;
    String make;
    String model;
    String modelYear;
    String vehicleType;
    String bodyBuild;
    String color;
    String transmission;
    String assembely;
    String wheels;
    String engineType;
    String engineCapicity;
    String mileage;
    String description;
    List<Map<String, String>> strLst = new ArrayList<>();
    VehicleAccessoriesAdapter vehicleAccessoriesAdapter;
    RegDistrictAdapter mRegDistrictAdapter;
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
    HashMap<String, String> map = new HashMap<>();
    ArrayList<String> list;
    public static String strSeparator = "__,__";
    public static String strSeparator2 = ", ";
    DistrictAdapter districtAdapter;
    MakeAdapter makeAdapter;
    ModelAdapter mModelAdapter;
    ArrayList<String> idList;
    ArrayList<String> nameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seize);
        setUI();
        //initializing login share prefences
        userSharedPreferences = getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        userID = userSharedPreferences.getString(USER_ID, "No Data");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_PERMISSION_CODE);
        } else {
            checkLocation();
        }
        districtId = userSharedPreferences.getString(USER_DISTRICT_ID, "No Data");
        mMap.put("district_id", createPartFromString(districtId));
        if (districtId != "No Data"){
            ArrayList<HashMap<String, String>> list = mDbHelper.getSpecificDistrictData(districtId);
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                districtName = map.get(DbConstants.DISTRICT_NAME);
            }
            seize_district_spinner.setText(districtName);

        }

        String special_squad = userSharedPreferences.getString(SPECIAL_SQUAD, "No Data");
        if (special_squad != "No Data"){
            if (special_squad.equals("1")){
                seize_district_spinner.setOnClickListener(this);
                seize_district_spinner.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp), null);
            }else {
                seize_district_spinner.setOnClickListener(null);
            }
        }


    }

    private void setUI() {
        Toolbar mToolbar = findViewById(R.id.seize_toolbar);
        mToolbar.setTitle(getString(R.string.seize_vehicle));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SeizeActivity.this)
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
        });
        //btns
        nxtBtn1 = findViewById(R.id.next_btn_1);
        nxtBtn2 = findViewById(R.id.next_btn_2);
        back_btn_1 = findViewById(R.id.back_btn_1);
        back_btn2 = findViewById(R.id.back_btn_2);
        back_btn3 = findViewById(R.id.back_btn_3);
        nxtBtn3 = findViewById(R.id.next_btn3);
        imageview1 = findViewById(R.id.imageview1);
        imageview2 = findViewById(R.id.imageview2);
        imageview3 = findViewById(R.id.imageview3);
        imageview4 = findViewById(R.id.imageview4);
        imageview5 = findViewById(R.id.imageview5);
        imageview6 = findViewById(R.id.imageview6);
        imageview7 = findViewById(R.id.imageview7);
        imageview8 = findViewById(R.id.imageview8);
        attachement_btn1 = findViewById(R.id.attachement_btn1);
        image1_delete = findViewById(R.id.image1_delete);
        attachement_btn2 = findViewById(R.id.attachement_btn2);
        image2_delete = findViewById(R.id.image2_delete);
        attachement_btn3 = findViewById(R.id.attachement_btn3);
        image3_delete = findViewById(R.id.image3_delete);
        attachement_btn4 = findViewById(R.id.attachement_btn4);
        image4_delete = findViewById(R.id.image4_delete);
        attachement_btn5 = findViewById(R.id.attachement_btn5);
        image5_delete = findViewById(R.id.image5_delete);
        attachement_btn6 = findViewById(R.id.attachement_btn6);
        image6_delete = findViewById(R.id.image6_delete);
        attachement_btn7 = findViewById(R.id.attachement_btn7);
        image7_delete = findViewById(R.id.image7_delete);
        attachement_btn8 = findViewById(R.id.attachement_btn8);
        image8_delete = findViewById(R.id.image8_delete);
        submit_btn = findViewById(R.id.submit_btn);
        //page layouts
        seize_layout = findViewById(R.id.seize_layout);
        form1 = findViewById(R.id.form1);
        form2 = findViewById(R.id.form2);
        form3 = findViewById(R.id.form3);
        form4 = findViewById(R.id.form4);
        stateProgressBar = findViewById(R.id.your_state_progress_bar_id);
        //page 1 fields
        seize_form_no_label = findViewById(R.id.seize_form_no_label);
        seize_form_no = findViewById(R.id.seize_form_no);
        seize_cat_spinner = findViewById(R.id.seize_cat_spinner);
        seize_cat_label = findViewById(R.id.seize_cat_label);
        seize_address_label = findViewById(R.id.seize_address_label);
        seize_address_et = findViewById(R.id.seize_address_et);
        seize_district_label = findViewById(R.id.seize_district_label);
        seize_district_spinner = findViewById(R.id.seize_district_spinner);
        driver_name_label = findViewById(R.id.driver_name_label);
        driver_name_et = findViewById(R.id.driver_name_et);
        driver_cnic_label = findViewById(R.id.driver_cnic_label);
        driver_cnic_et = findViewById(R.id.driver_cnic_et);
        driver_mobile_label = findViewById(R.id.driver_mobile_label);
        driver_mobile_et = findViewById(R.id.driver_mobile_et);
        driver_address_label = findViewById(R.id.driver_address_label);
        driver_address_et = findViewById(R.id.driver_address_et);
        owner_label = findViewById(R.id.owner_label);
        owner_name_et = findViewById(R.id.owner_name_et);
        owner_cnic_label = findViewById(R.id.owner_cnic_label);
        owner_cnic_et = findViewById(R.id.owner_cnic_et);
        owner_mobile_label = findViewById(R.id.owner_mobile_label);
        owner_mobile_no = findViewById(R.id.owner_mobile_no);
        squad_no_label = findViewById(R.id.squad_no_label);
        mobile_squard_no = findViewById(R.id.mobile_squard_no);
        vehicle_reg_district = findViewById(R.id.vehicle_reg_district);
        vehicle_reg_district_label = findViewById(R.id.vehicle_reg_district_label);

        //page 2 fields
        chasis_no_label = findViewById(R.id.chasis_no_label);
        vehicle_chasis_no = findViewById(R.id.vehicle_chasis_no);
        engine_no_label = findViewById(R.id.engine_no_label);
        vehicle_engine_no = findViewById(R.id.vehicle_engine_no);
        vehicle_reg_label = findViewById(R.id.vehicle_reg_label);
        vehicle_reg_no = findViewById(R.id.vehicle_reg_no);
        seize_date = findViewById(R.id.seize_date);
        seize_time = findViewById(R.id.seize_time);
        make_label = findViewById(R.id.make_label);
        make_et = findViewById(R.id.make_et);
        model_label = findViewById(R.id.model_label);
        model_et = findViewById(R.id.model_et);
        model_year_label = findViewById(R.id.model_year_label);
        year_et = findViewById(R.id.year_et);
        vehicle_type_label = findViewById(R.id.vehicle_type_label);
        vehicle_type_et = findViewById(R.id.vehicle_type_et);
        body_build_label = findViewById(R.id.body_build_label);
        body_build_et = findViewById(R.id.body_build_et);
        color_label = findViewById(R.id.color_label);
        color_et = findViewById(R.id.color_et);
        transmission_label = findViewById(R.id.transmission_label);
        vehicle_transmission_spinner = findViewById(R.id.vehicle_transmission_spinner);
        assembely_label = findViewById(R.id.assembely_label);
        assembely_spinner = findViewById(R.id.assembely_spinner);
        wheels_label = findViewById(R.id.wheels_label);
        wheels_spinner = findViewById(R.id.wheels_spinner);
        engine_type_label = findViewById(R.id.engine_type_label);
        engine_type_spinner = findViewById(R.id.engine_type_spinner);
        engine_capicity_label = findViewById(R.id.engine_capicity_label);
        engine_capicity_et = findViewById(R.id.engine_capicity_et);
        mileage_label = findViewById(R.id.mileage_label);
        mileage_et = findViewById(R.id.mileage_et);
        //page 3 accessories list
        accessoriesList = findViewById(R.id.accessories_list);
        //page 4 fields
        description_label = findViewById(R.id.description_label);
        description_et = findViewById(R.id.description_et);
        scroll = findViewById(R.id.scroll);
        //for animcation
        LeftSwipe = AnimationUtils.loadAnimation(this, R.anim.left_slide);
        RightSwipe = AnimationUtils.loadAnimation(this, R.anim.right_slide);
        mAwesomeProgressDialog = new AwesomeProgressDialog(this);
        // calling helper methods
        loadAccessories();
        loadSeizeCategories();
        loadDistrict();
        loadMake();
        loadModelYear();
        loadVehicletype();
        loadBodyBuild();
        loadColor();
        loadTransmission();
        loadAssembely();
        loadWeehls();
        loadEngine();
        loadRegDistricts();
        //text change listeners
        seize_form_no.addTextChangedListener(new GenericTextWatcher(seize_form_no));
        driver_name_et.addTextChangedListener(new GenericTextWatcher(driver_name_et));
        driver_cnic_et.addTextChangedListener(new GenericTextWatcher(driver_cnic_et));
        driver_mobile_et.addTextChangedListener(new GenericTextWatcher(driver_mobile_et));
        driver_address_et.addTextChangedListener(new GenericTextWatcher(driver_address_et));
        owner_name_et.addTextChangedListener(new GenericTextWatcher(owner_name_et));
        owner_cnic_et.addTextChangedListener(new GenericTextWatcher(owner_cnic_et));
        owner_mobile_no.addTextChangedListener(new GenericTextWatcher(owner_mobile_no));
        mobile_squard_no.addTextChangedListener(new GenericTextWatcher(mobile_squard_no));

        vehicle_chasis_no.addTextChangedListener(new GenericTextWatcher(vehicle_chasis_no));
        vehicle_engine_no.addTextChangedListener(new GenericTextWatcher(vehicle_engine_no));
        vehicle_reg_no.addTextChangedListener(new GenericTextWatcher(vehicle_reg_no));
        engine_capicity_et.addTextChangedListener(new GenericTextWatcher(engine_capicity_et));
        mileage_et.addTextChangedListener(new GenericTextWatcher(mileage_et));
        description_et.addTextChangedListener(new GenericTextWatcher(description_et));
        //click listeners
        seize_cat_spinner.setOnClickListener(this);
        make_et.setOnClickListener(this);
        model_et.setOnClickListener(this);
        year_et.setOnClickListener(this);
        vehicle_type_et.setOnClickListener(this);
        body_build_et.setOnClickListener(this);
        color_et.setOnClickListener(this);
        vehicle_transmission_spinner.setOnClickListener(this);
        assembely_spinner.setOnClickListener(this);
        wheels_spinner.setOnClickListener(this);
        engine_type_spinner.setOnClickListener(this);
        vehicle_reg_district.setOnClickListener(this);

        nxtBtn1.setOnClickListener(this);
        nxtBtn2.setOnClickListener(this);
        nxtBtn3.setOnClickListener(this);

        back_btn_1.setOnClickListener(this);
        back_btn2.setOnClickListener(this);
        back_btn3.setOnClickListener(this);
        back_btn3.setOnClickListener(this);

        attachement_btn1.setOnClickListener(this);
        attachement_btn2.setOnClickListener(this);
        attachement_btn3.setOnClickListener(this);
        attachement_btn4.setOnClickListener(this);
        attachement_btn5.setOnClickListener(this);
        attachement_btn6.setOnClickListener(this);
        attachement_btn7.setOnClickListener(this);
        attachement_btn8.setOnClickListener(this);

        image1_delete.setOnClickListener(this);
        image2_delete.setOnClickListener(this);
        image3_delete.setOnClickListener(this);
        image4_delete.setOnClickListener(this);
        image5_delete.setOnClickListener(this);
        image6_delete.setOnClickListener(this);
        image7_delete.setOnClickListener(this);
        image8_delete.setOnClickListener(this);

        imageview1.setOnClickListener(this);
        imageview2.setOnClickListener(this);
        imageview3.setOnClickListener(this);
        imageview4.setOnClickListener(this);
        imageview5.setOnClickListener(this);
        imageview6.setOnClickListener(this);
        imageview7.setOnClickListener(this);
        imageview8.setOnClickListener(this);

        submit_btn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seize_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.seize_finish) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to cancel form?")
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
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id){
            case R.id.next_btn_1:
                // form one validation and show form 2
                FormOneValidation();
                break;

            case R.id.next_btn3:
                // show form 4
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        accessoriesParts.clear();
                        form4.setVisibility(View.VISIBLE);
                        form3.setVisibility(View.GONE);
                        form4.startAnimation(LeftSwipe);
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                        scroll.scrollTo(0,0);
                        description_et.requestFocus();
                        list = vehicleAccessoriesAdapter.getId();
                        Log.e(TAG, "list size: "+list.size());
                        for(int i =0 ;i<list.size();i++) {
                            accessoriesParts.put("access[]"+i, createPartFromString(list.get(i)));
                        }
                    }
                });
                break;

            case R.id.next_btn_2:
                // show form 3
                FormTwoValidation();
                break;

            case R.id.back_btn_1:
                // show form 1
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        form1.setVisibility(View.VISIBLE);
                        form2.setVisibility(View.GONE);
                        form1.startAnimation(RightSwipe);
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                        scroll.scrollTo(0,0);
                    }
                });
                break;

            case R.id.back_btn_2:
                // show form 2
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        form2.setVisibility(View.VISIBLE);
                        form3.setVisibility(View.GONE);
                        form2.startAnimation(LeftSwipe);
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        scroll.scrollTo(0,0);
                    }
                });
                break;

            case R.id.back_btn_3:
                // show form 3
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        form3.setVisibility(View.VISIBLE);
                        form4.setVisibility(View.GONE);
                        form3.startAnimation(LeftSwipe);
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                        scroll.scrollTo(0,0);
                    }
                });
                break;

            case R.id.attachement_btn1:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE1);
                } else {
                    dispatchTakePictureIntent1();

                }
                break;
            case R.id.attachement_btn2:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE2);
                } else {
                    dispatchTakePictureIntent2();
                }
                break;
            case R.id.attachement_btn3:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE3);
                } else {
                    dispatchTakePictureIntent3();
                }
                break;
            case R.id.attachement_btn4:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE4);
                } else {
                    dispatchTakePictureIntent4();
                }
                break;
            case R.id.attachement_btn5:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE5);
                } else {
                    dispatchTakePictureIntent5();
                }
                break;
            case R.id.attachement_btn6:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE6);
                } else {
                    dispatchTakePictureIntent6();
                }
                break;
            case R.id.attachement_btn7:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE7);
                } else {
                    dispatchTakePictureIntent7();
                }
                break;
            case R.id.attachement_btn8:
                if (ContextCompat.checkSelfPermission(SeizeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SeizeActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            MY_CAMERA_PERMISSION_CODE8);
                } else {
                    dispatchTakePictureIntent8();
                }
                break;
            case R.id.image1_delete:
                removeImage(1);
                break;
            case R.id.image2_delete:
                removeImage(2);
                break;
            case R.id.image3_delete:
                removeImage(3);
                break;
            case R.id.image4_delete:
                removeImage(4);
                break;
            case R.id.image5_delete:
                removeImage(5);
                break;
            case R.id.image6_delete:
                removeImage(6);
                break;
            case R.id.image7_delete:
                removeImage(7);
                break;
            case R.id.image8_delete:
                removeImage(8);
                break;
            case R.id.imageview1:
                showImagePreview(Uri.fromFile(compressedfile1));
                break;
            case R.id.imageview2:
                showImagePreview(Uri.fromFile(compressedfile2));
                break;
            case R.id.imageview3:
                showImagePreview(Uri.fromFile(compressedfile3));
                break;
            case R.id.imageview4:
                showImagePreview(Uri.fromFile(compressedfile4));
                break;
            case R.id.imageview5:
                showImagePreview(Uri.fromFile(compressedfile5));
                break;
            case R.id.imageview6:
                showImagePreview(Uri.fromFile(compressedfile6));
                break;
            case R.id.imageview7:
                showImagePreview(Uri.fromFile(compressedfile7));
                break;
            case R.id.imageview8:
                showImagePreview(Uri.fromFile(compressedfile8));
                break;

            case R.id.submit_btn:
                // submit form data
                FormFourValidation();
                break;
            case R.id.seize_cat_spinner:
                // show seize cat dialog
                showSeizedCatDialog();
                break;
            case R.id.vehicle_reg_district:
                // show seize cat dialog
                showRegDistrictDialog();
                break;
            case R.id.seize_district_spinner:
                // show district dialog
                showDistrictDialog();
                break;
            case R.id.make_et:
                // show make dialog
                showMakeDialog();
                break;
            case R.id.model_et:
                // show model dialog
                if (!makeId.equals("")){
                    showModelDialog();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            make_label.requestFocus();
                            make_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
                            make_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            Snackbar snackbar = Snackbar
                                    .make(seize_layout, "Select Make", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    });

                }

                break;
            case R.id.year_et:
                // show model year dialog
                showModelYearDialog();
                break;
            case R.id.vehicle_type_et:
                // show model year dialog
                showVehcileDialog();
                break;
            case R.id.body_build_et:
                // show model year dialog
                showBodyBuildDialog();
                break;
            case R.id.color_et:
                // show model year dialog
                showColorDialog();
                break;
            case R.id.vehicle_transmission_spinner:
                // show model year dialog
                showTransmissionDialog();
                break;
            case R.id.assembely_spinner:
                // show model year dialog
                showAssembelyDialog();
                break;
            case R.id.wheels_spinner:
                // show model year dialog
                showWheelsDialog();
                break;
            case R.id.engine_type_spinner:
                // show model year dialog
                showEngineTypeDialog();
                break;

        }
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
                                imageview1.setImageResource(android.R.color.transparent);
                                attachement_btn1.setVisibility(View.VISIBLE);
                                image1_delete.setVisibility(View.GONE);
                                imageview1.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part1)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part1 );
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
                                imageview2.setImageResource(android.R.color.transparent);
                                attachement_btn2.setVisibility(View.VISIBLE);
                                image2_delete.setVisibility(View.GONE);
                                imageview2.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part2)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part2 );
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
                                imageview3.setImageResource(android.R.color.transparent);
                                attachement_btn3.setVisibility(View.VISIBLE);
                                image3_delete.setVisibility(View.GONE);
                                imageview3.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part3)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part3);
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
                                imageview4.setImageResource(android.R.color.transparent);
                                attachement_btn4.setVisibility(View.VISIBLE);
                                image4_delete.setVisibility(View.GONE);
                                imageview4.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part4)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part4 );
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
                                imageview5.setImageResource(android.R.color.transparent);
                                attachement_btn5.setVisibility(View.VISIBLE);
                                image5_delete.setVisibility(View.GONE);
                                imageview5.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part5)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part5 );
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
                                imageview6.setImageResource(android.R.color.transparent);
                                attachement_btn6.setVisibility(View.VISIBLE);
                                image6_delete.setVisibility(View.GONE);
                                imageview6.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part6)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part6);
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
                                imageview7.setImageResource(android.R.color.transparent);
                                attachement_btn7.setVisibility(View.VISIBLE);
                                image7_delete.setVisibility(View.GONE);
                                imageview7.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part7)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part7 );
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
                                imageview8.setImageResource(android.R.color.transparent);
                                attachement_btn8.setVisibility(View.VISIBLE);
                                image8_delete.setVisibility(View.GONE);
                                imageview8.setVisibility(View.GONE);

                                for(int i=0;i<fileParts.size();i++)
                                {
                                    if(fileParts.get(i) == part8)
                                    {
                                        fileParts.remove(i);
                                        Log.e(TAG, "Removed: "+part8 );
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
                Dialog previewDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
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
                    Log.e(TAG, "IOException: "+e );
                }
                preview_image.setImageBitmap(bitmap);
                previewDilaog.show();
            }
        });
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
                Log.e(TAG, "IOException: "+e );
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
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, File file) {
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse(FileUtils.getMimeType(file)), file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode){
                case REQUEST_TAKE_PHOTO1:
                    if(uri1 != null){
                        try {
                            compressedfile1 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file1);
                            delete(file1);
                            imageview1.setVisibility(View.VISIBLE);
                            imageview1.setImageURI(Uri.fromFile(compressedfile1));
                            image1_delete.setVisibility(View.VISIBLE);
                            attachement_btn1.setVisibility(View.GONE);
                            part1 = prepareFilePart("files[]", compressedfile1);
                            fileParts.add(part1);


                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO2:
                    if(uri2 != null){
                        try {
                            compressedfile2 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file2);
                            delete(file2);
                            imageview2.setVisibility(View.VISIBLE);
                            imageview2.setImageURI(Uri.fromFile(compressedfile2));
                            image2_delete.setVisibility(View.VISIBLE);
                            attachement_btn2.setVisibility(View.GONE);
                            part2 = prepareFilePart("files[]", compressedfile2);
                            fileParts.add(part2);



                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO3:
                    if(uri3 != null){
                        try {
                            compressedfile3 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file3);
                            delete(file3);
                            imageview3.setVisibility(View.VISIBLE);
                            imageview3.setImageURI(Uri.fromFile(compressedfile3));
                            image3_delete.setVisibility(View.VISIBLE);
                            attachement_btn3.setVisibility(View.GONE);
                            part3 = prepareFilePart("files[]", compressedfile3);
                            fileParts.add(part3);




                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO4:
                    if(uri4 != null){
                        try {
                            compressedfile4 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file4);
                            delete(file4);
                            imageview4.setVisibility(View.VISIBLE);
                            imageview4.setImageURI(Uri.fromFile(compressedfile4));
                            image4_delete.setVisibility(View.VISIBLE);
                            attachement_btn4.setVisibility(View.GONE);
                            part4 = prepareFilePart("files[]", compressedfile4);
                            fileParts.add(part4);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO5:
                    if(uri5 != null){
                        try {
                            compressedfile5 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file5);
                            delete(file5);
                            imageview5.setVisibility(View.VISIBLE);
                            imageview5.setImageURI(Uri.fromFile(compressedfile5));
                            image5_delete.setVisibility(View.VISIBLE);
                            attachement_btn5.setVisibility(View.GONE);
                            part5 = prepareFilePart("files[]", compressedfile5);
                            fileParts.add(part5);


                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO6:
                    if(uri6 != null){
                        try {
                            compressedfile6 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file6);
                            delete(file6);
                            imageview6.setVisibility(View.VISIBLE);
                            imageview6.setImageURI(Uri.fromFile(compressedfile6));
                            image6_delete.setVisibility(View.VISIBLE);
                            attachement_btn6.setVisibility(View.GONE);
                            part6 = prepareFilePart("files[]", compressedfile6);
                            fileParts.add(part6);



                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO7:
                    if(uri7 != null){
                        try {
                            compressedfile7 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file7);
                            delete(file7);
                            imageview7.setVisibility(View.VISIBLE);
                            imageview7.setImageURI(Uri.fromFile(compressedfile7));
                            image7_delete.setVisibility(View.VISIBLE);
                            attachement_btn7.setVisibility(View.GONE);
                            part7 = prepareFilePart("files[]", compressedfile7);
                            fileParts.add(part7);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case REQUEST_TAKE_PHOTO8:
                    if(uri8 != null){
                        try {
                            compressedfile8 = new Compressor(this)
                                    .setQuality(50)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation")
                                    .compressToFile(file8);
                            delete(file8);
                            imageview8.setVisibility(View.VISIBLE);
                            imageview8.setImageURI(Uri.fromFile(compressedfile8));
                            image8_delete.setVisibility(View.VISIBLE);
                            attachement_btn8.setVisibility(View.GONE);
                            part8 = prepareFilePart("files[]", compressedfile8);
                            fileParts.add(part8);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "IOException: "+e.toString());
                        }

                    }
                    break;

                case 1000:
                    if(resultCode == Activity.RESULT_OK){
                        String result = data.getStringExtra("result");
                    }if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                    checkLocation();
                }
                    break;

            }

        }catch (Exception e){
            Log.e(TAG, "onActivityResult: "+e );
        }

    }

    private void delete(File file) {
        file.delete();
        if(file.exists()){
            try {
                file.getCanonicalFile().delete();
            } catch (IOException e) {
                Log.e(TAG, "IOException: "+e.toString() );
            }
            if(file.exists()){
                getApplicationContext().deleteFile(file.getName());
            }
        }
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
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void postData() {

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
        Call<List<SeizePostData>> call = seizedVehicleApi.uploadFiles(mMap, seizeCatParts, accessoriesParts, fileParts);
        Log.e(TAG, "postData: "+mMap.toString() );
        Log.e(TAG, "postDataSize: "+mMap.size() );
        Log.e(TAG, "fileParts: "+fileParts.toString() );
        call.enqueue(new Callback<List<SeizePostData>>() {
            @Override
            public void onResponse(Call<List<SeizePostData>> call, Response<List<SeizePostData>> response) {
                if (response.isSuccessful()){
                    Log.e(TAG, "message: "+"success" );
                    int success = response.body().get(0).getSuccess();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAwesomeProgressDialog.hide();
                            new AwesomeSuccessDialog(SeizeActivity.this)
                                    .setTitle("Success")
                                    .setMessage("You have submited the form successfully")
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
                                            deleteFiles(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/Excise_and_Taxation");
                                            finish();

                                        }
                                    })
                                    .show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<SeizePostData>> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAwesomeProgressDialog.hide();
                        new AwesomeErrorDialog(SeizeActivity.this)
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
    public static void deleteFiles(String path) {

        File file = new File(path);

        if (file.exists()) {
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) { }
        }
    }
    private void FormTwoValidation() {
        chasisNo = vehicle_chasis_no.getText().toString();
        engineNo = vehicle_engine_no.getText().toString();
        vehRegNo = vehicle_reg_no.getText().toString();
        vehRegDistrict = vehicle_reg_district.getText().toString();
        currentDate = seize_date.getText().toString();
        currentTime = seize_time.getText().toString();
        make = make_et.getText().toString();
        model = model_et.getText().toString();
        modelYear = year_et.getText().toString();
        vehicleType = vehicle_type_et.getText().toString();
        bodyBuild = body_build_et.getText().toString();
        color = color_et.getText().toString();
        transmission = vehicle_transmission_spinner.getText().toString();
        assembely = assembely_spinner.getText().toString();
        wheels = wheels_spinner.getText().toString();
        engineType = engine_type_spinner.getText().toString();
        engineCapicity = engine_capicity_et.getText().toString();
        mileage = mileage_et.getText().toString();
        if (TextUtils.isEmpty(chasisNo)){
            chasis_no_label.setError(null);
            chasis_no_label.requestFocus();
            chasis_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            vehicle_chasis_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }else if (TextUtils.isEmpty(engineNo)){
            engine_no_label.setError(null);
            engine_no_label.requestFocus();
            engine_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            vehicle_engine_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }/*else if (TextUtils.isEmpty(vehRegNo)){
            *//*vehicle_reg_label.setError(null);
            vehicle_reg_label.requestFocus();
            vehicle_reg_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            vehicle_reg_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));*//*

        }*/else if (TextUtils.isEmpty(vehRegDistrict)){
            vehicle_reg_district_label.setError(null);
            vehicle_reg_district_label.requestFocus();
            vehicle_reg_district_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            vehicle_reg_district.setBackground(getResources().getDrawable(R.drawable.error_layout2));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Vehicle Reg District", Snackbar.LENGTH_SHORT);
            snackbar.show();

        }else if (TextUtils.isEmpty(make)){
            make_label.requestFocus();
            make_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            make_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Make", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(model)){
            model_label.requestFocus();
            model_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            model_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Model", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(modelYear)){
            model_year_label.requestFocus();
            year_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            model_year_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Model Year", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(vehicleType)){
            vehicle_type_label.requestFocus();
            vehicle_type_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            vehicle_type_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Vehicle Type", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(bodyBuild)){
            body_build_label.requestFocus();
            body_build_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            body_build_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Body Build", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(color)){
            color_label.requestFocus();
            color_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            color_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select ColorData", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(transmission)){
            transmission_label.requestFocus();
            vehicle_transmission_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            transmission_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Transmission", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(assembely)){
            assembely_label.requestFocus();
            assembely_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            assembely_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Assembely", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(wheels)){
            wheels_label.requestFocus();
            wheels_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            wheels_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Wheels", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(engineType)){
            engine_type_label.requestFocus();
            engine_type_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            engine_type_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Engine Type", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(engineCapicity)){
            engine_capicity_label.setError(null);
            engine_capicity_label.requestFocus();
            engine_capicity_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            engine_capicity_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));
        }else if (TextUtils.isEmpty(mileage)){
            mileage_label.setError(null);
            mileage_label.requestFocus();
            mileage_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            mileage_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }else {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    form3.setVisibility(View.VISIBLE);
                    form2.setVisibility(View.GONE);
                    form3.startAnimation(LeftSwipe);
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                    scroll.scrollTo(0,0);
                    if (TextUtils.isEmpty(vehRegNo)){
                        vehRegNo = "N/A";
                    }
                }
            });
        }
    }
    private void FormOneValidation() {
        formNo = seize_form_no.getText().toString();
        seizedCat = seize_cat_spinner.getText().toString();
        seizedDistrict = seize_district_spinner.getText().toString();
        seizeAddress = seize_address_et.getText().toString();
        driverName = driver_name_et.getText().toString();
        driverCnic = driver_cnic_et.getText().toString();
        driverMobile = driver_mobile_et.getText().toString();
        driverAddress = driver_address_et.getText().toString();
        ownerName = owner_name_et.getText().toString();
        ownerCnic = owner_cnic_et.getText().toString();
        ownerMobileNo = owner_mobile_no.getText().toString();
        ownerSquadNo = mobile_squard_no.getText().toString();

        if (TextUtils.isEmpty(formNo) || formNo.length() < 4){
            seize_form_no_label.setError(null);
            seize_form_no_label.requestFocus();
            seize_form_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            seize_form_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }else if (TextUtils.isEmpty(seizedCat)){
            seize_cat_label.requestFocus();
            seize_cat_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            seize_cat_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Seize Category", Snackbar.LENGTH_SHORT);
            snackbar.show();

        } else if (TextUtils.isEmpty(seizedDistrict)){
            seize_district_label.requestFocus();
            seize_district_spinner.setBackground(getResources().getDrawable(R.drawable.error_layout));
            seize_district_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Select Seize Category", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else if (TextUtils.isEmpty(seizeAddress)){
            seize_address_label.requestFocus();
            seize_address_et.setBackground(getResources().getDrawable(R.drawable.error_layout));
            seize_address_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Enter Seize Address", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }/*else if (TextUtils.isEmpty(driverName)){
            driver_name_label.setError(null);
            driver_name_label.requestFocus();
            driver_name_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            driver_name_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }*//*else if (TextUtils.isEmpty(driverCnic) || driverCnic.length() < 13){
            driver_cnic_label.setError(null);
            driver_cnic_label.requestFocus();
            driver_cnic_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            driver_cnic_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }*//*else if (TextUtils.isEmpty(driverMobile) || driverMobile.length() < 11){
            driver_mobile_label.setError(null);
            driver_mobile_label.requestFocus();
            driver_mobile_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            driver_mobile_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }*/ else if (TextUtils.isEmpty(ownerSquadNo)){
            squad_no_label.setError(null);
            squad_no_label.requestFocus();
            squad_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            mobile_squard_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));

        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    form1.setVisibility(View.GONE);
                    form2.setVisibility(View.VISIBLE);
                    form2.startAnimation(LeftSwipe);
                    stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                    scroll.scrollTo(0,0);
                    vehicle_chasis_no.requestFocus();
                    Date currentTimeStamp = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("h:mm a");
                    String currentDate = sdf.format(currentTimeStamp);
                    String currentTime = sdf2.format(currentTimeStamp);
                    Log.e(TAG, "time stamp: "+currentTimeStamp );
                    Log.e(TAG, "date: "+currentDate );
                    seize_date.setText(currentDate);
                    seize_time.setText(currentTime);
                    if (TextUtils.isEmpty(ownerName)){
                        ownerName = "N/A";

                    } if (TextUtils.isEmpty(ownerCnic)){
                        ownerCnic = "N/A";

                    } if (TextUtils.isEmpty(ownerMobileNo)){
                        ownerMobileNo = "N/A";

                    }

                }
            });
        }
    }
    private void FormFourValidation() {
        description = description_et.getText().toString();
        Log.e(TAG, "fileParts size: "+fileParts.size() );
        if(fileParts.size()<7){
            Snackbar snackbar = Snackbar
                    .make(seize_layout, "Capture atleast 7 pictures", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else {

            if (!isNetworkAvailable()){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       saveDataLocaly();
                        new AwesomeErrorDialog(SeizeActivity.this)
                                .setTitle("No Internet Connection")
                                .setMessage("Form will be sent later")
                                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                .setCancelable(false)
                                .setButtonText(getString(R.string.dialog_ok_button))
                                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                .setButtonText(getString(R.string.dialog_ok_button))
                                .setErrorButtonClick(new Closure() {
                                    @Override
                                    public void exec() {
                                        // click
                                        finish();
                                    }
                                })
                                .show();
                    }
                });


            }else {
                mMap.put("formserialno", createPartFromString(formNo));
                Log.e("formNo", "onCreate: "+formNo );

                mMap.put("seize_address", createPartFromString(seizeAddress));
                Log.e("seize_address", "onCreate: "+seizeAddress );

                mMap.put("drivername", createPartFromString(driverName));
                Log.e("driverName", "onCreate: "+driverName );

                mMap.put("drivercnicno", createPartFromString(driverCnic));
                Log.e("driverCnic", "onCreate: "+driverCnic );

                mMap.put("drivermobileno", createPartFromString(driverMobile));
                Log.e("driverMobile", "onCreate: "+driverMobile );

                mMap.put("driveraddress", createPartFromString(driverAddress));
                Log.e("driverAddress", "onCreate: "+driverAddress );

                mMap.put("vechileownername", createPartFromString(ownerName));
                Log.e("ownerName", "onCreate: "+ownerName );

                mMap.put("vechileownercnic",createPartFromString(ownerCnic));
                Log.e("ownerCnic", "onCreate: "+ownerCnic );

                mMap.put("vechileownermobileno",createPartFromString(ownerMobileNo));
                Log.e("ownerMobileNo", "onCreate: "+ownerMobileNo );

                mMap.put("vechileregistrationno", createPartFromString(vehRegNo));
                Log.e("vehRegNo", "onCreate: "+vehRegNo );

                mMap.put("vechicledescription", createPartFromString(description));
                Log.e("description", "onCreate: "+description );

                mMap.put("mobilesquadno", createPartFromString(ownerSquadNo));
                Log.e("ownerSquadNo", "onCreate: "+ownerSquadNo );

                mMap.put("chasisno", createPartFromString(chasisNo));
                Log.e("chasisNo", "onCreate: "+chasisNo );

                mMap.put("engineno", createPartFromString(engineNo));
                Log.e("engineNo", "onCreate: "+engineNo );

                mMap.put("siezeddate", createPartFromString(currentDate));
                Log.e("currentDate", "onCreate: "+currentDate );

                mMap.put("siezedtime", createPartFromString(currentTime));
                Log.e("currentTime", "onCreate: "+currentTime );

                mMap.put("vehicleengine_capcaity", createPartFromString(engineCapicity));
                Log.e("engineCapicity", "onCreate: "+engineCapicity );

                mMap.put("mileage", createPartFromString(mileage));
                Log.e("mileage", "onCreate: "+mileage );

                mMap.put("user_id", createPartFromString(userID));
                Log.e("userID", "onCreate: "+userID );

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(SeizeActivity.this)
                                .setMessage("Are you sure to submit form")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mAwesomeProgressDialog.setTitle("Uploading Data")
                                                .setMessage("Please Wait")
                                                .setColoredCircle(R.color.colorPrimaryDark)
                                                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                                .setCancelable(false)
                                                .show();
                                        if(!isNetworkAvailable()){
                                            mAwesomeProgressDialog.hide();
                                            new AwesomeErrorDialog(SeizeActivity.this)
                                                    .setTitle("No Internet Connection")
                                                    .setMessage("Please Try Again!")
                                                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                                    .setCancelable(false)
                                                    .setButtonText(getString(R.string.dialog_ok_button))
                                                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                                    .setButtonText(getString(R.string.dialog_ok_button))
                                                    .setErrorButtonClick(new Closure() {
                                                        @Override
                                                        public void exec() {
                                                            // click

                                                        }
                                                    })
                                                    .show();
                                        }else {
                                            postData();
                                        }


                                    }

                                })
                                .setNegativeButton("No", null)
                                .show();

                    }
                });
            }

        }

    }
    private void saveDataLocaly() {

        map.put(SEIZE_FORM_NO, formNo);
        map.put(SEIZE_CAT_ID, convertArrayToString(idList));
        map.put(SEIZE_CAT_NAME, convertArrayToString(nameList));
        map.put(SEIZE_DISTRICT_ID, districtId);
        map.put(SEIZE_DRIVER_NAME, driverName);
        map.put(SEIZE_DRIVER_CNIC, driverCnic);
        map.put(SEIZE_DRIVER_MOB_NO, driverMobile);
        map.put(SEIZE_DRIVER_ADDRESS, driverAddress);
        map.put(SEIZE_VEHICLE_OWNER_NAME, ownerName);
        map.put(SEIZE_VEHICLE_OWNER_CNIC, ownerCnic);
        map.put(SEIZE_VEHICLE_OWNER_MOB_NO, ownerMobileNo);
        map.put(SEIZE_SQUAD_NO, ownerSquadNo);


        map.put(SEIZE_CHASIS_NO, chasisNo);
        map.put(SEIZE_ENGINE_NO, engineNo);
        map.put(SEIZE_VEHICLE_REG_NO, vehRegNo);
        map.put(SEIZE_DATE, currentDate);
        map.put(SEIZE_TIME, currentTime);
        map.put(SEIZE_MODEL_YEAR, modelYearId);
        map.put(SEIZE_MODEL_ID, modelId);
        map.put(SEIZE_MAKE_ID, makeId);
        map.put(SEIZE_VEHICLE_TYPE, vehicletypeName);
        map.put(SEIZE_ENGINE_CAPICITY, engineCapicity);
        map.put(SEIZE_VEHICLE_MILEAGE, mileage);
        map.put(SEIZE_ENGINE_TYPE, engineTypeName);
        map.put(SEIZE_VEHICLE_WEEHLES, wheelsId);
        map.put(SEIZE_BODY_BUILD, bodyBuildId);
        map.put(SEIZE_VEHICLE_COLOR, colorId);
        map.put(SEIZE_VEHICLE_TRANSMISSION, transName);
        map.put(SEIZE_VEHICLE_ASSEMBELY, assembId);


        map.put(SEIZE_DESCRIPTION, description);
        map.put(SEIZE_USER_ID, userID);
        map.put(SEIZE_ACCESSORIES, convertArrayToString(list));

        if (FileUtils.getUri(compressedfile1) != null){
            map.put(SEIZE_IMAGE1, FileUtils.getUri(compressedfile1).toString());
        }else {
            map.put(SEIZE_IMAGE1, "empty");

        }
        if (FileUtils.getUri(compressedfile2) != null){
            map.put(SEIZE_IMAGE2, FileUtils.getUri(compressedfile2).toString());
        }else {
            map.put(SEIZE_IMAGE2, "empty");

        }
        if (FileUtils.getUri(compressedfile3) != null){
            map.put(SEIZE_IMAGE3, FileUtils.getUri(compressedfile3).toString());
        }else {
            map.put(SEIZE_IMAGE3, "empty");

        }
        if (FileUtils.getUri(compressedfile4) != null){
            map.put(SEIZE_IMAGE4, FileUtils.getUri(compressedfile4).toString());
        }else {
            map.put(SEIZE_IMAGE4, "empty");

        }
        if (FileUtils.getUri(compressedfile5) != null){
            map.put(SEIZE_IMAGE5, FileUtils.getUri(compressedfile5).toString());
        }else {
            map.put(SEIZE_IMAGE5, "empty");

        }
        if (FileUtils.getUri(compressedfile6) != null){
            map.put(SEIZE_IMAGE6, FileUtils.getUri(compressedfile6).toString());
        }else {
            map.put(SEIZE_IMAGE6, "empty");

        }
        if (FileUtils.getUri(compressedfile7) != null){
            map.put(SEIZE_IMAGE7, FileUtils.getUri(compressedfile7).toString());
        }else {
            map.put(SEIZE_IMAGE7, "empty");

        }
        if (FileUtils.getUri(compressedfile8) != null){
            map.put(SEIZE_IMAGE8, FileUtils.getUri(compressedfile8).toString());
        }else {
            map.put(SEIZE_IMAGE8, "empty");

        }

        mDbHelper.addSeizeVehicle(map);
        Log.e("Data", "saveDataLocaly: "+map.toString());
    }
    //helper methods
    public static String convertArrayToString(ArrayList<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            // Do not append comma at the end of last element
            if(i<array.size()-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String convertArrayToString2(ArrayList<String> array){
        String str = "";
        for (int i = 0;i<array.size(); i++) {
            str = str+array.get(i);
            // Do not append comma at the end of last element
            if(i<array.size()-1){
                str = str+strSeparator2;
            }
        }
        return str;
    }
    private void loadAccessories() {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        arrayList = mDbHelper.getAccessoriesData();
        Log.e(TAG, "accessoriesSize: "+arrayList.size() );
        for (int i = 0; i < arrayList.size(); i++) {
            HashMap<String, String> map = arrayList.get(i);
            String accessories_id = map.get(DbConstants.ACCESSORIES_ID);
            String accessories_name = map.get(DbConstants.ACCESSORIES_NAME);
            accessoriesIdArr.add(accessories_id);
            accessoriesNameArr.add(accessories_name);
        }
        Log.e(TAG, "accessoriesIdArr: "+accessoriesIdArr );
        Log.e(TAG, "accessoriesNameArr: "+accessoriesNameArr );
        vehicleAccessoriesAdapter = new VehicleAccessoriesAdapter(SeizeActivity.this, accessoriesIdArr, accessoriesNameArr);
        accessoriesList.setAdapter(vehicleAccessoriesAdapter);

    }
    private void loadRegDistricts() {
        regDistrictList.clear();
        ArrayList<HashMap<String, String>> mapArrayList =  mDbHelper.getRegDistrictData();
        Log.e(TAG, "regdistrict: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String reg_dist_id = map.get(DbConstants.REG_DISTRICT_ID);
            String reg_dist_name = map.get(DbConstants.REG_DISTRICT_NAME);
            regDistrictList.add(new RegisterNoDistricts.RegistationDistrict(reg_dist_id, reg_dist_name));
        }
        Log.e(TAG, "loadMakeId: "+makeList.toString() );

    }
    private void showEngineTypeDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog engineTypeDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                engineTypeDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                engineTypeDilaog.setCancelable(true);
                engineTypeDilaog.setContentView(R.layout.spinner_dialog);
                engineTypeDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = engineTypeDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Engine Type");
                ListView list = engineTypeDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = engineTypeDilaog.findViewById(R.id.search_et);
                EngineTypeAdapter engineTypeAdapter = new EngineTypeAdapter(SeizeActivity.this, engineTypeArrayId, engineTypeArrayName);
                list.setAdapter(engineTypeAdapter);
                engineTypeDilaog.show();
                search_et.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        engineTypeDilaog.dismiss();
                        engine_type_spinner.setText(""+engineTypeArrayName.get(i));
                        engineTypeName = engineTypeArrayName.get(i);
                        Log.e("engineTypeName", engineTypeName);
                        if (!engineTypeName.equals("")){
                            mMap.put("enginetype", createPartFromString(engineTypeName));
                            Log.e( "engineTypeName: ", engineTypeName );
                            engine_type_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            engine_type_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }
                        else {
                        }


                    }
                });
            }
        });
    }
    private void showWheelsDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog wheelsDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                wheelsDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                wheelsDilaog.setCancelable(true);
                wheelsDilaog.setContentView(R.layout.spinner_dialog);
                wheelsDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = wheelsDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Wheels");
                ListView list = wheelsDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = wheelsDilaog.findViewById(R.id.search_et);
                WheelsAdapter wheelsAdapter = new WheelsAdapter(SeizeActivity.this, wheelsArrayId, wheelsArrayName);
                list.setAdapter(wheelsAdapter);
                wheelsDilaog.show();
                search_et.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        wheelsDilaog.dismiss();
                        wheels_spinner.setText(wheelsArrayName.get(i)+" Wheels");
                        wheelsId = wheelsArrayId.get(i);
                        Log.e("wheelsId", wheelsId);
                        if (!wheelsId.equals("")){
                            mMap.put("vechilewheels", createPartFromString(wheelsId));
                            Log.e("wheelsId: ", wheelsId );
                            wheels_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            wheels_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }
                        else {

                        }


                    }
                });
            }
        });

    }
    private void showAssembelyDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog assembelyDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                assembelyDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                assembelyDilaog.setCancelable(true);
                assembelyDilaog.setContentView(R.layout.spinner_dialog);
                assembelyDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = assembelyDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Assembely");
                ListView list = assembelyDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = assembelyDilaog.findViewById(R.id.search_et);
                AssembelyAdapter assembelyAdapter = new AssembelyAdapter(SeizeActivity.this, assembArrayId, assembArrayName);
                list.setAdapter(assembelyAdapter);
                assembelyDilaog.show();
                search_et.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        assembelyDilaog.dismiss();
                        assembely_spinner.setText(""+assembArrayName.get(i));
                        assembId = assembArrayId.get(i);
                        Log.e("assembId", assembId);
                        if (!assembId.equals("")){
                            assembely_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            assembely_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("assembely", createPartFromString(assembId));
                            Log.e("assembId: ", assembId);
                        }
                        else {

                        }
                    }
                });
            }
        });

    }
    private void showTransmissionDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog transmissionDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                transmissionDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                transmissionDilaog.setCancelable(true);
                transmissionDilaog.setContentView(R.layout.spinner_dialog);
                transmissionDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = transmissionDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Vehicle Transmission");
                SearchView search_et = transmissionDilaog.findViewById(R.id.search_et);
                ListView list = transmissionDilaog.findViewById(R.id.seize_cat_list);
                TrasnsmissionAdapter trasnsmissionAdapter= new TrasnsmissionAdapter(SeizeActivity.this, transArrayId, transArrayName);
                list.setAdapter(trasnsmissionAdapter);
                transmissionDilaog.show();
                search_et.setVisibility(View.GONE);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        transmissionDilaog.dismiss();
                        vehicle_transmission_spinner.setText(""+transArrayName.get(i));
                        if (!transArrayName.get(i).equals("")){
                            vehicle_transmission_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            transmission_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("transmission", createPartFromString(transArrayName.get(i)));
                            transName = transArrayName.get(i);
                            Log.e("transArrayName", transArrayName.get(i) );

                        }
                        else {

                        }


                    }
                });
            }
        });
    }
    private void showColorDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog colorDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                colorDilaog.setCancelable(true);
                colorDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = colorDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Vehicle ColorData");
                ListView list = colorDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = colorDilaog.findViewById(R.id.search_et);
                search_et.setVisibility(View.GONE);
                ColorAdapter colorAdapter= new ColorAdapter(SeizeActivity.this, colorArrayId, colorArrayName);
                list.setAdapter(colorAdapter);
                colorDilaog.show();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        colorDilaog.dismiss();
                        color_et.setText(""+colorArrayName.get(i));
                        colorId = colorArrayId.get(i);
                        Log.e("colorId", colorId);
                        if (!colorId.equals("")){
                            color_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            color_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("color_id", createPartFromString(colorId));
                            Log.e( "colorId", colorId);
                        }
                        else {

                        }


                    }
                });
            }
        });
    }
    private void showBodyBuildDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog bodyBuildDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                bodyBuildDilaog.setCancelable(true);
                bodyBuildDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = bodyBuildDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Body Build");
                ListView list = bodyBuildDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = bodyBuildDilaog.findViewById(R.id.search_et);
                search_et.setVisibility(View.GONE);
                BodyBuildAdapter bodyBuildAdapter= new BodyBuildAdapter(SeizeActivity.this, bodyBuildArrayId, bodyBuildArrayName);
                list.setAdapter(bodyBuildAdapter);
                bodyBuildDilaog.show();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        bodyBuildDilaog.dismiss();
                        body_build_et.setText(""+bodyBuildArrayName.get(i));
                        bodyBuildId = bodyBuildArrayId.get(i);
                        Log.e("bodyBuildId", bodyBuildId);
                        if (!bodyBuildId.equals("")){
                            body_build_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            body_build_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("build_id", createPartFromString(bodyBuildId));
                            Log.e("bodyBuildId: ", bodyBuildId );
                        } else {

                        }


                    }
                });
            }
        });
    }
    private void showModelYearDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog modelYearDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                modelYearDilaog.setCancelable(true);
                modelYearDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = modelYearDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Vehile Model Year");
                ListView list = modelYearDilaog.findViewById(R.id.seize_cat_list);
                SearchView search_et = modelYearDilaog.findViewById(R.id.search_et);
                search_et.setVisibility(View.GONE);
                ModeyearlAdapter modelAdapter= new ModeyearlAdapter(SeizeActivity.this, modelYearArrayId, modelYearArrayName);
                list.setAdapter(modelAdapter);
                modelYearDilaog.show();

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        modelYearDilaog.dismiss();
                        year_et.setText(""+modelYearArrayName.get(i));
                        modelYearId = modelYearArrayId.get(i);
                        Log.e("modelYearId", modelYearId);
                        if (!modelYearId.equals("")){
                            year_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            model_year_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("vechiclemodelyear", createPartFromString(modelYearId));
                            Log.e("modelYearId: ", modelYearId );
                        } else {

                        }


                    }
                });
            }
        });
    }
    private void showModelDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog districtDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                districtDilaog.setCancelable(true);
                districtDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = districtDilaog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Vehicle Model");
                final SearchView mSearchView = districtDilaog.findViewById(R.id.search_et);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = districtDilaog.findViewById(R.id.seize_cat_list);
                mModelAdapter = new ModelAdapter(SeizeActivity.this, modelList);
                list.setAdapter(mModelAdapter);
                mModelAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                districtDilaog.show();
                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {
                            loadModel(makeId);
                            mModelAdapter = new ModelAdapter(SeizeActivity.this, modelList);
                            list.setAdapter(mModelAdapter);
                            mModelAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = mModelAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });
                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        districtDilaog.dismiss();
                        ArrayList<ModelData> arrayList = mModelAdapter.getList();
                        model_et.setText(""+arrayList.get(i).getSubmakename());
                        modelId = arrayList.get(i).getSubmakeid();
                        String modelName = arrayList.get(i).getSubmakename();

                        if (!modelId.equals("")){
                            model_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            model_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            //create part and put in hasmap
                            mMap.put("vehicle_model", createPartFromString(modelId));
                            Log.e( "modelId: ", modelId );
                        } else {

                        }


                    }
                });





            }
        });
    }



    private void showMakeDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog makeDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                makeDilaog.setCancelable(true);
                makeDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = makeDilaog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Vehicle Make");
                SearchView mSearchView = makeDilaog.findViewById(R.id.search_et);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = makeDilaog.findViewById(R.id.seize_cat_list);
                makeAdapter = new MakeAdapter(SeizeActivity.this, makeList);
                list.setAdapter(makeAdapter);
                makeAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                makeDilaog.show();
                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {
                            loadMake();
                            makeAdapter = new MakeAdapter(SeizeActivity.this, makeList);
                            list.setAdapter(makeAdapter);
                            makeAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = makeAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });

                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        makeDilaog.dismiss();
                        ArrayList<MakeData> arrayList = new ArrayList<>();
                        arrayList = makeAdapter.getList();
                        make_et.setText(""+arrayList.get(i).getMakename());
                        makeId = arrayList.get(i).getMakeid();
                        String makeName = arrayList.get(i).getMakename();

                        if (!makeId.equals("")){
                            //create part and put in hasmap
                            loadModel(makeId);
                            make_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            make_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            mMap.put("vehicle_make", createPartFromString(makeId));
                            Log.e( "makeId: ", makeId);
                        } else {

                        }


                    }
                });





            }
        });
    }
    private void showSeizedCatDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog seizeDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                seizeDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                seizeDilaog.setCancelable(true);
                seizeDilaog.setContentView(R.layout.seize_spinner_dialog);
                seizeDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = seizeDilaog.findViewById(R.id.seize_dialog_title);
                dialog_title.setText("Select Seize Category");
                SearchView search_et = seizeDilaog.findViewById(R.id.seize_search_et);
                search_et.setVisibility(View.GONE);
                final NonScrollListView list = seizeDilaog.findViewById(R.id.seize_cat_list);
                final SeizeCatAdapter seizeCatAdapter= new SeizeCatAdapter(SeizeActivity.this,seizeArrayId, seizeArrayName);
                list.setAdapter(seizeCatAdapter);
                Button seize_done_btn = seizeDilaog.findViewById(R.id.seize_done_btn);
                seize_done_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        idList = seizeCatAdapter.getSeizeId();
                        nameList = seizeCatAdapter.getSeizeName();
                        Log.e(TAG, "list size: "+idList.size());
                        seizeCatParts.clear();
                        for(int i =0 ;i<idList.size();i++) {
                            seizeCatParts.put("seizecat[]"+i, createPartFromString(idList.get(i)));

                        }
                        if (seizeCatParts.size() > 0){
                            seize_cat_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            seize_cat_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            seize_cat_spinner.setText(convertArrayToString2(nameList));
                            Log.e(TAG, "seizeCatParts: "+seizeCatParts.toString() );
                            Log.e(TAG, "idList: "+idList.toString() );
                        } else {

                        }
                        seizeDilaog.dismiss();
                    }
                });
                seizeDilaog.show();
                /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        seizeDilaog.dismiss();
                        seize_cat_spinner.setText(""+seizeArrayName.get(i));
                        seizeId = seizeArrayId.get(i);
                        Log.e("seizeId", seizeId);
                        if (!seizeId.equals("")){
                            seize_cat_spinner.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            seize_cat_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("vehicleseize_category[]", createPartFromString(seizeId));
                        } else {

                        }


                    }
                });*/
            }
        });
    }
    private void showRegDistrictDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog RegDistrictDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                RegDistrictDilaog.setCancelable(true);
                RegDistrictDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = RegDistrictDilaog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Registration District");
                SearchView mSearchView = RegDistrictDilaog.findViewById(R.id.search_et);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = RegDistrictDilaog.findViewById(R.id.seize_cat_list);
                mRegDistrictAdapter = new RegDistrictAdapter(SeizeActivity.this, regDistrictList);
                list.setAdapter(mRegDistrictAdapter);
                mRegDistrictAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                RegDistrictDilaog.show();
                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {

                            mRegDistrictAdapter = new RegDistrictAdapter(SeizeActivity.this, regDistrictList);
                            list.setAdapter(mRegDistrictAdapter);
                            mRegDistrictAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = mRegDistrictAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });

                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        RegDistrictDilaog.dismiss();

                        regDistrictList = mRegDistrictAdapter.getList();
                        vehicle_reg_district.setText(""+regDistrictList.get(i).getRegistrationdistrictname());
                        reg_district_id = regDistrictList.get(i).getRegistrationdistrictid();
                        String makeName = regDistrictList.get(i).getRegistrationdistrictname();

                        if (!reg_district_id.equals("")){
                            //create part and put in hasmap
                            vehicle_reg_district_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            vehicle_reg_district.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            mMap.put("registration_district", createPartFromString(reg_district_id));
                            Log.e( "reg_district_id: ", reg_district_id);
                        } else {

                        }


                    }
                });





            }
        });
    }
    private void showDistrictDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog districtDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                districtDilaog.setCancelable(true);
                districtDilaog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = districtDilaog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Seize District");
                SearchView mSearchView = districtDilaog.findViewById(R.id.search_et);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = districtDilaog.findViewById(R.id.seize_cat_list);
                districtAdapter = new DistrictAdapter(SeizeActivity.this, districtList);
                list.setAdapter(districtAdapter);
                districtAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                districtDilaog.show();

                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {
                            loadDistrict();
                            districtAdapter = new DistrictAdapter(SeizeActivity.this, districtList);
                            list.setAdapter(districtAdapter);
                            districtAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = districtAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });

                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        districtDilaog.dismiss();
                        ArrayList<DistrictData> arrayList = districtAdapter.getList();
                        seize_district_spinner.setText(""+arrayList.get(i).getDistrictname());
                        districtId = arrayList.get(i).getDistrictid();

                        if (!districtId.equals("")){
                            //create part and put in hasmap
                            mMap.put("district_id", createPartFromString(districtId));
                        } else {

                        }


                    }
                });





            }
        });
    }
    private void showVehcileDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog vehicleTypeDilaog = new Dialog(SeizeActivity.this, R.style.dialog_theme);
                vehicleTypeDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                vehicleTypeDilaog.setCancelable(true);
                vehicleTypeDilaog.setContentView(R.layout.spinner_dialog);
                vehicleTypeDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
                TextView dialog_title = vehicleTypeDilaog.findViewById(R.id.dialog_title);
                dialog_title.setText("Select Vehile Type");
                ListView list = vehicleTypeDilaog.findViewById(R.id.seize_cat_list);
                VehicleTypeAdapter vehicleTypeAdapter= new VehicleTypeAdapter(SeizeActivity.this, vehicletypeArrayId, vehicletypeArrayName);
                list.setAdapter(vehicleTypeAdapter);
                vehicleTypeDilaog.show();
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        vehicleTypeDilaog.dismiss();
                        vehicle_type_et.setText(""+vehicletypeArrayName.get(i));
                        vehicletypeName = vehicletypeArrayName.get(i);
                        Log.e("vehicletypeName", vehicletypeName);
                        if (!vehicletypeName.equals("")){
                            vehicle_type_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text));
                            vehicle_type_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                            mMap.put("vehicletype", createPartFromString(vehicletypeName));
                            Log.e( "vehicletypeName: ", vehicletypeName);
                        } else {
                        }


                    }
                });
            }
        });
    }
    private void loadSeizeCategories() {
        seizeArrayId.clear();
        seizeArrayName.clear();
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getSeizeCatData();
        Log.e(TAG, "loadSeizeCatSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String seize_id = map.get(DbConstants.SEIZ_ID);
            String seize_name = map.get(DbConstants.SEIZ_NAME);
            seizeArrayId.add(seize_id);
            seizeArrayName.add(seize_name);
        }
        Log.e(TAG, "seizeArrayId: "+seizeArrayId );
        Log.e(TAG, "seizeArrayName: "+seizeArrayName );
    }
    private void loadEngine() {
        engineTypeArrayId.add("1");
        engineTypeArrayName.add("petrol");
        engineTypeArrayId.add("2");
        engineTypeArrayName.add("CNG");
        engineTypeArrayId.add("3");
        engineTypeArrayName.add("hybrid");
        engineTypeArrayId.add("4");
        engineTypeArrayName.add("diesel");
    }
    private void loadWeehls() {
        wheelsArrayId.clear();
        wheelsArrayName.clear();
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getWheels();
        Log.e(TAG, "loadWheelsSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String model_wheel_id = map.get(DbConstants.VEHICLE_WHEEL_ID);
            String model_wheel_name = map.get(DbConstants.VEHICLE_WHEEL_NAME);
            wheelsArrayId.add(model_wheel_id);
            wheelsArrayName.add(model_wheel_name);
        }
        Log.e(TAG, "wheelsArrayId: "+wheelsArrayId );
        Log.e(TAG, "wheelsArrayName: "+wheelsArrayName );
    }
    private void loadAssembely() {
        assembArrayId.add("1");
        assembArrayName.add("imported");
        assembArrayId.add("2");
        assembArrayName.add("local");
    }
    private void loadTransmission() {
        transArrayId.add("1");
        transArrayName.add("Mannual");
        transArrayId.add("2");
        transArrayName.add("Automatic");
    }
    private void loadColor() {
        colorArrayId.clear();
        colorArrayName.clear();
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getColorData();
        Log.e(TAG, "loadColorSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String model_color_id = map.get(DbConstants.VEHICLE_COLOR_ID);
            String model_color_name = map.get(DbConstants.VEHICLE_COLOR_NAME);
            colorArrayId.add(model_color_id);
            colorArrayName.add(model_color_name);
        }
        Log.e(TAG, "colorArrayId: "+colorArrayId );
        Log.e(TAG, "colorArrayName: "+colorArrayName );
    }
    private void loadVehicletype(){
        vehicletypeArrayId.add("1");
        vehicletypeArrayName.add("private");
        vehicletypeArrayId.add("2");
        vehicletypeArrayName.add("commercial");
    }
    private void loadModelYear() {
        modelYearArrayId.clear();
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getModelYearData();
        Log.e(TAG, "loadModelYearSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String model_year_id = map.get(DbConstants.MODEL_YEAR_ID);
            String model_year_name = map.get(DbConstants.MODEL_YEAR_NAME);
            modelYearArrayId.add(model_year_id);
            modelYearArrayName.add(model_year_name);
        }
        //Log.e(TAG, "modelArrayId: "+modelArrayId );
        //Log.e(TAG, "modelArrayName: "+modelArrayName );
    }
    private void loadModel(String parent_id) {
        modelList.clear();
        model_et.setText("");
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getSpecificModelData(parent_id);
        Log.e(TAG, "loadModelSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String model_id = map.get(DbConstants.MODEL_ID);
            String model_name = map.get(DbConstants.MODEL_NAME);
            modelList.add(new ModelData(model_id, model_name));
        }
        Log.e(TAG, "modelList: "+modelList.toString() );
    }
    private void loadMake() {
        makeList.clear();
        ArrayList<HashMap<String, String>> mapArrayList =  mDbHelper.getMakeData();
        Log.e(TAG, "loadMakeSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String make_id = map.get(DbConstants.MAKE_ID);
            String make_name = map.get(DbConstants.MAKE_NAME);
            makeList.add(new MakeData(make_id, make_name));
        }
        Log.e(TAG, "loadMakeId: "+makeList.toString() );

    }
    private void loadDistrict() {
        districtList.clear();

        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getDistrictData();
        Log.e(TAG, "loadDistrictSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String district_id = map.get(DbConstants.DISTRICT_ID);
            String district_name = map.get(DbConstants.DISTRICT_NAME);
            districtList.add(new DistrictData(district_id, district_name));
        }
        Log.e(TAG, "districtList: "+districtList.toString() );

    }
    private void loadBodyBuild() {
        bodyBuildArrayId.clear();
        bodyBuildArrayName.clear();
        ArrayList<HashMap<String, String>> mapArrayList = new ArrayList<>();
        mapArrayList = mDbHelper.getBodyBuild();
        Log.e(TAG, "loadDistrictSize: "+mapArrayList.size() );
        for (int i = 0; i < mapArrayList.size(); i++) {
            HashMap<String, String> map = mapArrayList.get(i);
            String body_id = map.get(DbConstants.VEHICLE_BODY_ID);
            String body_name = map.get(DbConstants.VEHICLE_BODY_NAME);
            bodyBuildArrayId.add(body_id);
            bodyBuildArrayName.add(body_name);
        }
        Log.e(TAG, "bodyBuildArrayId: "+bodyBuildArrayId );
        Log.e(TAG, "bodyBuildArrayName: "+bodyBuildArrayName );

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
    @NonNull
    private RequestBody createPartFromString(String val) {
        return RequestBody.create(okhttp3.MultipartBody.FORM,  val);
    }
    private class GenericTextWatcher implements TextWatcher{

        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        public void afterTextChanged(Editable editable) {
            switch(view.getId()){
                case R.id.seize_form_no:
                    if (seize_form_no.getText().length()<4){
                        seize_form_no_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        seize_form_no.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                        seize_form_no.setError("Enter 4 digits");
                    }else {
                        seize_form_no_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        seize_form_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.driver_name_et:
                    if (!driver_name_et.getText().toString().isEmpty()){
                        driver_name_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        driver_name_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.driver_cnic_et:
                    if (driver_cnic_et.getText().length()<13){
                        driver_cnic_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        driver_cnic_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                        driver_cnic_et.setError("Enter 13 digits");
                    }else {
                        driver_cnic_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        driver_cnic_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.driver_mobile_et:
                    if (driver_mobile_et.getText().length()<11){
                        driver_mobile_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        driver_mobile_et.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                        driver_mobile_et.setError("Enter 11 digits");
                    }else {
                        driver_mobile_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        driver_mobile_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.owner_name_et:
                    if (!owner_name_et.getText().toString().isEmpty()){
                        owner_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        owner_name_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;
                case R.id.mobile_squard_no:
                    if (!mobile_squard_no.getText().toString().isEmpty()){
                        squad_no_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        mobile_squard_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.engine_capicity_et:
                    if (!engine_capicity_et.getText().toString().isEmpty()){
                        engine_capicity_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        engine_capicity_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.mileage_et:
                    if (!mileage_et.getText().toString().isEmpty()){
                        mileage_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        mileage_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.description_et:
                    if (!description_et.getText().toString().isEmpty()){
                        description_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        description_et.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.vehicle_chasis_no:
                    if (!vehicle_chasis_no.getText().toString().isEmpty()){
                        chasis_no_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        vehicle_chasis_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;

                case R.id.vehicle_engine_no:
                    if (!vehicle_engine_no.getText().toString().isEmpty()){
                        engine_no_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        vehicle_engine_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;
                case R.id.vehicle_reg_no:
                    if (!vehicle_reg_no.getText().toString().isEmpty()){
                        vehicle_reg_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        vehicle_reg_no.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                    break;
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

        if(mLocation == null){
            startLocationUpdates();
        }
        if (mLocation != null) {

            currentLatitude = mLocation.getLatitude();
            currentLongitude = mLocation.getLongitude();
            map.put(SEIZE_CURRENT_LATITUDE, String.valueOf(currentLatitude));
            map.put(SEIZE_CURRENT_LONGITUDE, String.valueOf(currentLongitude));
            mMap.put("seizedlocationlat", createPartFromString(String.valueOf(currentLatitude)));
            mMap.put("seizedlocationlong", createPartFromString(String.valueOf(currentLongitude)));

        } else {
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
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
        Log.e(TAG, "longitude: "+location.getLongitude() );
        Log.e(TAG, "latitude: "+location.getLatitude() );

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        map.put(SEIZE_CURRENT_LATITUDE, String.valueOf(currentLatitude));
        map.put(SEIZE_CURRENT_LONGITUDE, String.valueOf(currentLongitude));
        mMap.put("seizedlocationlat", createPartFromString(String.valueOf(currentLatitude)));
        mMap.put("seizedlocationlong", createPartFromString(String.valueOf(currentLongitude)));

    }

    private boolean checkLocation() {
        if(!isLocationEnabled()){
            showAlert();
        }

        return isLocationEnabled();
    }

    private void showAlert() {
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
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
                                status.startResolutionForResult(SeizeActivity.this, 1000);

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
}

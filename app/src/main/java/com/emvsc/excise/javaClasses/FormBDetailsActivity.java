package com.emvsc.excise.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.FormbAccessoriesAdapter;
import com.emvsc.excise.modelClasses.WhmSeizeVehicleData;
import com.emvsc.excise.utilClasses.NonScrollListView;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.emvsc.excise.utilClasses.Config.BaseUrl;

public class FormBDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "FormBDetails.class";
    private Toolbar mToolbar;
    private SliderLayout mDemoSlider;
    private TextView expand1, expand2,expand3;
    //seize information
    private TextView whm_seize_address,whm_seize_time,whm_reg_district,whm_seize_date,whm_squadno,whm_inpsector_name,whm_location,whm_seize_cat, whm_formno, whm_eto_approved_time, whm_eto_approved_date;
    //vehicle information
    private TextView whm_chasisno,whm_engine_no,whm_reg_no,whm_make,whm_model,whm_model_year,whm_vehicle_type,whm_body_build,whm_color,whm_transmission,whm_assembely,whm_wheels,
            whm_engine_type,whm_capacity,whm_mileage,whm_description;
    //accessories
    private NonScrollListView whm_accessories;
    //driver and owner details
    private TextView whm_driver_name,whm_driver_cnic,whm_driver_mob,whm_driver_address,whm_owner_name,whm_owner_cnic,whm_owner_mob;
    private ExpandableLinearLayout expandableLayout,expandableLayout2,expandableLayout3;
    private Button receive;
    String vehicle_id;
    TextSliderView textSliderView;
    List<WhmSeizeVehicleData.VehicleImage> object;
    List<WhmSeizeVehicleData.VehicleAccessory> accessList;
    FormbAccessoriesAdapter mFormbAccessoriesAdapter;
    Bundle args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bdetails);
        setUi();

    }
    private void setUi() {
        Intent intent = getIntent();
        args = intent.getBundleExtra("BUNDLE");
        object = (ArrayList<WhmSeizeVehicleData.VehicleImage>) args.getSerializable("vehicle_images");
        accessList = (ArrayList<WhmSeizeVehicleData.VehicleAccessory>) args.getSerializable("accessories_list");
        setUpSlider(object);
        expand1 = findViewById(R.id.expand1);
        expand2 = findViewById(R.id.expand2);
        expand3 = findViewById(R.id.expand3);
        receive = findViewById(R.id.receive);

        expand1.setOnClickListener(this);
        expand2.setOnClickListener(this);
        expand3.setOnClickListener(this);
        receive.setOnClickListener(this);
        expandableLayout = findViewById(R.id.expandableLayout);
        expandableLayout2 = findViewById(R.id.expandableLayout2);
        expandableLayout3 = findViewById(R.id.expandableLayout3);

        whm_reg_district = findViewById(R.id.whm_reg_district);
        whm_reg_district.setText(args.get("reg_district_name").toString());
        whm_seize_address = findViewById(R.id.whm_seize_address);
        whm_seize_address.setText("Seized Address : "+args.get("seize_address").toString());
        whm_seize_time = findViewById(R.id.whm_seize_time);
        whm_seize_time.setText(args.get("seize_time").toString());
        whm_seize_date = findViewById(R.id.whm_seize_date);
        whm_seize_date.setText(args.get("seize_date").toString());
        whm_squadno = findViewById(R.id.whm_squadno);
        whm_squadno.setText("Mobile Squad No."+args.get("squad_no").toString());
        whm_inpsector_name = findViewById(R.id.whm_inpsector_name);
        whm_inpsector_name.setText("Inspector Name : "+args.get("inspector_name").toString());
        whm_location = findViewById(R.id.whm_location);
        whm_seize_cat = findViewById(R.id.whm_seize_cat);
        whm_seize_cat.setText("Seize Category : "+args.get("seize_type").toString());
        whm_formno = findViewById(R.id.whm_formno);
        whm_formno.setText("Form A #"+args.getString("form_no"));
        whm_eto_approved_time = findViewById(R.id.whm_eto_approved_time);
        whm_eto_approved_time.setText(args.get("approved_time").toString());
        whm_eto_approved_date = findViewById(R.id.whm_eto_approved_date);
        whm_eto_approved_date.setText(args.get("approved_date").toString());
        //whm_eto_time.setText("11-02-2018");

        whm_chasisno = findViewById(R.id.whm_chasisno);
        whm_chasisno.setText(args.getString("chasis_no"));
        whm_engine_no = findViewById(R.id.whm_engine_no);
        whm_engine_no.setText(args.getString("engine_no"));
        whm_reg_no = findViewById(R.id.whm_reg_no);
        whm_reg_no.setText(args.getString("reg_no"));
        Log.e(TAG, "reg_no: "+args.getString("reg_no") );
        whm_make = findViewById(R.id.whm_make);
        whm_make.setText(args.getString("make_name"));
        whm_model = findViewById(R.id.whm_model);
        whm_model.setText(args.getString("model_name"));
        whm_model_year = findViewById(R.id.whm_model_year);
        whm_model_year.setText(args.getString("model_year"));
        whm_vehicle_type = findViewById(R.id.whm_vehicle_type);
        whm_vehicle_type.setText(args.getString("vehicle_type"));
        whm_body_build = findViewById(R.id.whm_body_build);
        whm_body_build.setText(args.getString("body_build"));
        whm_color = findViewById(R.id.whm_color);
        whm_color.setText(args.getString("color"));
        whm_transmission = findViewById(R.id.whm_transmission);
        whm_transmission.setText(args.getString("transmission"));
        whm_assembely = findViewById(R.id.whm_assembely);
        whm_assembely.setText(args.getString("assembely"));
        whm_wheels = findViewById(R.id.whm_wheels);
        whm_wheels.setText(args.getString("wheels"));
        whm_engine_type = findViewById(R.id.whm_engine_type);
        whm_engine_type.setText(args.getString("engine_type"));
        whm_capacity = findViewById(R.id.whm_capacity);
        whm_capacity.setText(args.getString("engine_capicity"));
        whm_mileage = findViewById(R.id.whm_mileage);
        whm_mileage.setText(args.getString("mileage"));
        whm_description = findViewById(R.id.whm_description);
        whm_description.setText(args.getString("description"));

        whm_accessories = findViewById(R.id.whm_accessories);
        mFormbAccessoriesAdapter = new FormbAccessoriesAdapter(this, accessList);
        whm_accessories.setAdapter(mFormbAccessoriesAdapter);
        Log.e(TAG, "accessList size: "+accessList.size() );

        whm_driver_name = findViewById(R.id.whm_driver_name);
        whm_driver_name.setText(args.get("driver_name").toString());
        whm_driver_cnic = findViewById(R.id.whm_driver_cnic);
        whm_driver_cnic.setText(args.get("driver_cnic").toString());
        whm_driver_mob = findViewById(R.id.whm_driver_mob);
        whm_driver_mob.setText(args.get("driver_mob").toString());
        whm_driver_address = findViewById(R.id.whm_driver_address);
        whm_driver_address.setText(args.get("driver_address").toString());
        whm_owner_name = findViewById(R.id.whm_owner_name);
        whm_owner_name.setText(args.get("owner_name").toString());
        whm_owner_cnic = findViewById(R.id.whm_owner_cnic);
        whm_owner_cnic.setText(args.get("owner_cnic").toString());
        whm_owner_mob = findViewById(R.id.whm_owner_mob);
        whm_owner_mob.setText(args.get("owner_mob").toString());


        displayAddress(Double.valueOf(args.get("lat").toString()), Double.valueOf(args.get("lng").toString()));

    }

    private void setUpSlider(List<WhmSeizeVehicleData.VehicleImage> arrayList) {
        SliderLayout mDemoSlider = findViewById(R.id.slide);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        for (int i = 0; i < arrayList.size(); i++) {
            textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image("http://175.107.63.24/wms/uploads/"+arrayList.get(i).getImagepath())
                    .description(i+1+"/"+arrayList.size())
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",arrayList.get(i).getImagepath());
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterInside);
            mDemoSlider.addSlider(textSliderView);
            Log.e(TAG, "name: "+arrayList.get(i).getImagepath() );
            Log.e(TAG, "setUpSlider: "+i );
        }
        Log.e(TAG, "setUpSlider: "+url_maps.toString());
        for(String name : url_maps.keySet()){

        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.stopAutoCycle();
        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("Slider Demo", "Page Changed: " + (position+1));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                whm_location.setText("Physical Address: "+knownName);
            }else {
                whm_location.setText("Physical Address: "+address);
            }
        } catch (IOException e) {
            Log.e("IOException", ""+e.toString());
        }


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.expand1:
                expandLayout1();
                break;
            case R.id.expand2:
                expandLayout2();
                break;
            case R.id.expand3:
                expandLayout3();
                break;
            case R.id.receive:
                if(isNetworkAvailable()){
                    goToFormB();

                }else {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
                
                break;
        }
        
    }

    private void goToFormB() {
        Intent intent = new Intent(FormBDetailsActivity.this, FormBActivity.class);
        vehicle_id = args.getString("vehicle_id");
        intent.putExtra("vehicle_id", vehicle_id);
       startActivity(intent);
    }

    private void expandLayout3() {
        expandableLayout3.toggle();

    }

    private void expandLayout2() {
        expandableLayout2.toggle();
    }

    private void expandLayout1() {
        expandableLayout.toggle();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            isAvailable = true;

        }
        return isAvailable;



    }
}

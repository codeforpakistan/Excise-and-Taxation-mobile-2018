package com.emvsc.excise.javaClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.emvsc.excise.R;
import com.emvsc.excise.dbClasses.DbConstants;
import com.emvsc.excise.dbClasses.DbHelper;
import com.emvsc.excise.modelClasses.ModelData;

import java.util.ArrayList;
import java.util.HashMap;

import static com.emvsc.excise.utilClasses.Prefences.LOGIN_PREF;
import static com.emvsc.excise.utilClasses.Prefences.TOKEN_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_DISTRICT_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_ID;

import static com.emvsc.excise.utilClasses.Prefences.USER_MOBILE;
import static com.emvsc.excise.utilClasses.Prefences.USER_NAME;
import static com.emvsc.excise.utilClasses.Prefences.USER_PREF;


public class InspectorHomeActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences loginSharedPreferences, userSharedPreferences;
    private SharedPreferences.Editor userEditor, loginEditor;
    private Button seizeBtn, vehicleBtn;
    private Toolbar toolbar;
    private TextView inspectorNameTv;
    private TextView inspector_location;
    private String tokenId;
    private String id;
    private String name;
    private String district_id;
    private String district_name;
    private String mobile;
    DbHelper mDbHelper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUI();
    }
    private void setUI() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginSharedPreferences = getApplicationContext().getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        userSharedPreferences = getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        tokenId = userSharedPreferences.getString(TOKEN_ID, "No Data");
        id = userSharedPreferences.getString(USER_ID, "No Data");
        name = userSharedPreferences.getString(USER_NAME, "No Data");
        mobile = userSharedPreferences.getString(USER_MOBILE, "No Data");
        district_id = userSharedPreferences.getString(USER_DISTRICT_ID, "No Data");
        if (district_id != "No Data"){
            ArrayList<HashMap<String, String>> list = mDbHelper.getSpecificDistrictData(district_id);
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, String> map = list.get(i);
                String district_id = map.get(DbConstants.DISTRICT_ID);
                district_name = map.get(DbConstants.DISTRICT_NAME);


            }
        }

        Log.e("tokenId", tokenId);
        Log.e("id", id);
        Log.e("name", name);
        Log.e("mobile", mobile);
        Log.e("district_id", district_id);
        Log.e("district_name", district_name);

        seizeBtn = findViewById(R.id.seize_veh_btn);
        vehicleBtn = findViewById(R.id.vehicle_history_btn);
        inspectorNameTv = findViewById(R.id.inspector_name_tv);
        inspector_location = findViewById(R.id.inspector_location);
        inspectorNameTv.setText(name);
        inspector_location.setText(district_name);

        seizeBtn.setOnClickListener(this);
        vehicleBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.seize_veh_btn:
                Intent seizeIntent = new Intent(InspectorHomeActivity.this, SeizeActivity.class);
                startActivity(seizeIntent);
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case R.id.vehicle_history_btn:
                Intent historyIntent = new Intent(InspectorHomeActivity.this, VehicleHistoryActivity.class);
                startActivity(historyIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to logout")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            goToLogin();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToLogin() {
        loginEditor = loginSharedPreferences.edit();
        userEditor = userSharedPreferences.edit();
        loginEditor.clear();
        userEditor.clear();
        loginEditor.commit();
        userEditor.commit();
        Intent intent = new Intent(InspectorHomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}

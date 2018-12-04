package com.emvsc.excise.javaClasses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.emvsc.excise.R;
import com.emvsc.excise.interfaceClasses.LoginApi;
import com.emvsc.excise.modelClasses.Login;
import com.emvsc.excise.utilClasses.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.emvsc.excise.utilClasses.Prefences.LOGIN_ID;
import static com.emvsc.excise.utilClasses.Prefences.LOGIN_PREF;
import static com.emvsc.excise.utilClasses.Prefences.SPECIAL_SQUAD;
import static com.emvsc.excise.utilClasses.Prefences.TOKEN_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_CNIC;
import static com.emvsc.excise.utilClasses.Prefences.USER_DISTRICT_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_ID;
import static com.emvsc.excise.utilClasses.Prefences.USER_MOBILE;
import static com.emvsc.excise.utilClasses.Prefences.USER_NAME;
import static com.emvsc.excise.utilClasses.Prefences.USER_PREF;
import static com.emvsc.excise.utilClasses.Prefences.USER_ROLE_ID;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText cnicEditext, passwordEditext;
    private HashMap<String, String> mMap = new HashMap<>();
    private SharedPreferences userSharedPreferences;
    private SharedPreferences.Editor userEditor;
    private SharedPreferences loginSharedPreferences;
    private SharedPreferences.Editor loginEditor;
    private List<Login.Logindata> mArrayList = new ArrayList<>();
    private Login mPostData;
    private CheckBox mCheckBox;
    LinearLayout login_layout;
    Snackbar mSnackbar;
    String userId;
    String tokenId;
    String userName;
    String userDistrict;
    String userMobile;
    String specialSquad;
    String roleId;

    AwesomeProgressDialog mAwesomeProgressDialog;
    TextView cnic_label;
    String cnicString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUi();
    }

    private void setUi() {
        //initializing login share prefences
        userSharedPreferences = getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        loginSharedPreferences = getApplicationContext().getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);

        //getting user id from login prefences
        String id = loginSharedPreferences.getString(LOGIN_ID, "No Data");
        String user_roleid = userSharedPreferences.getString(USER_ROLE_ID, "No Data");
        if (!id.equals("No Data")){
            if (user_roleid.equals("2")){
                Intent homeIntent = new Intent(LoginActivity.this, InspectorHomeActivity.class);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }else if (user_roleid.equals("3")){
                Intent whmIntent = new Intent(LoginActivity.this, WhmActivity.class);
                startActivity(whmIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }

        }else {
            //initi views
            login_layout = findViewById(R.id.login_layout);
            loginBtn = findViewById(R.id.login_btn);
            cnicEditext = findViewById(R.id.cnic_edit_text);
            passwordEditext = findViewById(R.id.password_edit_text);
            mCheckBox = findViewById(R.id.remember_ch);
            cnic_label = findViewById(R.id.cnic_label);
            mAwesomeProgressDialog = new AwesomeProgressDialog(this);


            //set on click listerner
            loginBtn.setOnClickListener(this);

            cnicEditext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String cnic = cnicEditext.getText().toString();
                    if (cnic.length() < 13){
                        cnic_label.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        cnicEditext.setBackground(getResources().getDrawable(R.drawable.error_layout2));
                        cnicEditext.setError("Enter 13 digits");
                    }else {
                        cnic_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        cnicEditext.setBackground(getResources().getDrawable(R.drawable.custom_edit_text2));
                    }
                }
            });


        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.login_btn:
                cnicString = cnicEditext.getText().toString();
                String passwordString = passwordEditext.getText().toString();
                if (TextUtils.isEmpty(cnicString)){
                    cnicEditext.setError("Enter CNIC No");
                    cnicEditext.requestFocus();
                }else if (TextUtils.isEmpty(passwordString)){
                    passwordEditext.setError("Enter Password");
                    passwordEditext.requestFocus();
                }else{
                    if (isNetworkAvailable()){
                        mMap.put("usercnic", cnicString);
                        mMap.put("password", passwordString);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                postData();
                                mAwesomeProgressDialog.setTitle("Authenticating")
                                        .setMessage("Please Wait")
                                        .setColoredCircle(R.color.colorPrimaryDark)
                                        .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                        .setCancelable(false)
                                        .show();

                            }
                        });
                    }else {
                       mSnackbar = Snackbar.make(login_layout, "No Internet Connection", Snackbar.LENGTH_SHORT);
                       View mSnackview = mSnackbar.getView();
                        mSnackview.setBackgroundColor(getResources().getColor(R.color.red));
                        TextView textView = (TextView) mSnackview.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(getResources().getColor(R.color.white));
                        mSnackbar.setActionTextColor(getResources().getColor(R.color.white));
                        mSnackbar.show();

                        mSnackbar.setAction("Check Setting", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                            }
                        });
                    }
                    }

                break;

        }
    }


    private void postData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginApi loginApi = retrofit.create(LoginApi.class);
        Call<Login> call = loginApi.savePost(mMap);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                int success = response.body().getSuccess();
                Log.e("success", ""+success);
                if (success == 1){
                    roleId = response.body().getRole();
                    userDistrict = response.body().getDistrictId();
                    mPostData = response.body();
                    mArrayList = mPostData.getLogindata();
                    tokenId = mArrayList.get(0).getApiToken();
                    userId = mArrayList.get(0).getUserid();
                    userName = mArrayList.get(0).getUsername();
                    userMobile = mArrayList.get(0).getUsermobile();
                    specialSquad = mArrayList.get(0).getSpecial_squad();
                    Log.e("tokenId", tokenId);
                    Log.e("userId", userId);
                    Log.e("userName", userName);
                    Log.e("userDistrict", userDistrict);
                    Log.e("userMobile", userMobile);
                    Log.e("specialSquad", specialSquad);
                    Log.e("roleId", roleId);
                    saveData(tokenId, userId, userName, userDistrict, userMobile, specialSquad, roleId);
                    if (mCheckBox.isChecked()){
                        loginEditor = loginSharedPreferences.edit();
                        loginEditor.putString(LOGIN_ID, userId);
                        loginEditor.putString(USER_CNIC, cnicString);
                        loginEditor.commit();
                    }


                }else if (success == 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AwesomeErrorDialog(LoginActivity.this)
                                    .setTitle("WhOops")
                                    .setMessage("Cnic or Password is incorrect")
                                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                    .setCancelable(false)

                                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                    .setButtonText("Try Again")
                                    .setErrorButtonClick(new Closure() {
                                        @Override
                                        public void exec() {
                                            // click
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {


                                                }
                                            });
                                        }
                                    })
                                .show();
                        }
                    });
                    mAwesomeProgressDialog.hide();
                }else if (success == 2){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AwesomeErrorDialog(LoginActivity.this)
                                    .setTitle("WhOops")
                                    .setMessage("Account Has Been Disabled")
                                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                    .setCancelable(false)
                                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                    .setButtonText("Try Again")
                                    .setErrorButtonClick(new Closure() {
                                        @Override
                                        public void exec() {
                                            // click
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {


                                                }
                                            });
                                        }
                                    });
                        }
                    });
                    mAwesomeProgressDialog.hide();
                }


            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("Error", t.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AwesomeErrorDialog(LoginActivity.this)
                                .setTitle("WhOops")
                                .setMessage("Some Thing Went Wrong")
                                .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                .setCancelable(false)

                                .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                .setButtonText("Try Again")
                                .setErrorButtonClick(new Closure() {
                                    @Override
                                    public void exec() {
                                        // click
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {


                                            }
                                        });
                                    }
                                })
                                .show();
                    }
                });
                mAwesomeProgressDialog.hide();

            }
        });
    }

    private void saveData(String tokenId, String userId, String userName, String userDistrict, String userMobile, String specialSquad, final String roleid) {
        userEditor = userSharedPreferences.edit();
        userEditor.putString(TOKEN_ID, tokenId);
        userEditor.putString(USER_ID, userId);
        userEditor.putString(USER_NAME, userName);
        userEditor.putString(USER_DISTRICT_ID, userDistrict);
        userEditor.putString(USER_MOBILE, userMobile);
        userEditor.putString(SPECIAL_SQUAD, specialSquad);
        userEditor.putString(USER_ROLE_ID, roleId);
        userEditor.commit();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (roleid.equals("2")){
                    mAwesomeProgressDialog.hide();
                    Intent intent = new Intent(LoginActivity.this, InspectorHomeActivity.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }else if (roleid.equals("3")){
                    mAwesomeProgressDialog.hide();
                    Intent intent = new Intent(LoginActivity.this, WhmActivity.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }else {

                }

            }
        });


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

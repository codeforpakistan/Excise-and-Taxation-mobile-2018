package com.emvsc.excise.javaClasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.emvsc.excise.R;
import com.emvsc.excise.adapterClasses.SeizeAprovedAdapter;
import com.emvsc.excise.fragmentClasses.OneFragment;
import com.emvsc.excise.fragmentClasses.TwoFragment;
import com.emvsc.excise.modelClasses.CustomMessageEvent;
import com.emvsc.excise.modelClasses.CustomMessageEvent2;
import com.emvsc.excise.modelClasses.RefreshTab1;
import com.emvsc.excise.modelClasses.RefreshTab2;
import com.emvsc.excise.utilClasses.GlobalApp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static com.emvsc.excise.utilClasses.Prefences.LOGIN_PREF;
import static com.emvsc.excise.utilClasses.Prefences.USER_PREF;

public class WhmActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    public ViewPager viewPager;
    private Toolbar mToolbar;
    ViewPagerAdapter adapter;
    LinearLayout linearLayoutOne, linearLayout2;
    private SharedPreferences loginSharedPreferences, userSharedPreferences;
    private SharedPreferences.Editor userEditor, loginEditor;
    FloatingActionButton mFloatingActionButton;
    SearchView searchView = null;
    OneFragment mOneFragment;
    TwoFragment mTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whm);
        setUi();
        EventBus.getDefault().register(this);
    }

    private void setUi() {
        loginSharedPreferences = getApplicationContext().getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        userSharedPreferences = getApplicationContext().getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);



        mToolbar = findViewById(R.id.whm_toolbar);
        setSupportActionBar(mToolbar);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_tab, null, false);

        linearLayoutOne = (LinearLayout) headerView.findViewById(R.id.ll);
        linearLayout2 = (LinearLayout) headerView.findViewById(R.id.ll2);

        tabLayout.getTabAt(0).setCustomView(linearLayoutOne);
        tabLayout.getTabAt(1).setCustomView(linearLayout2);



    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ETO Reported");
        adapter.addFrag(new TwoFragment(), "Inspector Reported");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            // Causes adapter to reload all Fragments when
            // notifyDataSetChanged is called
            notifyDataSetChanged();
            return POSITION_NONE;
        }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.whm_home_menu, menu);
        MenuItem item = menu.findItem(R.id.whm_home_search);
        MenuItem item_refresh = menu.findItem(R.id.refresh_btn);
        searchView = (SearchView) item.getActionView();
        item_refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RefreshTab1 refreshTab1 = new RefreshTab1();
                        RefreshTab2 refreshTab2 = new RefreshTab2();
                        refreshTab1.setRefreshStatus(1);
                        refreshTab2.setRefreshStatus(1);
                        EventBus.getDefault().post(refreshTab1);
                        EventBus.getDefault().post(refreshTab2);
                    }
                });
                return true;
            }
        });


        changeSearchViewTextColor(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                    // this is your adapter that will be filtered
                    PagerAdapter pagerAdapter = (PagerAdapter) viewPager
                            .getAdapter();
                    for (int i = 0; i < pagerAdapter.getCount(); i++) {

                        Fragment viewPagerFragment = (Fragment) viewPager
                                .getAdapter().instantiateItem(viewPager, i);
                        if (viewPagerFragment != null
                                && viewPagerFragment.isAdded()) {

                            if (viewPagerFragment instanceof OneFragment) {
                                mOneFragment = (OneFragment) viewPagerFragment;
                                if (mOneFragment != null) {
                                    mOneFragment.beginSearch(query);
                                    Log.e("onQueryTextChange", query );
                                }
                            } else if (viewPagerFragment instanceof TwoFragment) {
                                mTwoFragment = (TwoFragment) viewPagerFragment;
                                if (mTwoFragment != null) {
                                   mTwoFragment.beginSearch(query);
                                }
                            }
                        }
                    }

                    return true;

            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.wm_logout) {
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
        Intent intent = new Intent(WhmActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Subscribe
    public void OnEvent1(CustomMessageEvent event){
        TextView textView = linearLayoutOne.findViewById(R.id.tvtab1);
        textView.setText(event.getTotalSeize());
        Log.e("shah", "setUi: "+event.getTotalSeize() );
        }

    @Subscribe
    public void OnEvent2(CustomMessageEvent2 event){

        TextView textView2 = linearLayout2.findViewById(R.id.tvtab2);
        textView2.setText(event.getTotalInspectorReports());
        Log.e("shah", "setUi: "+event.getTotalInspectorReports() );

    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}

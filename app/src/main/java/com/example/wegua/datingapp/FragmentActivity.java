package com.example.wegua.datingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends AppCompatActivity {

    private static final String TAG = FragmentActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    ProfileFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Intent intent=getIntent();

        Bundle bundle = new Bundle();
        bundle.putString("name",intent.getStringExtra("name"));
        bundle.putString("age",intent.getStringExtra("age"));
        bundle.putString("occuption",intent.getStringExtra("occuption"));
        bundle.putString("description",intent.getStringExtra("description"));
        fragment = new ProfileFragment();
        fragment.setArguments(bundle);

        // Adding Toolbar to Main screen
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Log.i(TAG, "onCreate()");
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(this.fragment, "Profile");
        adapter.addFrag(new MatchFragment(), "Matches");
        adapter.addFrag(new SettingFragment(), "Setting");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }


}

package com.example.ton.furnituretabapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("HOME");
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new QAPageI(), "MAIN1");
        adapter.addFragment(new QAPageII(), "MAIN2");
        adapter.addFragment(new QAPageIII(), "MAIN3");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            // in my case I get the support fragment manager, it should work with the native one too
            FragmentManager fragmentManager = getSupportFragmentManager();
            // this will clear the back stack and displays no animation on the screen
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            finish();
            return false;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 1001 && resultCode == RESULT_OK) {
                    ImageView picMainImg = findViewById(R.id.picMainImg);
                    Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMainImg);
                    VariableName.vaPicMainImg = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                }
            else if (requestCode == 1002 && resultCode == RESULT_OK){
                    ImageView picMCI = findViewById(R.id.picMC_I);
                    Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCI);
            }
    }
    @Override
    protected void onDestroy() {
        freeMemory();
        super.onDestroy();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}

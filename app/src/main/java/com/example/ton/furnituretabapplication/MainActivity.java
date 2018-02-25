package com.example.ton.furnituretabapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import resource.AsyncTaskLogin;

public class MainActivity extends AppCompatActivity {

    private Button loginBth;
    private EditText username,password;
    private String usernameTxt,passwordTxt;

    //Database
    SQLiteDatabase mDb;
    DatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loginBth = findViewById(R.id.loginBtn);
        username = findViewById(R.id.usernameEdt);
        password = findViewById(R.id.passwordEdt);
        loginBth.setOnClickListener(doLogin);
        loginBth.setEnabled(true);
        accessPermission();
        createDB();

        //check android varsion
        if (getAndroidVersion()<23) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alert")
                    .setMessage("Application not support Android Version.")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }
                    })
                    .show();
        }
        //check tablet or phone
        if (!checkIsTablet()){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alert")
                    .setMessage("Application support tablet.")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }
                    })
                    .show();
        }
        //check internet access
        if (!isNetworkConnected()){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alert")
                    .setMessage("Application need internet.")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            finish();
                        }
                    })
                    .show();
        }


    }

    private View.OnClickListener doLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            usernameTxt = username.getText().toString();
            passwordTxt = password.getText().toString();

            if (usernameTxt.matches("")){
                Toast.makeText(MainActivity.this,"Please enter Username !!",Toast.LENGTH_LONG).show();
            } else if (passwordTxt.matches("")){
                Toast.makeText(MainActivity.this,"Please enter Password !!",Toast.LENGTH_LONG).show();
            }else {
                loginBth.setEnabled(false);
                AsyncTaskLogin atlLogin = new AsyncTaskLogin(MainActivity.this,usernameTxt,passwordTxt,loginBth);
                atlLogin.execute();
            }
        }
    };

    private void createDB(){
        //SQLite
        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();
    }

    private void accessPermission(){
        //ขออนุญาติ
        Dexter.initialize(MainActivity.this);
        Dexter.checkPermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                List<PermissionGrantedResponse> permissionGrantedResponses = report.getGrantedPermissionResponses();

                for(PermissionGrantedResponse grantedResponse : permissionGrantedResponses)
                {
                    grantedResponse.getPermissionName();
                }

                report.areAllPermissionsGranted();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE
                ,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.ACCESS_NETWORK_STATE);

    }
    private int getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        Log.d("RELEASE",release);
        int sdkVersion = Build.VERSION.SDK_INT;
        return sdkVersion;
    }
    private boolean checkIsTablet() {
        boolean isTablet = false;
        Display display = MainActivity.this.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        float widthInches = metrics.widthPixels / metrics.xdpi;
        float heightInches = metrics.heightPixels / metrics.ydpi;
        double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
        if (diagonalInches >= 7.0) {
            isTablet = true;
        }

        return isTablet;
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
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

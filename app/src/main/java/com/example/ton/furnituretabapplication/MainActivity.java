package com.example.ton.furnituretabapplication;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
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
        loginBth = (Button)findViewById(R.id.loginBtn);
        username = (EditText)findViewById(R.id.usernameEdt);
        password = (EditText)findViewById(R.id.passwordEdt);
        loginBth.setOnClickListener(doLogin);
        accessPermission();
        createDB();

        //ทดสอบ webService
        //WebService webService = new WebService();
        //webService.getQASectionDetail();
        //ArrayList<String> qaTypeList = webService.getQaSectionType();
        //ArrayList<String> qaDescList = webService.getQaSectionDesc();
        //for(int i=0; i<qaTypeList.size(); i++){
        //    Log.i("QA TYPE"+(i+1),qaTypeList.get(i).toString() +" : "+qaDescList.get(i).toString() );
        //}

        //login = findViewById(R.id.loginBtn);
        //login.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent i = new Intent(MainActivity.this,SubActivity.class);
        //        startActivity(i);
        //    }
        //});

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
                AsyncTaskLogin atlLogin = new AsyncTaskLogin(MainActivity.this,usernameTxt,passwordTxt);
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
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);

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

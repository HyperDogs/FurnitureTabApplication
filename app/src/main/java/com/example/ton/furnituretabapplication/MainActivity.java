package com.example.ton.furnituretabapplication;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
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

        //new MaterialDialog.Builder()





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
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE);

    }
    private int getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return sdkVersion;
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

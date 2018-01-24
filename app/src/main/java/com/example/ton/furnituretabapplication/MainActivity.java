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

import Model.QADetailModel;
import Model.QASectionModel;
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

        //test webService
        //WebService webServiceQASection = new WebService();
        //webServiceQASection.getQASection();
        //ArrayList<String> qaSectionTypeList = webServiceQASection.getQaSectionType();
        //ArrayList<String> qaSectionDescList = webServiceQASection.getQaSectionDesc();
        //for(int i=0; i<qaSectionTypeList.size(); i++){
            //Log.i("QA SECTION"+(i+1),qaSectionTypeList.get(i).toString() +" : "+qaSectionDescList.get(i).toString() );
        //}
        //List<QASectionModel> qaSectionModelList = new ArrayList();
        //for(int i=0; i<qaSectionTypeList.size(); i++){
        //    QASectionModel qaSectionModel = new QASectionModel();
        //    qaSectionModel.setQaSectionType(qaSectionTypeList.get(i));
        //    qaSectionModel.setQaSectionDesc(qaSectionDescList.get(i));
        //    qaSectionModelList.add(qaSectionModel);
        //}


        //WebService webServiceQADetail = new WebService();
        //webServiceQADetail.getQADetail();
        //ArrayList<String> qaDetailSeqList = webServiceQADetail.getQaDetailSeq();
        //ArrayList<String> qaDetailTypeList = webServiceQADetail.getQaDetailType();
        //ArrayList<String> qaDetailDescList = webServiceQADetail.getQaDetailDesc();
        //ArrayList<String> qaDetailTxtUseList = webServiceQADetail.getQaDetailTxtUse();
        //for(int i=0; i<qaSectionTypeList.size(); i++){
        //    Log.i("QA SECTION",qaSectionTypeList.get(i).toString() +" : "+qaSectionDescList.get(i).toString() );
        //    for(int j=0; j<qaDetailSeqList.size(); j++){
        //        if(qaSectionTypeList.get(i).toString().equals(qaDetailTypeList.get(j).toString())){
        //            Log.i("QA DETAIL",qaDetailSeqList.get(j).toString() +" : "+qaDetailTypeList.get(j).toString()+" : "+qaDetailDescList.get(j).toString()+" : "+qaDetailTxtUseList.get(j).toString() );
        //        }
        //    }
        //}
        //for(int i=0; i<qaSectionModelList.size(); i++){
        //    List<QADetailModel> qaDetailModelList = new ArrayList();
        //    QASectionModel qaSectionModel = qaSectionModelList.get(i);
        //    for(int j=0; j<qaDetailTypeList.size(); j++){
        //        if(qaSectionModel.getQaSectionType().equals(qaDetailTypeList.get(j))){
        //            QADetailModel qaDetailModel = new QADetailModel();
        //            qaDetailModel.setQaDetailSeq(qaDetailSeqList.get(j));
        //            qaDetailModel.setQaDetailType(qaDetailTypeList.get(j));
        //            qaDetailModel.setQaDetailDesc(qaDetailDescList.get(j));
        //            qaDetailModel.setQaDetailTxtUse(qaDetailTxtUseList.get(j));
        //            qaDetailModelList.add(qaDetailModel);
        //        }
        //    }
        //    qaSectionModel.setQaDetailModelList(qaDetailModelList);
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
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE);

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

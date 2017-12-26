package com.example.ton.furnituretabapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button loginBth;
    EditText username,password;
    String usernameTxt,passwordTxt;
    //Database
    SQLiteDatabase mDb;
    //DatabaseHelper mHelper;
    //ArrayList<TBUserLoginModel> loginUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

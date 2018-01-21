package com.example.ton.furnituretabapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {
    private Button qaformbtn,listqabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        initView();

    }

    private void initView(){
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MENU");
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //Button
        qaformbtn = findViewById(R.id.qaformbtn);
        listqabtn = findViewById(R.id.listqabtn);
        qaformbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubActivity.this,Home.class);
                startActivity(i);
            }
        });
        listqabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubActivity.this,ListQA.class);
                startActivity(i);

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            new AlertDialog.Builder(SubActivity.this)
                    .setTitle("Logout")
                    .setMessage("Would you like to logout ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            Intent i = new Intent(SubActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();

                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
            return false;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(SubActivity.this)
                    .setTitle("Logout")
                    .setMessage("Would you like to logout ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            Intent i = new Intent(SubActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();

                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
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

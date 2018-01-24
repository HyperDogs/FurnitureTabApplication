package com.example.ton.furnituretabapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class Preview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //get Intent
        Intent getData = getIntent();
        String nameImage = getData.getStringExtra("NAME_IMAGE_VIEW");
        String path = VariableName.imageStorageFolder+nameImage;
        SubsamplingScaleImageView imageView = findViewById(R.id.previewImg);
        imageView.setImage(ImageSource.uri(path));
        Toast.makeText(Preview.this,"PATH :"+path,Toast.LENGTH_SHORT).show();
        Toast.makeText(Preview.this,"NAME :"+VariableName.vaPicMainImg,Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            // close this activity and return to preview activity (if there is any)
            System.gc();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

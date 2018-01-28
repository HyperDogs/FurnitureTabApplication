package com.example.ton.furnituretabapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Home extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    public static Context mContext;
    private File file;

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
        adapter.addFragment(new QAPageI(), "PAGE1");
        adapter.addFragment(new QAPageII(), "PAGE2");
        adapter.addFragment(new QAPageIII(), "PAGE3");
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
            //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            finish();
            return false;

        }

        if (item.getItemId() == R.id.mybutton) {
                // do something here
            Toast.makeText(Home.this,"Saved",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(Home.this,"REQCODE"+data,Toast.LENGTH_SHORT).show();
        Uri selectedImage = null;
        String fileName = null;
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            ImageView picMainImg = findViewById(R.id.picMainImg);
                    if (data != null){
                        selectedImage = data.getData();

                        Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                        File destFile = new File(VariableName.imageStorageFolder);
                            try {
                                fileName = copyFile(new File(uri.toString()), destFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMainImg);
                        VariableName.vaPicMainImg = fileName;
                        picMainImg.setTag("any");
                        //Toast.makeText(Home.this,"namepath"+VariableName.vaPicMainImg,Toast.LENGTH_SHORT).show();
                    } else {
                        Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMainImg);
                        VariableName.vaPicMainImg = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                        picMainImg.setTag("any");
                    }


                }
        else if (requestCode == 1002 && resultCode == RESULT_OK){
            ImageView picMCI = findViewById(R.id.picMC_I);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCI);
                VariableName.vaPicMC1 = fileName;
                picMCI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCI);
                VariableName.vaPicMC1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCI.setTag("any");
            }
            //Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCI);
        }
        else if (requestCode == 1003 && resultCode == RESULT_OK){
            ImageView picMCII = findViewById(R.id.picMC_II);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName =  copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCII);
                VariableName.vaPicMC2 = fileName;
                picMCII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCII);
                VariableName.vaPicMC2 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCII.setTag("any");
            }
        }
        else if (requestCode == 1004 && resultCode == RESULT_OK){
            ImageView picMCIII = findViewById(R.id.picMC_III);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCIII);
                VariableName.vaPicMC3 = fileName;
                picMCIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCIII);
                VariableName.vaPicMC3 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCIII.setTag("any");
            }
        }
        else if (requestCode == 1005 && resultCode == RESULT_OK){
            ImageView picMCIIII = findViewById(R.id.picMC_IIII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCIIII);
                VariableName.vaPicMC4 = fileName;
                picMCIIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCIIII);
                VariableName.vaPicMC4 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCIIII.setTag("any");
            }
        }
        else if (requestCode == 1006 && resultCode == RESULT_OK){
            ImageView picMCIV = findViewById(R.id.picMC_IV);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCIV);
                VariableName.vaPicMC5 = fileName;
                picMCIV.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCIV);
                VariableName.vaPicMC5 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCIV.setTag("any");
            }
        }
        else if (requestCode == 1007 && resultCode == RESULT_OK){
            ImageView picMCV = findViewById(R.id.picMC_V);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCV);
                VariableName.vaPicMC6 = fileName;
                picMCV.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCV);
                VariableName.vaPicMC6 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCV.setTag("any");
            }
        }
        else if (requestCode == 1008 && resultCode == RESULT_OK){
            ImageView picMCVI = findViewById(R.id.picMC_VI);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCVI);
                VariableName.vaPicMC7 = fileName;
                picMCVI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCVI);
                VariableName.vaPicMC7 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCVI.setTag("any");
            }
        }
        else if (requestCode == 1009 && resultCode == RESULT_OK){
            ImageView picMCVII = findViewById(R.id.picMC_VII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMCVII);
                VariableName.vaPicMC8 = fileName;
                picMCVII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMCVII);
                VariableName.vaPicMC8 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMCVII.setTag("any");
            }
        }
        else if (requestCode == 1010 && resultCode == RESULT_OK){
            ImageView picIBI = findViewById(R.id.picIB_I);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picIBI);
                VariableName.vaPicIB1 = fileName;
                picIBI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picIBI);
                VariableName.vaPicIB1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picIBI.setTag("any");
            }
        }
        else if (requestCode == 1011 && resultCode == RESULT_OK){
            ImageView picIBII = findViewById(R.id.picIB_II);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picIBII);
                VariableName.vaPicIB2 = fileName;
                picIBII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picIBII);
                VariableName.vaPicIB2 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picIBII.setTag("any");
            }
        }
        else if (requestCode == 1012 && resultCode == RESULT_OK){
            ImageView picIBIII = findViewById(R.id.picIB_III);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picIBIII);
                VariableName.vaPicIB3 = fileName;
                picIBIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picIBIII);
                VariableName.vaPicIB3 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picIBIII.setTag("any");
            }
        }
        else if (requestCode == 1013 && resultCode == RESULT_OK){
            ImageView picIBIIII = findViewById(R.id.picIB_IIII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picIBIIII);
                VariableName.vaPicIB4 = fileName;
                picIBIIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picIBIIII);
                VariableName.vaPicIB4 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picIBIIII.setTag("any");
            }
        }
        else if (requestCode == 1014 && resultCode == RESULT_OK){
            ImageView picMOI = findViewById(R.id.picMOI);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picMOI);
                VariableName.vaPicMO1 = fileName;
                picMOI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMOI);
                VariableName.vaPicMO1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picMOI.setTag("any");
            }
        }
        else if (requestCode == 1015 && resultCode == RESULT_OK){
            ImageView picPDI = findViewById(R.id.picPD_I);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDI);
                VariableName.vaPicPD1 = fileName;
                picPDI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDI);
                VariableName.vaPicPD1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDI.setTag("any");
            }
        }
        else if (requestCode == 1016 && resultCode == RESULT_OK){
            ImageView picPDII = findViewById(R.id.picPD_II);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDII);
                VariableName.vaPicPD2 = fileName;
                picPDII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDII);
                VariableName.vaPicPD2 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDII.setTag("any");
            }
        }
        else if (requestCode == 1017 && resultCode == RESULT_OK){
            ImageView picPDIII = findViewById(R.id.picPD_III);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDIII);
                VariableName.vaPicPD3 = fileName;
                picPDIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDIII);
                VariableName.vaPicPD3 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDIII.setTag("any");
            }
        }
        else if (requestCode == 1018 && resultCode == RESULT_OK){
            ImageView picPDIIII = findViewById(R.id.picPD_IIII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDIIII);
                VariableName.vaPicPD4 = fileName;
                picPDIIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDIIII);
                VariableName.vaPicPD4 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDIIII.setTag("any");
            }
        }
        else if (requestCode == 1019 && resultCode == RESULT_OK){
            ImageView picPDIV = findViewById(R.id.picPD_IV);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDIV);
                VariableName.vaPicPD5 = fileName;
                picPDIV.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDIV);
                VariableName.vaPicPD5 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDIV.setTag("any");
            }
        }
        else if (requestCode == 1020 && resultCode == RESULT_OK){
            ImageView picPDV = findViewById(R.id.picPD_V);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDV);
                VariableName.vaPicPD6 = fileName;
                picPDV.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDV);
                VariableName.vaPicPD6 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDV.setTag("any");
            }
        }
        else if (requestCode == 1021 && resultCode == RESULT_OK){
            ImageView picPDVI = findViewById(R.id.picPD_VI);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName =  copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDVI);
                VariableName.vaPicPD7 = fileName;
                picPDVI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDVI);
                VariableName.vaPicPD7 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDVI.setTag("any");
            }
        }
        else if (requestCode == 1022 && resultCode == RESULT_OK){
            ImageView picPDVII = findViewById(R.id.picPD_VII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picPDVII);
                VariableName.vaPicPD8 = fileName;
                picPDVII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picPDVII);
                VariableName.vaPicPD8 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picPDVII.setTag("any");
            }
        }
        else if (requestCode == 1023 && resultCode == RESULT_OK){
            ImageView picFTI = findViewById(R.id.picFT_I);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picFTI);
                VariableName.vaPicFT1 = fileName;
                picFTI.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picFTI);
                VariableName.vaPicFT1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picFTI.setTag("any");
            }
        }
        else if (requestCode == 1024 && resultCode == RESULT_OK){
            ImageView picFTII = findViewById(R.id.picFT_II);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picFTII);
                VariableName.vaPicFT2 = fileName;
                picFTII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picFTII);
                VariableName.vaPicFT2 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picFTII.setTag("any");
            }
        }
        else if (requestCode == 1025 && resultCode == RESULT_OK){
            ImageView picFTIII = findViewById(R.id.picFT_III);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picFTIII);
                VariableName.vaPicFT3 = fileName;
                picFTIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picFTIII);
                VariableName.vaPicFT3 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picFTIII.setTag("any");
            }
        }
        else if (requestCode == 1026 && resultCode == RESULT_OK){
            ImageView picFTIIII = findViewById(R.id.picFT_IIII);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picFTIIII);
                VariableName.vaPicFT4 = fileName;
                picFTIIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picFTIIII);
                VariableName.vaPicFT4 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picFTIIII.setTag("any");
            }
        }
        else if (requestCode == 1027 && resultCode == RESULT_OK){
            ImageView picRII = findViewById(R.id.picRI_I);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picRII);
                VariableName.vaPicRI1 = fileName;
                picRII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picRII);
                VariableName.vaPicRI1 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picRII.setTag("any");
            }
        }
        else if (requestCode == 1028 && resultCode == RESULT_OK){
            ImageView picRIII = findViewById(R.id.picRI_II);
            if (data != null){
                selectedImage = data.getData();

                Uri uri = Uri.parse(getRealPathFromURI(Home.this,selectedImage));
                File destFile = new File(VariableName.imageStorageFolder);
                Log.d("PATH_DEST", String.valueOf(destFile));
                Log.d("PATH_SOURCE", String.valueOf(uri));
                try {
                    fileName = copyFile(new File(uri.toString()), destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Picasso.with(Home.this).load(selectedImage).fit().centerCrop().into(picRIII);
                VariableName.vaPicRI2 = fileName;
                picRIII.setTag("any");
            } else {
                Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picRIII);
                VariableName.vaPicRI2 = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
                picRIII.setTag("any");
            }
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String copyFile(File sourceFile, File destFile) throws IOException {

        if (!sourceFile.exists()) {
            Log.d("EXISTS ","EXISTS");
            return "Duplicate";
        }
        destFile = CreateFile.createUnique();
        FileChannel  source = new FileInputStream(sourceFile).getChannel();
        FileChannel  destination = new FileOutputStream(destFile).getChannel();
        Log.d("COPY_FILE_DEST ", String.valueOf(destination));
        if (destination != null && source != null) {
            Log.d("SOURCE ", String.valueOf(source));
            Log.d("DEST ", String.valueOf(destination));
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
        return destFile.getName();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
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

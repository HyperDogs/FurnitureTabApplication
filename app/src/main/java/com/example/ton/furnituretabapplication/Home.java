package com.example.ton.furnituretabapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import resource.AsyncTaskSave;

import static com.example.ton.furnituretabapplication.VariableName.vaPicMainImg;
import static resource.AsyncTaskLogin.getDeviceImei;

public class Home extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    public static Context mContext;
    private File file;
    private VariableName varName;
    private ImageView picMainImg;
    private EditText txtStockNo, txtAcmeNo, txtDesc, txtOrder, txtSampling, txtCusName, txtPoNo, txtAql, txtMajor, txtMinor, txtDate, txtNW, txtGW, txtRT_I, txtRT_II, txtRT_III, txtRT_IIII;
    private CheckBox checkBoxRegular, checkBoxRe;
    public static Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

            mResources = getResources();

            //Toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("HOME");
            // add back arrow to toolbar
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }


            mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
            // Set up the ViewPager with the sections adapter.
            mViewPager = findViewById(R.id.container);
            setupViewPager(mViewPager);

            final TabLayout tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                   hideKeyboard();
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new QAPageI(), "HOME");
        adapter.addFragment(new QAPageII(), "SECTION 2");
        adapter.addFragment(new QAPageIII(), "SECTION 3");
        viewPager.setAdapter(adapter);
    }

    private void initView(){
        //Page I
        picMainImg = findViewById(R.id.picMainImg);
        txtStockNo = findViewById(R.id.txtStockNo);
        txtAcmeNo = findViewById(R.id.txtAcmeNo);
        txtDesc = findViewById(R.id.txtDesc);
        txtOrder = findViewById(R.id.txtOrder);
        txtSampling = findViewById(R.id.txtSampling);
        txtCusName = findViewById(R.id.txtCusName);
        txtPoNo = findViewById(R.id.txtPoNo);
        txtAql = findViewById(R.id.txtAql);
        txtMajor = findViewById(R.id.txtMajor);
        txtMinor = findViewById(R.id.txtMinor);
        txtDate = findViewById(R.id.txtDate);
        checkBoxRegular  = findViewById(R.id.checkBoxRegular);
        checkBoxRe = findViewById(R.id.checkBoxRe);
        txtNW = findViewById(R.id.txtNW);
        txtGW = findViewById(R.id.txtGW);

        //Page II

        //Page III
        txtRT_I = findViewById(R.id.txtRT_I);
        txtRT_II = findViewById(R.id.txtRT_II);
        txtRT_III = findViewById(R.id.txtRT_III);
        txtRT_IIII = findViewById(R.id.txtRT_IIII);
    }
     private void setData(){
        //Page I
        VariableName.vaStockNo = txtStockNo.getText().toString();
        VariableName.vaAcmeNo = txtAcmeNo.getText().toString();
        VariableName.vaDesc = txtDesc.getText().toString();
        VariableName.vaOrder = txtOrder.getText().toString();
        VariableName.vaSampling = txtSampling.getText().toString();
        VariableName.vaCusName = txtCusName.getText().toString();
        VariableName.vaPoNo = txtPoNo.getText().toString();
        VariableName.vaAql = txtAql.getText().toString();
        VariableName.vaMajor = txtMajor.getText().toString();
        VariableName.vaMinor = txtMinor.getText().toString();
        VariableName.vaDate = txtDate.getText().toString();
        if(checkBoxRegular.isChecked()){
            VariableName.vaCheckBoxRegularIns = "REGULAR INSPECTION";
        }
        if(checkBoxRe.isChecked()){
            VariableName.vaCheckBoxReIns = "RE INSPECTION";
        }
        VariableName.vaNW = txtNW.getText().toString();
        VariableName.vaGW = txtGW.getText().toString();

        //Page II

        //Page III
         if(txtRT_I != null){
             VariableName.vaRemarkText1 = txtRT_I.getText().toString();
         }

         if(txtRT_II != null){
             VariableName.vaRemarkText2 = txtRT_II.getText().toString();
         }

         if(txtRT_III != null){
             VariableName.vaRemarkText3 = txtRT_III.getText().toString();
         }

         if(txtRT_IIII != null){
             VariableName.vaRemarkText4 = txtRT_IIII.getText().toString();
         }

     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //onBackPressed();
            new AlertDialog.Builder(Home.this)
                    .setTitle("Logout")
                    .setMessage("Would you like to logout ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            Intent i = new Intent(Home.this,MainActivity.class);
                            startActivity(i);
                            finish();

                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        }

        if (item.getItemId() == R.id.mybutton) {
            // do something here
            initView();
            if(VariableName.vaPicMainImg.equals("")
                    || txtStockNo.getText().toString().isEmpty()
                    || txtAcmeNo.getText().toString().isEmpty()
                    || txtDesc.getText().toString().isEmpty()
                    || txtOrder.getText().toString().isEmpty()
                    || txtSampling.getText().toString().isEmpty()
                    || txtCusName.getText().toString().isEmpty()
                    || txtPoNo.getText().toString().isEmpty()
                    || txtAql.getText().toString().isEmpty()
                    || txtMajor.getText().toString().isEmpty()
                    || txtMinor.getText().toString().isEmpty()
                    || txtDate.getText().toString().isEmpty()){
                Toast.makeText(Home.this,"กรณากรอกข้อมูลสินค้าให้ครบถ้วน",Toast.LENGTH_SHORT).show();
            }else {
                new AlertDialog.Builder(Home.this)
                    .setTitle("Save")
                    .setMessage("Would you like to save ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            setData();
                            AsyncTaskSave atlSave = new AsyncTaskSave(Home.this, getDeviceImei(Home.this));
                            atlSave.execute();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
            }
            //Toast.makeText(Home.this,"Saved",Toast.LENGTH_SHORT).show();
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
                        vaPicMainImg = fileName;
                        picMainImg.setTag("any");
                        //Toast.makeText(Home.this,"namepath"+VariableName.vaPicMainImg,Toast.LENGTH_SHORT).show();
                    } else {
                        Picasso.with(Home.this).load(Uri.fromFile(HelperMethod.filePagei)).fit().centerCrop().into(picMainImg);
                        vaPicMainImg = Uri.fromFile(HelperMethod.filePagei).getLastPathSegment();
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

package com.example.ton.furnituretabapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;

/**
 * Created by ton on 1/11/18.
 */

public class HelperMethod extends Activity {


    final private static String[] action_camera = {"Take Picture","Open Gallery","Preview","Delete"};
    final private static String[] action_camera2 = {"Take Picture","Open Gallery"};
    public static File filePagei;
    public static Uri uri;

    public static File dialogImg(final Context mContext, final int imageViewInt, final String nameImage, final ImageView imageView){
        if (nameImage.equals("")||imageView.getTag().equals("defult")){
            new MaterialDialog.Builder(mContext)
                    .items(action_camera2)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                            if (position == 0){
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                filePagei = CreateFile.createUnique();
                                uri = FileProvider.getUriForFile(mContext,BuildConfig.APPLICATION_ID + ".provider",filePagei);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                Log.d("URI>>>>",String.valueOf(uri));
                                ((Activity)mContext).startActivityForResult(intent, imageViewInt);
                            }else{
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                ((Activity)mContext).startActivityForResult(pickPhoto, imageViewInt);
                            }
                        }
                    })
                    .show();

        }else {
            new MaterialDialog.Builder(mContext)
                    .items(action_camera)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                            if (position == 0){
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                filePagei = CreateFile.createUnique();
                                //uri = Uri.fromFile(filePagei);
                                uri = FileProvider.getUriForFile(mContext,BuildConfig.APPLICATION_ID + ".provider",filePagei);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                Log.d("URI>>>>",String.valueOf(uri));
                                ((Activity)mContext).startActivityForResult(intent, imageViewInt);
                            }else if (position == 1){
                                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                ((Activity)mContext).startActivityForResult(pickPhoto, imageViewInt);
                            }else if(position == 2){
                                if (nameImage == null || nameImage.isEmpty() || imageView.getTag().equals("defult")){
                                    Toast.makeText(mContext,"Please take a  photo !! ",Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent previewImg = new Intent(mContext,Preview.class);
                                    previewImg.putExtra("NAME_IMAGE_VIEW",nameImage);
                                    mContext.startActivity(previewImg);
                                }
                            }  else {
                                if (nameImage == null || nameImage.isEmpty() || imageView.getTag().equals("defult")){
                                    Toast.makeText(mContext,"Please take a  photo !! ",Toast.LENGTH_SHORT).show();
                                } else {
                                    @SuppressLint({"NewApi", "LocalSuppress"}) Drawable drawable = mContext.getDrawable(R.drawable.xxx);

                                    //String nameDrawable = mContext.getResources().getResourceEntryName(R.drawable.test);
                                    imageView.setImageDrawable(drawable);
                                    imageView.setTag("defult");
                                    Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    })
                    .show();
        }
        return filePagei;
    }
}

package com.example.ton.furnituretabapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;

/**
 * Created by ton on 1/11/18.
 */

public class HelperMethod extends Activity {


    final private static String[] action_camera = {"Take Picture","Choose Picture","Preview Picture","Delete Picture"};
    public static File filePagei;
    public static Uri uri;
    public static int headerImage = 1001,headerPickImage = 1002;

    public static File dialogImg(final Context mContext, final int imageViewInt){
        Log.d("context",String.valueOf(mContext));
        new MaterialDialog.Builder(mContext)
                .items(action_camera)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                        if (position == 0){
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            filePagei = CreateFile.createUnique();
                            uri = Uri.fromFile(filePagei);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            ((Activity)mContext).startActivityForResult(intent, imageViewInt);
                        }else if (position == 1){
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            ((Activity)mContext).startActivityForResult(pickPhoto, headerPickImage);
                        }else if(position == 2){
                            Intent previewImg = new Intent(mContext,Preview.class);
                            mContext.startActivity(previewImg);
                        }
                        else {
                            Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();
        return filePagei;
    }
}

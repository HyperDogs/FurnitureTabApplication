package com.example.ton.furnituretabapplication;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.lang.reflect.Array;

import javax.security.auth.callback.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageI extends Fragment {
    private TableLayout tableComponent;
    private ImageView picMainImg;
    private String[] action_camera = {"Take Picture","Choose Picture","Preview Picture","Delete Picture"};
    private File file;
    private Uri uri;
    private Context mContext;
    private int headerImage = 1001,headerPickImage = 1002;

    public QAPageI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qapage_i, container, false);
        initView(view);
        setOnClick();
        tableComponent.removeAllViews();
        for (int i = 0;i<5;i++){
            TableRow tableRow = new TableRow(getContext());
            inflater.inflate(R.layout.row_table,tableRow);
            tableComponent.addView(tableRow);

        }




        return view;
    }

    private void initView(View view){
        tableComponent = view.findViewById(R.id.tableComponent);
        picMainImg = view.findViewById(R.id.picMainImg);

    }

    private void setOnClick(){
        picMainImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getActivity())
                        .items(action_camera)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {

                                if (position == 0){
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    file = CreateFile.createUnique();
                                    //uri = FileProvider.getUriForFile(getContext(),BuildConfig.APPLICATION_ID + ".provider",file);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                    startActivityForResult(intent, headerImage);
                                }else {
                                    Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

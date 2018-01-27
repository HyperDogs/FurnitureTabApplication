package com.example.ton.furnituretabapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageIII extends Fragment {

    private ImageView picPDI,picPDII,picPDIII,picPDIIII,picPDIV,picPDV,picPDVI,picPDVII,
    picFTI,picFTII,picFTIII,picFTIIII,
    picRII,picRIII;
    private File file;

    public QAPageIII() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qapage_iii, container, false);
        initView(view);
        setOnClick();


        return view;
    }

    private void initView(View view){
        picPDI = view.findViewById(R.id.picPD_I);
        picPDII = view.findViewById(R.id.picPD_II);
        picPDIII = view.findViewById(R.id.picPD_III);
        picPDIIII = view.findViewById(R.id.picPD_IIII);
        picPDIV = view.findViewById(R.id.picPD_IV);
        picPDV = view.findViewById(R.id.picPD_V);
        picPDVI = view.findViewById(R.id.picPD_VI);
        picPDVII = view.findViewById(R.id.picPD_VII);
        picFTI = view.findViewById(R.id.picFT_I);
        picFTII = view.findViewById(R.id.picFT_II);
        picFTIII = view.findViewById(R.id.picFT_III);
        picFTIIII = view.findViewById(R.id.picFT_IIII);
        picRII = view.findViewById(R.id.picRI_I);
        picRIII = view.findViewById(R.id.picRI_II);

    }

    private void setOnClick(){
        picPDI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1015,VariableName.vaPicPD1, picPDI);
            }
        });

        picPDII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1016,VariableName.vaPicPD2, picPDII);
            }
        });

        picPDIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1017,VariableName.vaPicPD3, picPDIII);
            }
        });

        picPDIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1018,VariableName.vaPicPD4, picPDIIII);
            }
        });

        picPDIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1019,VariableName.vaPicPD5, picPDIV);
            }
        });

        picPDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1020,VariableName.vaPicPD6, picPDV);
            }
        });

        picPDVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1021,VariableName.vaPicPD7, picPDVI);
            }
        });

        picPDVII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1022,VariableName.vaPicPD8, picPDVII);
            }
        });

        picFTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1023,VariableName.vaPicFT1, picFTI);
            }
        });

        picFTII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1024,VariableName.vaPicFT2, picFTII);
            }
        });

        picFTIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1025,VariableName.vaPicFT3, picFTIII);
            }
        });

        picFTIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1026,VariableName.vaPicFT4, picFTIIII);
            }
        });

        picRII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1027,VariableName.vaPicRI1, picRII);
            }
        });

        picRIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1028,VariableName.vaPicRI2, picRIII);
            }
        });
    }
    @Override
    public void onDestroy() {
        freeMemory();
        super.onDestroy();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

}

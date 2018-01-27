package com.example.ton.furnituretabapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageII extends Fragment {
    private ImageView picMCI,picMCII,picMCIII,picMCIIII,picMCV,picMCIV,picMCVI,picMCVII,
    picIBI,picIBII,picIBIII,picIBIIII,
    picMOI;
    File file;

    public QAPageII() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qapage_ii, container, false);
        initView(view);
        setOnClick();
        // Inflate the layout for this fragment
        return view;
    }
    private void initView(View view){
        picMCI = view.findViewById(R.id.picMC_I);
        picMCII = view.findViewById(R.id.picMC_II);
        picMCIII = view.findViewById(R.id.picMC_III);
        picMCIIII = view.findViewById(R.id.picMC_IIII);
        picMCIV = view.findViewById(R.id.picMC_IV);
        picMCV = view.findViewById(R.id.picMC_V);
        picMCVI = view.findViewById(R.id.picMC_VI);
        picMCVII = view.findViewById(R.id.picMC_VII);
        picIBI = view.findViewById(R.id.picIB_I);
        picIBII = view.findViewById(R.id.picIB_II);
        picIBIII = view.findViewById(R.id.picIB_III);
        picIBIIII = view.findViewById(R.id.picIB_IIII);
        picMOI = view.findViewById(R.id.picMOI);

    }

    private void setOnClick(){
        picMCI.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                file =  HelperMethod.dialogImg(getActivity(),1002,VariableName.vaPicMC1, picMCI);
            }
        });

        picMCII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1003,VariableName.vaPicMC2, picMCII);
            }
        });

        picMCIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1004,VariableName.vaPicMC3, picMCIII);
            }
        });

        picMCIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1005,VariableName.vaPicMC4, picMCIIII);
            }
        });

        picMCIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1006,VariableName.vaPicMC5, picMCIV);
            }
        });

        picMCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1007,VariableName.vaPicMC6, picMCV);
            }
        });

        picMCVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1008,VariableName.vaPicMC7,picMCVI);
            }
        });

        picMCVII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1009,VariableName.vaPicMC8,picMCVII);
            }
        });

        picIBI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1010,VariableName.vaPicIB1, picIBI);
            }
        });

        picIBII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1011,VariableName.vaPicIB2, picIBII);
            }
        });

        picIBIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1012,VariableName.vaPicIB3, picIBIII);
            }
        });

        picIBIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1013,VariableName.vaPicIB4, picIBIIII);
            }
        });

        picMOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(),1014,VariableName.vaPicMO1, picMOI);
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

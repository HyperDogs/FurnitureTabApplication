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
                                file =  HelperMethod.dialogImg(getActivity(),"picMainImg");
            }
        });

        picMCII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMCVII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picIBI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picIBII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picIBIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picIBIIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
            }
        });

        picMOI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), "picMainImg");
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

package com.example.ton.furnituretabapplication;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.QADetailModel;
import Model.QASectionModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageI extends Fragment {
    private TableLayout tableComponent,maintable;
    private ImageView picMainImg;
    private File file;
    private DatePickerDialog.OnDateSetListener datePick;
    private Calendar myCalendar;
    private EditText txtDate;


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
        initTable(inflater,view);





        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void initTable(LayoutInflater inflater,View view) {

        List<QASectionModel> qaSectionlModelList = VariableName.qaSectionList;
        Log.d("qaSectionSize",String.valueOf(qaSectionlModelList.size()));
        VariableName.idHeader = new ArrayList();
        VariableName.idDetail = new ArrayList();
        maintable.removeAllViews();
        TableRow tableRow = null;
        TableRow tableRow1 = null;
        int headerInt = 1110;
        int detailInt = 2220;
        for (int i = 0;i < qaSectionlModelList.size();i++){
            QASectionModel qaSectionModel = qaSectionlModelList.get(i);
            Log.d("qaSecTionModel",qaSectionModel.getQaSectionType());
            List<QADetailModel> qaDetailModelList = qaSectionlModelList.get(i).getQaDetailModelList();
            Log.d("qaDetailModelList",String.valueOf(qaSectionlModelList.get(i).getQaDetailModelList().size()));
            tableRow = new TableRow(getContext());
            inflater.inflate(R.layout.row_table_head,tableRow);
            maintable.addView(tableRow);

            String headerStr = qaSectionModel.getQaSectionDesc();
            TextView headerTxt = view.findViewById(R.id.header);
            headerTxt.setId(headerInt + i);
            headerTxt.setText(headerStr);
            VariableName.idHeader.add(headerTxt.getId());


            TableLayout tableCom = view.findViewById(R.id.tableComponent);
            tableCom.setId(1+i);
            tableCom.removeAllViews();


            for (int a = 0;a < qaDetailModelList.size();a++){
                QADetailModel qaDetailModel = qaDetailModelList.get(a);
                    tableRow1 = new TableRow(getContext());
                    if (qaDetailModel.getQaDetailTxtUse().equals("Y")) {
                        inflater.inflate(R.layout.row_table, tableRow1);
                    }else {
                        inflater.inflate(R.layout.row_table_edt,tableRow1);
                    }

                    tableCom.addView(tableRow1);
                    TextView rowNum = view.findViewById(R.id.txtNo);
                    rowNum.setId(1 + a);
                    rowNum.setText(qaDetailModel.getQaDetailSeq());

                    String detailStr = qaDetailModel.getQaDetailDesc();
                    TextView detailTxt = view.findViewById(R.id.txtDetail);
                    detailTxt.setId(detailInt + a);
                    detailTxt.setText(detailStr);


            }
        }

    }

    private void initView(View view){
        maintable = view.findViewById(R.id.maintable);
        tableComponent = view.findViewById(R.id.tableComponent);
        picMainImg = view.findViewById(R.id.picMainImg);
        txtDate = view.findViewById(R.id.txtDate);

    }

    private void setOnClick() {
        picMainImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = HelperMethod.dialogImg(getActivity(), 1001, VariableName.vaPicMainImg, picMainImg);
            }
        });
        myCalendar = Calendar.getInstance();
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), datePick, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        datePick = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                txtDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

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

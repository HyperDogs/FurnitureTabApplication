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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.QADataTable;
import Model.QADetailModel;
import Model.QASectionModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageI extends Fragment {
    private TableLayout tableComponent,maintable;
    private RadioButton releaseRdo,rejectRdo;
    private ImageView picMainImg;
    private File file;
    private DatePickerDialog.OnDateSetListener datePick;
    private Calendar myCalendar;
    private EditText txtDate;
    private TextView txtEmployee;
    private RadioButton radioReleaseF, radioRejectF;
    private boolean fistRec = true;
    private View view;
    public static ArrayList<TextView> textViewsSectionList;
    public static ArrayList<TextView> textViewsDetailList;
    public static ArrayList<EditText> editTextsDetailList;
    public static ArrayList<RadioGroup> radioGroupsStatusList;
    public static ArrayList<EditText> editTextsComment;
    public static ArrayList<String> listType;


    public QAPageI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_qapage_i, container, false);
        initView(view);
        setOnClick();
        initTable(inflater,view);
        txtEmployee.setText(VariableName.employeeName);




        return view;
    }

    @SuppressLint("ResourceAsColor")
    private void initTable(LayoutInflater inflater,View view) {

        List<QASectionModel> qaSectionlModelList = VariableName.qaSectionList;
        maintable.removeAllViews();
        TableRow tableRow = null;
        TableRow tableRow1 = null;

        textViewsSectionList = new ArrayList<>();
        listType = new ArrayList();
        textViewsDetailList = new ArrayList<>();
        editTextsDetailList = new ArrayList<>();
        radioGroupsStatusList = new ArrayList<>();
        editTextsComment = new ArrayList<>();
        for (int i = 0;i < qaSectionlModelList.size();i++){
            QASectionModel qaSectionModel = qaSectionlModelList.get(i);
            List<QADetailModel> qaDetailModelList = qaSectionlModelList.get(i).getQaDetailModelList();
            tableRow = new TableRow(getContext());
            inflater.inflate(R.layout.row_table_head,tableRow);
            maintable.addView(tableRow);

            String headerStr = qaSectionModel.getQaSectionDesc();
            String type = qaSectionModel.getQaSectionType();
            TextView headerTxt = view.findViewById(R.id.header);
            textViewsSectionList.add(headerTxt);
            //headerTxt.setId(R.id.header + i);
            headerTxt.setText(headerStr);
            //VariableName.idHeader.add(headerTxt.getId());


            TableLayout tableCom = view.findViewById(R.id.tableComponent);
            tableCom.setId(i);
            tableCom.removeAllViews();


            for (int a = 0;a < qaDetailModelList.size();a++){

                    listType.add(type);
                    QADetailModel qaDetailModel = qaDetailModelList.get(a);
                    tableRow1 = new TableRow(getContext());
                    if (qaDetailModel.getQaDetailTxtUse().equals("N")) {
                        inflater.inflate(R.layout.row_table_edt, tableRow1);
                    }else {
                        inflater.inflate(R.layout.row_table_edt, tableRow1);
                    }


                    tableCom.addView(tableRow1);
                    TextView rowNum = view.findViewById(R.id.txtNo);
                    rowNum.setId(1 + a);
                    rowNum.setText(qaDetailModel.getQaDetailSeq());

                    String detailStr = qaDetailModel.getQaDetailDesc();
                    TextView detailTxt = view.findViewById(R.id.txtDetail);
                    textViewsDetailList.add(detailTxt);
                    detailTxt.setId(2 + a);
                    detailTxt.setText(detailStr);

                    EditText detailEdt = view.findViewById(R.id.txtDetail2);
                    detailEdt.setId(3+a);
                    editTextsDetailList.add(detailEdt);
                    if (qaDetailModel.getQaDetailTxtUse().equals("N")) {
                        detailEdt.setVisibility(View.INVISIBLE);
                        detailEdt.setText("###");
                    }else {
                        detailEdt.setVisibility(View.VISIBLE);
                        detailEdt.setText("");
                    }


                    RadioGroup radioGroup = view.findViewById(R.id.group_radio);
                    radioGroup.setId(4+a);
                    radioGroupsStatusList.add(radioGroup);



                    EditText commentEdt = view.findViewById(R.id.txtComment);
                    editTextsComment.add(commentEdt);
                    commentEdt.setId(5+a);

            }


        }

    }

    private void initView(View view){
        maintable = view.findViewById(R.id.maintable);
        tableComponent = view.findViewById(R.id.tableComponent);
        picMainImg = view.findViewById(R.id.picMainImg);
        txtDate = view.findViewById(R.id.txtDate);
        releaseRdo = view.findViewById(R.id.radioReleaseF);
        rejectRdo = view.findViewById(R.id.radioRejectF);
        txtEmployee = view.findViewById(R.id.txtEmployee);
        radioReleaseF = view.findViewById(R.id.radioReleaseF);
        radioReleaseF.setChecked(true);
        radioRejectF = view.findViewById(R.id.radioRejectF);
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
        radioReleaseF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioRejectF.setChecked(false);
                VariableName.vaFinalStatus = "RELRASE";
            }
        });
        radioRejectF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioReleaseF.setChecked(false);
                VariableName.vaFinalStatus = "REJECT";
            }
        });

    }


    public static ArrayList<QADataTable> getDataTable(){
        ArrayList<QADataTable> setDataTable = new ArrayList<>();
        QADataTable qaDataTable;
        int id;
        Log.d("id Header size : ",String.valueOf(listType.size()));
        for (int i = 0 ;i<listType.size();i++){
            String  typeSection = listType.get(i);
            qaDataTable = new QADataTable();
            qaDataTable.setSection(typeSection);
            Log.d("type",String.valueOf(listType.get(i)));


            TextView detail = textViewsDetailList.get(i);
            String detailStr =  detail.getText().toString();
            qaDataTable.setDetail(detailStr);
            Log.d("detail" , detailStr);


            EditText detailEdt = editTextsDetailList.get(i);
            String detailEdtStr="";
            if (detailEdt.getText().toString().equals("###")){
                detailEdtStr = "";
            }else {
                detailEdtStr = detailEdt.getText().toString();
            }
            qaDataTable.setDetailExtra(detailEdtStr);
            Log.d("detailEdt" , detailEdtStr);


            RadioGroup radioGroup = radioGroupsStatusList.get(i);
            int statusInt  = radioGroup.getCheckedRadioButtonId();
            String statusStr = Home.mResources.getResourceEntryName(statusInt);
            if (statusStr.equals("accept")){
                qaDataTable.setRadio(VariableName.ACCEPT);
            }else if (statusStr.equals("reject")){
                qaDataTable.setRadio(VariableName.REJECT);
            }else {
                qaDataTable.setRadio(VariableName.REWORK);
            }
            Log.d("radioCheck",statusStr);


            EditText commentEdt = editTextsComment.get(i);
            String commentStr = commentEdt.getText().toString();
            qaDataTable.setComment(commentStr);
            Log.d("comment",commentStr);

            setDataTable.add(qaDataTable);
        }
        return setDataTable;
    }




    @Override
    public  void onDestroy() {
        freeMemory();
        super.onDestroy();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}

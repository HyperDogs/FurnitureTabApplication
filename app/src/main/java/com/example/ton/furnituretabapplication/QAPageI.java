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
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageI extends Fragment {
    private TableLayout tableComponent;
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
        tableComponent.removeAllViews();
        TableRow tableRow = null;
        int i = 0;

        for (i = 0;i<6;i++){

            tableRow = new TableRow(getContext());
            inflater.inflate(R.layout.row_table,tableRow);
            TextView rowNum = view.findViewById(R.id.txtNo);
            rowNum.setId(i);
            rowNum.setText(""+i);

            tableComponent.addView(tableRow,i);
        }
        TextView rowNum = view.findViewById(R.id.txtNo);
        Log.d("rowNum",String.valueOf(rowNum.getId()));
        rowNum.setId(i+1);
        Log.d("rowNum",String.valueOf(rowNum.getId()));
        rowNum.setText(""+i);
       // Toast.makeText(getContext(),""+countRow,Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),""+tableRow.getVirtualChildCount(),Toast.LENGTH_SHORT).show();
    }

    private void initView(View view){
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

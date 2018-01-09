package com.example.ton.furnituretabapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageI extends Fragment {
    private TableLayout tableComponent;

    public QAPageI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qapage_i, container, false);
        initView(view);
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

    }

}

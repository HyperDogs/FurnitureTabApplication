package com.example.ton.furnituretabapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QAPageIII extends Fragment {

    private TableLayout table;
    private TextView textTest;
    private TableRow tableRow;
    public QAPageIII() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> obj = new ArrayList<String>();

	  /*This is how elements should be added to the array list*/
        obj.add("Ajeet");
        obj.add("Harry");
        obj.add("Chaitanya");
        obj.add("Steve");
        obj.add("Anuj");



//        table.addView(tableRow);
  //      table.addView(view.findViewById(R.id.row1));







        // Inflate the layout for this fragment

        Toast.makeText(getActivity(),"Hello",Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_qapage_iii, container, false);
    }

}

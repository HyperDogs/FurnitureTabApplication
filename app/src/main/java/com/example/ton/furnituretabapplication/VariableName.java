package com.example.ton.furnituretabapplication;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Model.QADataTable;
import Model.QASectionModel;

/**
 * Created by marisalom on 10/01/2018.
 */

public class VariableName {

    public static String imageStorageFolder = Environment.getExternalStorageDirectory() + File.separator + "DCIM" + File.separator + "Camera" + File.separator;
    public static ArrayList idHeader;
    public static ArrayList idDetail;
    public static ArrayList idRadio;
    public static ArrayList dataTable;
    public static List<QASectionModel> qaSectionList;
    public static ArrayList<QADataTable> dataTablesList;
    final public static String ACCEPT = "ACCEPT";
    final public static String REJECT = "REJECT";
    final public static String REWORK = "REWORK";

    //Qa Page I
    public static String vaPicMainImg = "";
    public static String vaStockNo = "";
    public static String vaAcmeNo = "";
    public static String vaDesc = "";
    public static String vaOrder = "";
    public static String vaSampling = "";
    public static String vaCusName = "";
    public static String vaPoNo = "";
    public static String vaAql = "";
    public static String vaMajor = "";
    public static String vaMinor = "";
    public static String vaCheckBoxRegularIns = "";
    public static String vaCheckBoxReIns = "";
    public static String vaDate = "";
    public static String employeeName;

    //Qa Page II
    public static String vaPicMC1 = "";
    public static String vaPicMC2 = "";
    public static String vaPicMC3 = "";
    public static String vaPicMC4 = "";
    public static String vaPicMC5 = "";
    public static String vaPicMC6 = "";
    public static String vaPicMC7 = "";
    public static String vaPicMC8 = "";
    public static String vaPicIB1 = "";
    public static String vaPicIB2 = "";
    public static String vaPicIB3 = "";
    public static String vaPicIB4 = "";
    public static String vaPicMO1 = "";

    //Qa Page III
    public static String vaPicPD1 = "";
    public static String vaPicPD2 = "";
    public static String vaPicPD3 = "";
    public static String vaPicPD4 = "";
    public static String vaPicPD5 = "";
    public static String vaPicPD6 = "";
    public static String vaPicPD7 = "";
    public static String vaPicPD8 = "";
    public static String vaPicFT1 = "";
    public static String vaPicFT2 = "";
    public static String vaPicFT3 = "";
    public static String vaPicFT4 = "";
    public static String vaPicRI1 = "";
    public static String vaPicRI2 = "";
    public static String vaRemarkText1 = "";
    public static String vaRemarkText2 = "";
    public static String vaRemarkText3 = "";
    public static String vaRemarkText4 = "";



}

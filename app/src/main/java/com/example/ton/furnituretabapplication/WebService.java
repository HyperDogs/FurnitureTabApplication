package com.example.ton.furnituretabapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by Optimize on 21/1/2561.
 */

public class WebService {
    public static String URL_server = "http://180.183.245.89/acme/qa/";
    public static String URL_createNew = "createNewFile.php";
    public static String URL_uploadFile = "UploadFileToServer.php";
    public static String URL_createPDF = "mailer/mailer.php";
    public static String URL_qaSection = "qaSection.php";
    public static String URL_qaDetail = "qaDetail.php";


    OkHttpHelper okHttpHelper = new OkHttpHelper();
    public ArrayList<String> qaSectionType;
    public ArrayList<String> qaSectionDesc;

    public void getQASectionDetail(){
        RequestBody qaParams = new FormBody.Builder()
                .build();

        String qaResult = okHttpHelper.serverRequest(URL_qaSection, qaParams);

        try {
            JSONArray data = new JSONArray(qaResult);

            if(data.length()>0) {
                qaSectionType = new ArrayList<String>();
                qaSectionDesc = new ArrayList<String>();

                for (int i = 0; i < data.length(); i++) {
                    JSONObject c = data.getJSONObject(i);
                    qaSectionType.add(c.getString("TYPE").toString());
                    qaSectionDesc.add(c.getString("DESC").toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getQaSectionType(){
        return qaSectionType;
    }

    public ArrayList<String> getQaSectionDesc(){
        return  qaSectionDesc;
    }
}
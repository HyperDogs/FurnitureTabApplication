package resource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.example.ton.furnituretabapplication.CopyImageToServer;
import com.example.ton.furnituretabapplication.OkHttpHelper;
import com.example.ton.furnituretabapplication.VariableName;
import com.example.ton.furnituretabapplication.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.QADataTable;
import okhttp3.FormBody;

import static com.example.ton.furnituretabapplication.QAPageI.getDataTable;

/**
 * Created by marisalom on 28/01/2018.
 */

public class AsyncTaskSave extends AsyncTask<String, Void, String> {
    private Activity activity;
    private ProgressDialog progressDialog;
    private String Imei;
    private boolean SAVE_STATUS = false;
    private Handler handler = new Handler();

    private OkHttpHelper mOkHttpHelper;
    private WebService mWebService;
    private CopyImageToServer mUploadPhoto;
    private int mNewDocumentNo = 0;

    List<String> MPList;
    List<String> IBList;
    List<String> MCList;
    List<String> PRList;
    List<String> FIList;
    List<String> RMList;
    List<String> RTList;

    public AsyncTaskSave(Activity act, String Imei){
        this.activity = act;
        this.Imei = Imei;

        this.mOkHttpHelper = new OkHttpHelper();
        this.mWebService = new WebService();
        this.mUploadPhoto = new CopyImageToServer();

        setFileName();
        setRemarkText();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "",
                "Loading. Please wait...", true);
    }

    protected String doInBackground(String... urls)   {
        String result="";
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Imei.equals("")){
                    SAVE_STATUS = false;
                }else {
                    //Header
                    sendHeaderToServer();

                    //Detail
                    VariableName.dataTablesList = getDataTable();
                    for (int i = 0; i < VariableName.dataTablesList.size(); i++) {
                        QADataTable qaDataTable = VariableName.dataTablesList.get(i);
                        sendDetailToServer(String.valueOf(i), qaDataTable);
                    }

                    //Image
                    for (int i = 0; i < MPList.size(); i++) {
                        String fileName = MPList.get(i);
                        sendImageToServer(String.valueOf(i), "MP", fileName);
                    }

                    for (int i = 0; i < IBList.size(); i++) {
                        String fileName = IBList.get(i);
                        sendImageToServer(String.valueOf(i), "IB", fileName);
                    }

                    for (int i = 0; i < MCList.size(); i++) {
                        String fileName = MCList.get(i);
                        sendImageToServer(String.valueOf(i), "MC", fileName);
                    }

                    for (int i = 0; i < PRList.size(); i++) {
                        String fileName = PRList.get(i);
                        sendImageToServer(String.valueOf(i), "PR", fileName);
                    }

                    for (int i = 0; i < FIList.size(); i++) {
                        String fileName = FIList.get(i);
                        sendImageToServer(String.valueOf(i), "FI", fileName);
                    }

                    for (int i = 0; i < RMList.size(); i++) {
                        String fileName = RMList.get(i);
                        sendImageToServer(String.valueOf(i), "RM", fileName);
                    }

                    //Remark Text
                    for (int i = 0; i < RTList.size(); i++) {
                        String text = RTList.get(i);
                        sendRemarkToServer(String.valueOf(i), "RT", text);
                    }

                    SAVE_STATUS = true;
                }

                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        if (SAVE_STATUS == true) {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                Toast.makeText(activity, "Successfully Save.", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else if (SAVE_STATUS == false) {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                Toast.makeText(activity, "Can't Save", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }, 1500);
            }
        }).start();
        return result;
    }

    protected void onPostExecute(String result)  {

    }

    private String sendHeaderToServer(){
        mUploadPhoto.sendFileToServer(VariableName.vaPicMainImg, mWebService.URL_uploadFile,"HEADER");

        FormBody params = new FormBody.Builder()
                .add("PICMAINIMG", VariableName.vaPicMainImg)
                .add("STOCKNO", VariableName.vaStockNo)
                .add("ACMENO", VariableName.vaAcmeNo)
                .add("DESC", VariableName.vaDesc)
                .add("ORDER", VariableName.vaOrder)
                .add("SAMPLING", VariableName.vaSampling)
                .add("CUSNAME", VariableName.vaCusName)
                .add("PONO", VariableName.vaPoNo)
                .add("AQL", VariableName.vaAql)
                .add("MAJOR", VariableName.vaMajor)
                .add("MINOR", VariableName.vaMinor)
                .add("REGULARINS", VariableName.vaCheckBoxRegularIns)
                .add("REINS", VariableName.vaCheckBoxReIns)
                .add("DATE", VariableName.vaDate)
                .add("REGULAR_INSPECTION", VariableName.vaCheckBoxRegularIns)
                .add("RE_INSPECTION", VariableName.vaCheckBoxReIns)
                .add("NW", VariableName.vaNW)
                .add("GW", VariableName.vaGW)
                .add("FINAL_STATUS", VariableName.vaFinalStatus)

                .build();

        try {
            JSONArray data = new JSONArray(mOkHttpHelper.serverRequest(mWebService.URL_createNew,params));
            JSONObject c = data.getJSONObject(0);

            if(c.getInt("STATUS") == 0){
                return c.getString("DESCRIPTION");
            } else {
                return "";
            }
        } catch (JSONException e) {
            return e.getMessage().toString();
        }
    }

    private String sendDetailToServer(String seq, QADataTable qaDataTable){
        FormBody params = new FormBody.Builder()
                .add("SEQ", seq)
                .add("SECTION", qaDataTable.getSection())
                .add("DETAIL", qaDataTable.getDetail())
                .add("EDITTEXT", qaDataTable.getDetailExtra())
                .add("STATUS", qaDataTable.getRadio())
                .add("COMMENT", qaDataTable.getComment())

                .build();

        try {
            JSONArray data = new JSONArray(mOkHttpHelper.serverRequest(mWebService.URL_createNew,params));
            JSONObject c = data.getJSONObject(0);

            if(c.getInt("STATUS") == 0){
                return c.getString("DESCRIPTION");
            } else {
                return "";
            }
        } catch (JSONException e) {
            return e.getMessage().toString();
        }
    }

    private String sendImageToServer(String seq, String type, String fileName){
        if (fileName != null){
            //mUploadPhoto.sendFileToServer(fileName, mWebService.URL_uploadFile,"IMAGE");

            FormBody params = new FormBody.Builder()
                    .add("SEQ", seq)
                    .add("TYPE", type)
                    .add("PICNAME", fileName)

                    .build();

            try {
                JSONArray data = new JSONArray(mOkHttpHelper.serverRequest(mWebService.URL_createNew, params));
                JSONObject c = data.getJSONObject(0);

                if(c.getInt("STATUS") == 0){
                    return c.getString("DESCRIPTION");
                } else {
                    return "";
                }
            } catch (JSONException e) {
                return e.getMessage().toString();
            }
        }else {
            return null;
        }

    }

    private String sendRemarkToServer(String seq, String type, String text){

        FormBody params = new FormBody.Builder()
                .add("SEQ", seq)
                .add("TYPE", type)
                .add("REMARKTEXT", text)

                .build();

        try {
            JSONArray data = new JSONArray(mOkHttpHelper.serverRequest(mWebService.URL_createNew, params));
            JSONObject c = data.getJSONObject(0);

            if(c.getInt("STATUS") == 0){
                return c.getString("DESCRIPTION");
            } else {
                return "";
            }
        } catch (JSONException e) {
            return e.getMessage().toString();
        }
    }

    private void setFileName(){
        MPList = new ArrayList<>();
        MPList.add(VariableName.vaPicMC1);
        MPList.add(VariableName.vaPicMC2);
        MPList.add(VariableName.vaPicMC3);
        MPList.add(VariableName.vaPicMC4);
        MPList.add(VariableName.vaPicMC5);
        MPList.add(VariableName.vaPicMC6);
        MPList.add(VariableName.vaPicMC7);
        MPList.add(VariableName.vaPicMC8);

        IBList = new ArrayList<>();
        IBList.add(VariableName.vaPicIB1);
        IBList.add(VariableName.vaPicIB2);
        IBList.add(VariableName.vaPicIB3);
        IBList.add(VariableName.vaPicIB4);

        MCList = new ArrayList<>();
        MCList.add(VariableName.vaPicMO1);

        PRList = new ArrayList<>();
        PRList.add(VariableName.vaPicPD1);
        PRList.add(VariableName.vaPicPD2);
        PRList.add(VariableName.vaPicPD3);
        PRList.add(VariableName.vaPicPD4);
        PRList.add(VariableName.vaPicPD5);
        PRList.add(VariableName.vaPicPD6);
        PRList.add(VariableName.vaPicPD7);
        PRList.add(VariableName.vaPicPD8);

        FIList = new ArrayList<>();
        FIList.add(VariableName.vaPicFT1);
        FIList.add(VariableName.vaPicFT2);
        FIList.add(VariableName.vaPicFT3);
        FIList.add(VariableName.vaPicFT4);

        RMList = new ArrayList<>();
        RMList.add(VariableName.vaPicRI1);
        RMList.add(VariableName.vaPicRI2);

    }

    private void setRemarkText(){
        RTList = new ArrayList<>();
        RTList.add(VariableName.vaRemarkText1);
        RTList.add(VariableName.vaRemarkText2);
        RTList.add(VariableName.vaRemarkText3);
        RTList.add(VariableName.vaRemarkText4);
    }

}

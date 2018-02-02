package resource;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.example.ton.furnituretabapplication.CopyImageToServer;
import com.example.ton.furnituretabapplication.OkHttpHelper;
import com.example.ton.furnituretabapplication.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import Model.TBUserLoginModel;
import okhttp3.FormBody;

import static com.example.ton.furnituretabapplication.VariableName.vaAcmeNo;
import static com.example.ton.furnituretabapplication.VariableName.vaAql;
import static com.example.ton.furnituretabapplication.VariableName.vaCheckBoxReIns;
import static com.example.ton.furnituretabapplication.VariableName.vaCheckBoxRegularIns;
import static com.example.ton.furnituretabapplication.VariableName.vaCusName;
import static com.example.ton.furnituretabapplication.VariableName.vaDate;
import static com.example.ton.furnituretabapplication.VariableName.vaDesc;
import static com.example.ton.furnituretabapplication.VariableName.vaMajor;
import static com.example.ton.furnituretabapplication.VariableName.vaMinor;
import static com.example.ton.furnituretabapplication.VariableName.vaOrder;
import static com.example.ton.furnituretabapplication.VariableName.vaPicFT1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicFT2;
import static com.example.ton.furnituretabapplication.VariableName.vaPicFT3;
import static com.example.ton.furnituretabapplication.VariableName.vaPicFT4;
import static com.example.ton.furnituretabapplication.VariableName.vaPicIB1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicIB2;
import static com.example.ton.furnituretabapplication.VariableName.vaPicIB3;
import static com.example.ton.furnituretabapplication.VariableName.vaPicIB4;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC2;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC3;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC4;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC5;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC6;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC7;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMC8;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMO1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicMainImg;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD2;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD3;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD4;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD5;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD6;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD7;
import static com.example.ton.furnituretabapplication.VariableName.vaPicPD8;
import static com.example.ton.furnituretabapplication.VariableName.vaPicRI1;
import static com.example.ton.furnituretabapplication.VariableName.vaPicRI2;
import static com.example.ton.furnituretabapplication.VariableName.vaPoNo;
import static com.example.ton.furnituretabapplication.VariableName.vaRemarkText1;
import static com.example.ton.furnituretabapplication.VariableName.vaRemarkText2;
import static com.example.ton.furnituretabapplication.VariableName.vaRemarkText3;
import static com.example.ton.furnituretabapplication.VariableName.vaRemarkText4;
import static com.example.ton.furnituretabapplication.VariableName.vaSampling;
import static com.example.ton.furnituretabapplication.VariableName.vaStockNo;

/**
 * Created by marisalom on 28/01/2018.
 */

public class AsyncTaskSave extends AsyncTask<String, Void, String> {
    private Activity activity;
    private ProgressDialog progressDialog;
    private String Imei, imgName;
    private boolean SAVE_STATUS = false;
    private Handler handler = new Handler();

    private OkHttpHelper mOkHttpHelper;
    private WebService mWebService;
    private CopyImageToServer mUploadPhoto;

    public AsyncTaskSave(Activity act, String Imei, String imgName){
        this.activity = act;
        this.Imei = Imei;
        this.imgName = imgName;

        this.mOkHttpHelper = new OkHttpHelper();
        this.mWebService = new WebService();

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
        mUploadPhoto.sendFileToServer(imgName, mWebService.URL_uploadFile,"HEADER");

        FormBody params = new FormBody.Builder()
                .add("PARAM_1", vaPicMainImg)
                .add("PARAM_2", vaStockNo)
                .add("PARAM_3", vaAcmeNo)
                .add("PARAM_4", vaDesc)
                .add("PARAM_5", vaOrder)
                .add("PARAM_6", vaSampling)
                .add("PARAM_7", vaCusName)
                .add("PARAM_8", vaPoNo)
                .add("PARAM_9", vaAql)
                .add("PARAM_10", vaMajor)
                .add("PARAM_11", vaMinor)
                .add("PARAM_12", vaCheckBoxRegularIns)
                .add("PARAM_13", vaCheckBoxReIns)
                .add("PARAM_14", vaDate)

                .add("PARAM_15", vaPicMC1)
                .add("PARAM_16", vaPicMC2)
                .add("PARAM_17", vaPicMC3)
                .add("PARAM_18", vaPicMC4)
                .add("PARAM_19", vaPicMC5)
                .add("PARAM_20", vaPicMC6)
                .add("PARAM_21", vaPicMC7)
                .add("PARAM_22", vaPicMC8)
                .add("PARAM_23", vaPicIB1)
                .add("PARAM_24", vaPicIB2)
                .add("PARAM_25", vaPicIB3)
                .add("PARAM_26", vaPicIB4)
                .add("PARAM_27", vaPicMO1)

                .add("PARAM_28", vaPicPD1)
                .add("PARAM_29", vaPicPD2)
                .add("PARAM_30", vaPicPD3)
                .add("PARAM_31", vaPicPD4)
                .add("PARAM_32", vaPicPD5)
                .add("PARAM_33", vaPicPD6)
                .add("PARAM_34", vaPicPD7)
                .add("PARAM_35", vaPicPD8)
                .add("PARAM_36", vaPicFT1)
                .add("PARAM_37", vaPicFT2)
                .add("PARAM_38", vaPicFT3)
                .add("PARAM_39", vaPicFT4)
                .add("PARAM_40", vaPicRI1)
                .add("PARAM_41", vaPicRI2)
                .add("PARAM_42", vaRemarkText1)
                .add("PARAM_43", vaRemarkText2)
                .add("PARAM_44", vaRemarkText3)
                .add("PARAM_45", vaRemarkText4)

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


}

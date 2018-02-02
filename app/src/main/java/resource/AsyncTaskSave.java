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
import okhttp3.FormBody;

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
                .add("PARAM_1", VariableName.vaPicMainImg)
                .add("PARAM_2", VariableName.vaStockNo)
                .add("PARAM_3", VariableName.vaAcmeNo)
                .add("PARAM_4", VariableName.vaDesc)
                .add("PARAM_5", VariableName.vaOrder)
                .add("PARAM_6", VariableName.vaSampling)
                .add("PARAM_7", VariableName.vaCusName)
                .add("PARAM_8", VariableName.vaPoNo)
                .add("PARAM_9", VariableName.vaAql)
                .add("PARAM_10", VariableName.vaMajor)
                .add("PARAM_11", VariableName.vaMinor)
                .add("PARAM_12", VariableName.vaCheckBoxRegularIns)
                .add("PARAM_13", VariableName.vaCheckBoxReIns)
                .add("PARAM_14", VariableName.vaDate)

                .add("PARAM_15", VariableName.vaPicMC1)
                .add("PARAM_16", VariableName.vaPicMC2)
                .add("PARAM_17", VariableName.vaPicMC3)
                .add("PARAM_18", VariableName.vaPicMC4)
                .add("PARAM_19", VariableName.vaPicMC5)
                .add("PARAM_20", VariableName.vaPicMC6)
                .add("PARAM_21", VariableName.vaPicMC7)
                .add("PARAM_22", VariableName.vaPicMC8)
                .add("PARAM_23", VariableName.vaPicIB1)
                .add("PARAM_24", VariableName.vaPicIB2)
                .add("PARAM_25", VariableName.vaPicIB3)
                .add("PARAM_26", VariableName.vaPicIB4)
                .add("PARAM_27", VariableName.vaPicMO1)

                .add("PARAM_28", VariableName.vaPicPD1)
                .add("PARAM_29", VariableName.vaPicPD2)
                .add("PARAM_30", VariableName.vaPicPD3)
                .add("PARAM_31", VariableName.vaPicPD4)
                .add("PARAM_32", VariableName.vaPicPD5)
                .add("PARAM_33", VariableName.vaPicPD6)
                .add("PARAM_34", VariableName.vaPicPD7)
                .add("PARAM_35", VariableName.vaPicPD8)
                .add("PARAM_36", VariableName.vaPicFT1)
                .add("PARAM_37", VariableName.vaPicFT2)
                .add("PARAM_38", VariableName.vaPicFT3)
                .add("PARAM_39", VariableName.vaPicFT4)
                .add("PARAM_40", VariableName.vaPicRI1)
                .add("PARAM_41", VariableName.vaPicRI2)
                .add("PARAM_42", VariableName.vaRemarkText1)
                .add("PARAM_43", VariableName.vaRemarkText2)
                .add("PARAM_44", VariableName.vaRemarkText3)
                .add("PARAM_45", VariableName.vaRemarkText4)

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

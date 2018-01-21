package resource;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.ton.furnituretabapplication.DatabaseHelper;
import com.example.ton.furnituretabapplication.Home;
import com.example.ton.furnituretabapplication.SubActivity;

/**
 * Created by marisalom on 17/01/2018.
 */

public class AsyncTaskLogin extends AsyncTask<String, Void, String> {

    private Activity activity;
    private ProgressDialog progressDialog;
    private String getUser,getPassword;
    private Handler handler = new Handler();

    //DB
    SQLiteDatabase mDb;
    DatabaseHelper mHelper;

    public AsyncTaskLogin(Activity act, String loginUser, String loginPassword){
        this.activity = act;
        this.getUser = loginUser;
        this.getPassword = loginPassword;
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
                createDB();

                /// Login
                final String errDesc = mHelper.checkUserLogin(getUser, getPassword, getDeviceImei(activity));

                handler.postDelayed(new Runnable() {
                    public void run() {
                        progressDialog.dismiss();

                        if (errDesc.equals("")) {
                            Intent intent = new Intent(activity, SubActivity.class);
                            activity.startActivity(intent);
                            activity.finish();
                        } else {
                            activity.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(activity, errDesc, Toast.LENGTH_SHORT).show();
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
    private void createDB(){
        //SQLite
        mHelper = new DatabaseHelper(activity);
        mDb = mHelper.getWritableDatabase();
    }

    /** IMEI*/
    @SuppressLint("MissingPermission")
    public static String getDeviceImei(Context ctx) {
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
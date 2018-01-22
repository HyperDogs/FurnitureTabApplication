package com.example.ton.furnituretabapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marisalom on 17/01/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "SQLiteDatabase.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_USERLOGIN = "TBUserLogin";

    public static final String COL_ULUSERLOGINID = "ulUserLoginId";
    public static final String COL_ULNAME = "ulName";
    public static final String COL_ULPASS = "ulPass";
    public static final String COL_ULDESC = "ulDesc";
    public static final String COL_ULGROUPID = "ulGroupId";
    public static final String COL_ULSTATUS = "ulStatus";
    public static final String COL_ULEMPLOYEEID = "ulEmployeeId";
    public static final String COL_ULBRANCHID = "ulBranchId";
    public static final String COL_ULSETPERMISSION = "ulSetPermission";
    public static final String COL_ULREMOTEADDR = "ulRemoteAddr";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTABLE_USERLOGIN(db);
    }

    private void createTABLE_USERLOGIN(SQLiteDatabase db){
        String CREATE_USERLOGIN_TABLE = "CREATE TABLE " + TABLE_USERLOGIN + "("
                + COL_ULUSERLOGINID + " CHAR(10) PRIMARY KEY, "
                + COL_ULNAME + " VARCHAR(20), "
                + COL_ULPASS + " VARCHAR(80), "
                + COL_ULDESC + " VARCHAR(50), "
                + COL_ULGROUPID + " VARCHAR(10), "
                + COL_ULSTATUS + " CHAR(1), "
                + COL_ULEMPLOYEEID + " VARCHAR(10), "
                + COL_ULBRANCHID + " VARCHAR(10), "
                + COL_ULSETPERMISSION + " CHAR(1), "
                + COL_ULREMOTEADDR + " VARCHAR(15) "
                + ") ";
        db.execSQL(CREATE_USERLOGIN_TABLE);
        db.execSQL("INSERT INTO " + TABLE_USERLOGIN + " (" + COL_ULUSERLOGINID + ", " + COL_ULNAME + ", " + COL_ULPASS + ", " + COL_ULDESC + ", "+ COL_ULEMPLOYEEID +", " + COL_ULBRANCHID +" ) " +
                "VALUES " +
                "('admin', 'admin', '1234', '867007020839046', '01', '01'), " +
                "('party', '1589', 'Party Suwit', '867007020839046', '04', '01'), " +
                "('2', 'admin', 'admin', '357220073447263', '02', '01'), " +
                "('3', 'admin', 'admin', '357221073447261', '03', '01'), " +
                "('4', 'admin', 'admin', '000000000000000', '01', '01');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USERLOGIN);
        onCreate(db);
    }

    public String checkUserLogin(String userName, String passWord, String imei){
        String result = "";

        database = this.getReadableDatabase();
        try{
            database.beginTransaction();

            // CHECK USERNAME, PASSWORD
            Cursor cursor = database.query(TABLE_USERLOGIN, null, COL_ULUSERLOGINID + " = ? AND " + COL_ULPASS + " = ? ", new String[]{userName, passWord}, null, null, null);
            if(cursor.getCount() > 0){
                result = "";
            } else {
                result = "Invalid Username or Password";
            }
            cursor.close();

            // CHECK LOGIN
            if(result.equals("")) {
                cursor = database.query(TABLE_USERLOGIN, null, COL_ULUSERLOGINID + " = ? AND " + COL_ULPASS + " = ? AND " + COL_ULDESC + " = ? ", new String[]{userName, passWord, imei}, null, null, null);
                if (cursor.getCount() > 0) {
                    result = "";
                } else {
                    result = "Your device is not support !!";
                }
                cursor.close();
            }
            database.setTransactionSuccessful();
        }catch (SQLException e){
            result = e.getMessage();
        }finally {
            database.endTransaction();
            database.close();
        }

        return result;
    }
}

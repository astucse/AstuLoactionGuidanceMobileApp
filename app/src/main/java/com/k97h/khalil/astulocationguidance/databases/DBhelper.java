package com.k97h.khalil.astulocationguidance.databases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.k97h.khalil.astulocationguidance.adapters.LocationAdapter;
import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBName="locationdb.db";
    @SuppressLint("SdCardPath")
    public static final String DBLocation="/data/data/com.k97h.khalil.astulocationguidance/databases/";
    private Context mcontext;
    private SQLiteDatabase msqLiteDatabase;


    public DBhelper(Context context) {
        super(context, DBName, null, 1);
        this.mcontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void openDatabase(){
        String path=mcontext.getDatabasePath(DBName).getPath();

        if(msqLiteDatabase!=null && msqLiteDatabase.isOpen()){
            return;
        }
        msqLiteDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
    }

    private void closeDatabase(){
        if(msqLiteDatabase!=null){
            msqLiteDatabase.close();
        }
    }

    public List<LocationData> getLocationList() {
        List<LocationData> data=new ArrayList<>();

        LocationData locationData=new LocationData("kerayu",1,"kerayu is astu",null,8.559088,39.285646);
        data.add(locationData);

        return data;
    }
}

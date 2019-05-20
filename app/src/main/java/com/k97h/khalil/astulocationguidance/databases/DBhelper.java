package com.k97h.khalil.astulocationguidance.databases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.k97h.khalil.astulocationguidance.models.LocationData;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBName= "locationdata.sqlite3";
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

    public List<LocationData> getLocationList(String category) {

        LocationData locationData;
        List<LocationData> data=new ArrayList<>();
        openDatabase();
        Cursor cursor=msqLiteDatabase.rawQuery("Select * From locations Where category like ?", new String[]{category});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            locationData=new LocationData(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getDouble(4),cursor.getDouble(5));
            data.add(locationData);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return data;
    }

    public List<LocationData> getLocationList() {
        LocationData locationData;
        List<LocationData> data=new ArrayList<>();
        openDatabase();
        Cursor cursor=msqLiteDatabase.rawQuery("Select * From locations where favorite=1",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            locationData=new LocationData(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getDouble(4),cursor.getDouble(5));
            data.add(locationData);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();

        return data;
    }
    public void updateHistory(int id, int fav){

        openDatabase();

        msqLiteDatabase.execSQL("UPDATE locations SET favorite ="+ fav +" WHERE id ="+id);
        closeDatabase();
    }
    public boolean isFav(int id){
        openDatabase();
    Cursor x=msqLiteDatabase.rawQuery("select * from locations where favorite=1 and id="+id,null);
        x.moveToFirst();
        if(!x.isAfterLast()){
            closeDatabase();
            return false;
        }
        closeDatabase();
        return true;
    }
}

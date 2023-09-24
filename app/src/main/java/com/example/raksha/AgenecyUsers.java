package com.example.raksha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AgenecyUsers extends SQLiteOpenHelper {
    String ag_name, h_no, password, type, admin;
    private static final String DATABASE_NAME = "agencydb";
    private static final String TABLE_NAME = "agencyusers";
    AgenecyUsers(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table agencyusers (Agency TEXT, Helpline TEXT, AdminKey TEXT, Password TEXT,Lat Double, Long Double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists agencyusers");
    }
    public boolean addText(String agency, String helpline, String adminKey, String password, Double Lat, Double Long){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Agency",agency);
        contentValues.put("Helpline",helpline);
        contentValues.put("AdminKey",adminKey);
        contentValues.put("Password",password);
        contentValues.put("Latitude",Lat);
        contentValues.put("Longitude",Long);
        long result = sqLiteDatabase.insert("agencyusers",null,contentValues);
        if(result==-1)return false;
        else return true;
    }
    public boolean checkuser(String helpline){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from agencyusers where helpline = ?",new String[]{helpline});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

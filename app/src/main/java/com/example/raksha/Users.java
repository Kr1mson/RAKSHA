package com.example.raksha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Users extends SQLiteOpenHelper {
    String ag_name, h_no, password, type, admin;
    private static final String DATABASE_NAME = "usersdb";
    private static final String TABLE_NAME = "users";
    Users(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (Fullname TEXT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public boolean addText(String fullname, String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FullName",fullname);
        contentValues.put("Email",email);
        contentValues.put("Password",password);
        long result = sqLiteDatabase.insert("users",null,contentValues);
        if(result==-1)return false;
        else return true;
    }
    public boolean checkuser(String email){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where Email = ?",new String[]{email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkAvailable(String email, String password){
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cursor = mydb.rawQuery("select * from users where Email = ? and Password = ?",new String[]{email, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

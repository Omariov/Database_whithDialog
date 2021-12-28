package com.example.mydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){super(context,"Userdata.db",null,1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(nom TEXT primary Key,ville TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String nom,String ville )
    {
        SQLiteDatabase db=this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("nom",nom);
         contentValues.put("ville",ville);
        long result=db.insert("Userdetails",null,contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Userdetails",null);
        return cursor;
    }




}

package com.example.android.sociohack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDB extends SQLiteOpenHelper {
    public MyDB(Context context) {
        super(context, "mydb", null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table menu(id text,item text,price text,qty text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("drop table if exists menu");
        db.execSQL("create table menu(id text,item text,price text,qty text)");

    }
public  boolean insertMenu(String id,String item,String price,String qty){
    Log.e("Db out",id+" "+qty);
        SQLiteDatabase db=getWritableDatabase();
        SQLiteDatabase db1=getReadableDatabase();
    Cursor c=db1.rawQuery("select * from menu where id='"+id+"'",null);
    if(c.getCount()>0){
        try {
            db.execSQL("update menu set qty='"+qty+"' where id='"+id+"'");
            Log.e("HIHIHI","6532.0");
        return true;
        }catch (Exception e){
            Log.e("excekjjn","hjnvm");
            return false;
        }
    }else{
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", id);
            cv.put("item", item);
            cv.put("price", price);
            cv.put("qty", qty);
            db.insert("menu", null, cv);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

}
public Cursor getMenu(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from menu",null);
return c;
    }

}

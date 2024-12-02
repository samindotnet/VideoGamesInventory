package com.example.videogames;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DatabaseName ="Projectdb";
    public static final String TableName="Videogames";
    public static final String Column1="ID";
    public static final String Column2="Title";
    public static final String Column3="Developers";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TableName+"(ID Integer primary key autoincrement, Title text, Developers text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TableName);
        onCreate(db);
    }
    public boolean InsertData(String Title, String Developers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column2,Title);
        contentValues.put(Column3,Developers);
        long result = db.insert(TableName,null,contentValues);
        if(result ==-1)
            return false;
        else
            return true;
    }
    public boolean UpdateData(String ID,String Title,String Developers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column1,ID);
        contentValues.put(Column2,Title);
        contentValues.put(Column3,Developers);
        db.update(TableName,contentValues,"ID=?",new String[] {ID});
        return true;
    }
    public Integer DeleteData(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName,"ID=?",new String[] {ID});
    }
    public Cursor showallData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor acura = db.rawQuery("select * from "+TableName,null);
        return acura;
    }
}

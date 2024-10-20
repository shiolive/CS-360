package com.example.projectsrule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;


public class LoginDatabase extends SQLiteOpenHelper {

    public static final String DatabaseName = "loginDB";
    public LoginDatabase(Context context){
        super(context, "loginDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB){
        DB.execSQL("Create Table users(username TEXT primary key, password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int j){
        DB.execSQL("drop table if exists users");
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = DB.insert("users", null, contentValues);
        if (result==-1) return false;
        else return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkUserPassword(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}

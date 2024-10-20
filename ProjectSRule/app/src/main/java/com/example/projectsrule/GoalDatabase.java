package com.example.projectsrule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class GoalDatabase extends SQLiteOpenHelper {
    public static final String DatabaseName = "goalDB";
    public GoalDatabase(Context context){
        super(context, "goalDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB){
        DB.execSQL("Create Table goalWeight(goal TEXT primary key)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int j){
    }
    public Boolean insertData(String goal){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal", goal);
        long result = DB.insert("goalWeight", null, contentValues);
        if (result==-1) return false;
        else return true;
    }

}

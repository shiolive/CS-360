package com.example.projectsrule;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String record_id = "ID";
    public static final String weight_table = "weightTable";
    public static final String user_date = "userDate";
    public static final String user_weight = "userWeight";

    public Database(@Nullable Context context) {

        super(context, "user_weight.db", null, 1);
    }

    // create the database when the first record is created
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "Create Table " + weight_table + " (" + record_id + " Integer Primary Key AutoIncrement, " + user_date + " Text, " + user_weight + " Integer)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

    }

    public boolean addOne(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(user_date, userModel.getDate());
        cv.put(user_weight, userModel.getWeight());

        long insert = db.insert(weight_table, null, cv);
        if (insert == -1) {return false;        }
        else {return true;}
    }

    public boolean removeOne(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.remove(user_date);
        cv.remove(user_weight);

        long insert = db.insert(weight_table, null, cv);
        if (insert == -1) {return false;}
        else {return true;}
    }


    public List<UserModel> getEveryone() {
        List<UserModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + weight_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {

                int idNum = cursor.getInt(0);
                String userDate = cursor.getString(1);
                int userWeight = cursor.getInt(2);

                UserModel newEntry = new UserModel(idNum, userDate, userWeight);
                returnList.add(newEntry);

            } while (cursor.moveToNext());
        }
        else {}


        cursor.close();
        db.close();
        return returnList;
    }
}
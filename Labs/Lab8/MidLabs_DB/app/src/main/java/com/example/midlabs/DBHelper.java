package com.example.midlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "story.db", factory, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS info");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE info (Number Text,story Text);";
        db.execSQL(query);
    }

    public void insert(String number,String story){
        ContentValues  values=new ContentValues();
        values.put("Number",number);
        values.put("story",story);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("info",null,values);
        db.close();

    }
    public String print(String number){
        String string="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM info WHERE Number=\""+number+"\";";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();


        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("Number"))!=null)
                string+=c.getString(c.getColumnIndex("story"));

            c.moveToNext();
            break;
        }
        db.close();
        return  string;
    }
}

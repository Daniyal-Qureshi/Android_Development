package com.example.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "SIBA.db", factory, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS info");
        onCreate(db);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE info (Name TEXT,Phone TEXT,Street TEXT,Email TEXT,City Text);";
        db.execSQL(query);


    }
    public void insert(String Name,String Phone,String Street,String Email,String City){
        ContentValues  values=new ContentValues();
        values.put("Name",Name);
        values.put("Phone",Phone);
        values.put("Street",Street);
        values.put("City",Name);
        values.put("Email",Email);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("info",null,values);
        db.close();

    }
    public String print(String name){
        String string="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM info WHERE Name=\""+name+"\";";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();



        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("Name"))!=null)
                string+=c.getString(c.getColumnIndex("Name"));

            c.moveToNext();
            break;
        }
        db.close();
        return  string;
    }
}

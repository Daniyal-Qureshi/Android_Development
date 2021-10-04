package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView view;
    ArrayAdapter<String> arrayAdapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Retrieve student records
        String URL = "content://com.example.dictionary/login/" ;
        Uri dictionaryURI = Uri.parse ( URL );
        Cursor c = getContentResolver().query( dictionaryURI, null , null , null , null );
        list=new ArrayList<String >();
        c.moveToFirst();

            do {
                       list.add(0,c.getString (c.getColumnIndex ("name")));

            } while ( c.moveToNext ());

        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        view=findViewById(R.id.listview);
        view.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.search:
                    getData("Enter a word to search");
                    break;
                case R.id.add:
                    getData("Insert word to dictionary");
                    break;

            }



        return super.onOptionsItemSelected(item);
    }

    public void getData(String title){


        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle(title);
        EditText input=new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(title.equals("Enter a word to search")){
                    searchword(input.getText().toString());

                }
                else
                    addword(input.getText().toString());

            }

        });
        alert.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    public void addword(String word){
        ContentValues values = new ContentValues();

        values.put ("name", word);
        Uri uri = getContentResolver (). insert (dict.CONTENT_URI , values );
        list.add(0,word);
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        view.setAdapter(arrayAdapter);

    }
    public void searchword(String word){

        String URL = "content://com.example.dictionary/login/" ;
        Uri dictionaryURI = Uri.parse ( URL );
        Cursor c = getContentResolver().query( dictionaryURI, null , "name=?" , new String[]{word} , null );
        if(c.getCount()==0)
            return;

        c.moveToFirst();
        List<String> list2=new ArrayList<String>();
        do {
            list2.add(c.getString (c.getColumnIndex ("name")));

        } while ( c.moveToNext ());
        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list2);
        view.setAdapter(arrayAdapter);

    }
}

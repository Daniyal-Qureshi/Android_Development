package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class database extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");


        TextView text=findViewById(R.id.name);
        DBHelper db=new DBHelper(this,null,null,1);


        text.setText(db.print(name));

    }
}
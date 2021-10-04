package com.example.midlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class story extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Intent intent=getIntent();
        String story=intent.getStringExtra("story");
        TextView txt=(TextView)findViewById(R.id.textView5);
        txt.setText(story);


    }
}
package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MadLibStory extends AppCompatActivity implements View.OnTouchListener {
    Intent intent;
    TextView txt;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_lib_story);
        txt=findViewById(R.id.str);
        intent=getIntent();
        String temp=intent.getStringExtra("Story");
        txt.setText(temp);
        txt.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        txt.setMovementMethod(new ScrollingMovementMethod());
        return false;
    }
    public void another(View view){
        finish();
    }
}
package com.example.mood_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void change(View view) {
        Button btn = (Button) view;
        btn.setText("Done");
        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.full);
        TextView txt = (TextView) findViewById(R.id.text);
        txt.setText("I am so full");

    }
}
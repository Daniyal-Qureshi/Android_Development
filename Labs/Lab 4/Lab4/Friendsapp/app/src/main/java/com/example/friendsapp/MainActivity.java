package com.example.friendsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void change(View view){
        ImageButton btn=(ImageButton)view;
        Intent intent=new Intent(this,Details.class);

        intent.putExtra("tag",btn.getTag().toString());

    startActivityForResult(intent,123);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==123)
        {
            String returnString = data.getStringExtra("rate");
            Toast.makeText(this,"You rated "+returnString,Toast.LENGTH_LONG).show();
        }
    }
}
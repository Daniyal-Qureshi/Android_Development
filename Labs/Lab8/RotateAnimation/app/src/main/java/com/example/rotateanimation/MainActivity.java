package com.example.rotateanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int counter=0;
    Boolean check;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref=getSharedPreferences("c",MODE_PRIVATE);
        img=findViewById(R.id.imageView);

        check=true;
            counter=pref.getInt("counter",0);

        img.setRotation(counter);

    }

    public void toggle(View view) {
        SharedPreferences pref=getSharedPreferences("c",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();

        if(check) {
            editor.putInt("counter",counter);
            editor.commit();
            check = false;
        }
        else
            check=true;

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
             while(check){
                counter+=20;
                 if(counter==360)
                     counter=0;
                 img.setRotation(counter);
                 try {
                     Thread.sleep(50);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

             }
            }

        });
        thread.start();


    }
}
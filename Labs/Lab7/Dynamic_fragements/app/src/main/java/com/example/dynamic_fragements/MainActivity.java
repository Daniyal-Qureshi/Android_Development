package com.example.dynamic_fragements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();




    }
    public void show(View view){
        Button btn = (Button)view;
        if(btn.getText().toString().equalsIgnoreCase("Setting"))
        {
            transaction=manager.beginTransaction();
            transaction.replace(R.id.frag,new setting());
            transaction.commit();


        }
       else if(btn.getText().toString().equalsIgnoreCase("Notification"))
        {
            transaction=manager.beginTransaction();

            transaction.replace(R.id.frag,new notification());
            transaction.commit();


        }
        if(btn.getText().toString().equalsIgnoreCase("Alarm"))
        {
            transaction=manager.beginTransaction();
            transaction.replace(R.id.frag,new alarm());
            transaction.commit();
        }
        if(btn.getText().toString().equalsIgnoreCase("Location"))
        {
            transaction=manager.beginTransaction();
            transaction.replace(R.id.frag,new location());
            transaction.commit();

        }
        if(btn.getText().toString().equalsIgnoreCase("Battery"))
        {
            transaction=manager.beginTransaction();

            transaction.replace(R.id.frag,new Battery());
            transaction.commit();
        }




    }

}
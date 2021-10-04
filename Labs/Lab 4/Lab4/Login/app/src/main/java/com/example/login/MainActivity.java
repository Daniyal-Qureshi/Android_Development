package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view){
        EditText Email=(EditText)findViewById(R.id.Email);
        EditText Password=(EditText)findViewById(R.id.password);


        SharedPreferences prefs=getSharedPreferences("user",MODE_PRIVATE);
        String d=prefs.getString(Email.getText().toString(),"none");
        Log.i("val",d);

        if(d.equals(Password.getText().toString()))
            Toast.makeText(this,"Login Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Sign up first", Toast.LENGTH_LONG).show();



    }

    public void signup(View view){
    Intent intent=new Intent(this,Signup.class);
    startActivity(intent);
    }

}
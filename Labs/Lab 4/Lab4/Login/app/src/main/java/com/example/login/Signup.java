package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signup(View view){
        EditText Email=(EditText)findViewById(R.id.Email);
        EditText Password=(EditText)findViewById(R.id.password);

        SharedPreferences prefs=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();

        editor.putString(Email.getText().toString(),Password.getText().toString());
        editor.commit();
        Toast.makeText(this,"You are registered",Toast.LENGTH_LONG).show();
    }

    public void login(View view){
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);

    }
}
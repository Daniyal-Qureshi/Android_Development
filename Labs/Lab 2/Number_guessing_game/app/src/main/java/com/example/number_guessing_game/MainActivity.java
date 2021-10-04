package com.example.number_guessing_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
public TextView txt;
public EditText textfield;
int NumberToGuess;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand=new Random();
        NumberToGuess=rand.nextInt(1000);

    }
    public void Validate(View view){
         txt=(TextView) findViewById(R.id.text);
         textfield=(EditText)findViewById(R.id.input);

        int guess=Integer.parseInt(textfield.getText().toString());
        if(guess==NumberToGuess)
            txt.setText("You win");

        else if(guess<NumberToGuess)
               txt.setText("You are low");
        else
            txt.setText("You are high");

    }
}
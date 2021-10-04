package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.textView);
        txt.setText("Application is in creation state");
    }

    @Override
    protected void onStart() {
        super.onStart();
        txt.setText(txt.getText().toString()+"\nApplication is in start state");
    }

    @Override
    protected void onResume() {
        super.onResume();
        txt.setText(txt.getText().toString()+"\nApplication is in resume state");
    }

    @Override
    protected void onPause() {
        super.onPause();
        txt.setText(txt.getText().toString()+"\nApplication is in pause state");

    }

    @Override
    protected void onStop() {
        super.onStop();
        txt.setText(txt.getText().toString()+"\nApplication is in stop state");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        txt.setText(txt.getText().toString()+"\nApplication is in restart state");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        txt.setText(txt.getText().toString()+"\nApplication is in destroy state");
    }
}
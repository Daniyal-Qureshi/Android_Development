package com.example.coffee_ordering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    TextView summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.textView3);
        summary=(TextView)findViewById(R.id.summary);

    }
    public void add(View view){
        int text=Integer.parseInt(txt.getText().toString())+1;
        txt.setText(""+text);
    }

    public void sub(View view){
        int text=Integer.parseInt(txt.getText().toString())-1;
        if(text>=0)
            txt.setText(""+text);


    }

    public void ShowSummary(View view){
         String cream="no";
         String choc="no";
         int qty=Integer.parseInt(txt.getText().toString());
         float amount=qty*4;
         CheckBox bx1=(CheckBox)findViewById(R.id.checkBox);
         CheckBox bx2=(CheckBox)findViewById(R.id.checkBox2);

         if(bx1.isChecked()) {
             cream = "yes";
            amount+=0.50*qty;
         }
         if(bx2.isChecked()) {
             choc = "yes";
            amount+=qty;
         }

        summary.setText("Add whipped cream ?"+cream+"\nAdd chocolate ?"+choc+"\nQuantity: "+qty
        +"\n\nPrice :$"+amount+"\nTHANK YOU!");


    }
}
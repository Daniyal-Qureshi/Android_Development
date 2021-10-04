package com.example.midlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class words extends AppCompatActivity {


    TextView leftwords,placingwords;
    EditText editText;
    ArrayList<String> matches;
    String story;

    int counter,index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        leftwords=(TextView)findViewById(R.id.leftwords);
        placingwords=(TextView)findViewById(R.id.word);
        editText=(EditText) findViewById(R.id.editext);


    //Read files
        Random rand=new Random();
        int number=rand.nextInt(5);
        Scanner scan;
        if(number==0)
            scan = new Scanner(getResources().openRawResource(R.raw.zero));
        else if(number==1)
            scan = new Scanner(getResources().openRawResource(R.raw.one));
        else if(number==2)
            scan = new Scanner(getResources().openRawResource(R.raw.two));
        else  if(number==3)
            scan = new Scanner(getResources().openRawResource(R.raw.three));
        else
            scan = new Scanner(getResources().openRawResource(R.raw.four));



// read entire file
        story = "";
        while (scan.hasNextLine())
           story+= scan.nextLine();

        scan.close();
        Log.i("val",story);




        Pattern pattern = Pattern.compile("\\<(.*?)\\>");
        Matcher matcher = pattern.matcher(story);

        matches = new ArrayList<String>();
        while(matcher.find())
            matches.add(matcher.group());


        counter=matches.size();
        index=0;
        leftwords.setText(counter +"words(s) left");
        String w=matches.get(index);
        w=w.replace("<","");
        w=w.replace(">","");
        placingwords.setText("please type a/an "+w);
        editText.setHint(w);
        Log.i("dic",matches.toString());
    }



    public  void Ok(View view){
        if(TextUtils.isEmpty(editText.getText().toString()))
        {
            editText.setError("Empty field");
            return;
        }

        Toast.makeText(this,"Great keep  going",Toast.LENGTH_LONG).show();
        leftwords.setText(--counter +"words(s) left");
        String w=matches.get(index);
        story = story.replaceFirst(w, editText.getText().toString());


                if(counter==0)
        {
            Intent intent=new Intent(this,story.class);
            intent.putExtra("story",story);
            startActivity(intent);


        }



        Log.i("story",story);
        w=matches.get(++index);
        w=w.replace("<","");
        w=w.replace(">","");
        placingwords.setText("please type a/an "+w);
        editText.setHint(w);
        editText.setText("");

    }

}
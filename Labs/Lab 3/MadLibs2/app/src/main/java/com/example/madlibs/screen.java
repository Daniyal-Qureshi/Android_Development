package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class screen extends AppCompatActivity {
    SharedPreferences prefs;
    TextView msg;
    EditText inp;
    Button ok;
    String story;
    String hints;
    String words="";
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

    }

    @Override
    protected void onResume() {
        super.onResume();
        msg=findViewById(R.id.msg);
        inp=findViewById(R.id.inword);
        ok=findViewById(R.id.ok);
        prefs=getSharedPreferences("Stories",MODE_PRIVATE);
        Set<String> fetch =prefs.getStringSet("story",null);
        List<String> list=new ArrayList<String>(fetch);
        Random rand=new Random();
        i=0;
        i=rand.nextInt(list.size());
        story= list.get(i);
        i=0;
        int j=0;
        char[] arr=story.toCharArray();
        hints="";
        for(int k=0;k<arr.length;k++){
            if(arr[k]=='<'){
                i++;
                if(j<arr.length){
                    j++;
                    for(;arr[j]!='>';j++){
                        hints+=arr[j];
                        k++;
                    }
                    j--;
                    hints+="&";
                }
            }
            j++;
        }
        msg.setText(i+" word(s) left");
        inp.setHint(hints.substring(0,hints.indexOf("&")));
    }

    public void place(View view){
        String inpt=inp.getText().toString();
        String hin=inp.getHint().toString();

        if(!(inpt.equals(""))){
           story=story.replaceFirst(story.substring(story.indexOf('<'),story.indexOf('>')+1),inpt);
           hints=hints.replaceFirst(hints.substring(0,hints.indexOf("&")),"");
           if(i!=1)
                hints=hints.replaceFirst("&","");
            inp.setText("");

            inp.setHint(hints.substring(0,hints.indexOf("&")));
            i--;
            if(i==0)
            {
                Intent intent=new Intent(this,MadLibStory.class);
                intent.putExtra("Story",story);
                startActivity(intent);
            }
            msg.setText(i+" word(s) left");
        }
        else{
            Toast.makeText(this,"Enter the Word",Toast.LENGTH_SHORT).show();
        }
    }

}
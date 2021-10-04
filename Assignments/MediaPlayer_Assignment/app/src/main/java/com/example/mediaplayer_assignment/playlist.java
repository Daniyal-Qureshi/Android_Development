package com.example.mediaplayer_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class playlist extends AppCompatActivity {
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Intent intent=getIntent();

        int counter=intent.getIntExtra("counter",-1);
        String[] playlist_names=new String[counter];
        SharedPreferences prefs=getSharedPreferences("playlistnames",MODE_PRIVATE);
        for (int i=0;i<counter;i++)
            playlist_names[i]=prefs.getString(""+i,"-1");


        listview=findViewById(R.id.listView);
        Intent intent1=new Intent(this,MainActivity.class);

        ListAdapter listAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,playlist_names);
        listview.setAdapter(listAdapter);
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent1.putExtra("name",playlist_names[position]);
                        startActivity(intent1);
                        finish();
                    }
                }


        );



    }


}
package com.example.mediaplayer_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    MediaPlayer mp;
    SharedPreferences.Editor editor;
    int counter;
    boolean check;
    boolean add;
    String playlist_name;
    PrintStream output;
    boolean checkboxes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check=false;
        add=false;
        checkboxes=false;
        Intent intent=getIntent();
        TextView playlistname=findViewById(R.id.playlistname);
        listview=findViewById(R.id.listView);

        if(intent.getExtras()!=null) {
            playlist_name=intent.getStringExtra("name");
            playlistname.setText(playlist_name);
            add=true;
            try {
                output = new PrintStream(openFileOutput(playlist_name+".txt", MODE_APPEND));

            }
            catch (Exception e){}

        }
        else
            playlist_name="songs";





        SharedPreferences prefs=getSharedPreferences("playlistnames",MODE_PRIVATE);
        editor=prefs.edit();





        counter=0;
        while(!prefs.getString(""+counter,"-1").equals("-1"))
            counter+=1;




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);
        }
        else
            getSongs(playlist_name);


    }



    public void getSongs(String listname) {
        String[] song_names={};
        ArrayList<File> list=new ArrayList<File>();

        Songs.files = (ArrayList<File>) Songs.getListFiles(Environment.getExternalStorageDirectory());

         if (!listname.equals("songs"))
                list=getPlaylist();



        if(list.size()==0)
                list=Songs.files;


        Songs.current=list;

        song_names = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
            song_names[i] = list.get(i).getName().toString();


         Intent intent=new Intent(this,PlayerActivity.class);

        ListAdapter  listAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,song_names);


        if(check)
            listAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,song_names);


        listview.setAdapter(listAdapter);

        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if(!checkboxes) {
                            Log.i("cl","click");
                            intent.putExtra("index", position);
                            startActivity(intent);
                        }
                    }
                }


        );



    }

    public ArrayList<File> getPlaylist(){
        //Read from playlist songs file
        ArrayList<Integer> indices=new ArrayList<Integer>();
        try {
            Scanner scan = new Scanner(openFileInput(playlist_name+".txt"));
            while(scan.hasNextLine())
                   indices.add(Integer.parseInt(scan.nextLine()));
            scan.close();

            ArrayList<File> list=new ArrayList<File>();


            for (int i = 0; i < Songs.files.size(); i++)
                if(indices.contains(i))
                    list.add(Songs.files.get(i));



            return list;

        }
        catch (Exception ee){}



        return null;
    }





    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addsong:
                check=true;
                checkboxes=true;
                invalidateOptionsMenu();
                getSongs("songs");
                break;

            case R.id.addplaylist:
                showForgotDialog(this);

                break;
            case R.id.playlist:
                Intent intent=new Intent(this,playlist.class);
                intent.putExtra("counter",counter);
                startActivity(intent);
                finish();
                break;

            case R.id.check:
            for (int i=0;i<listview.getCount();i++)
                if(listview.isItemChecked(i))
                    output.println(i);
                output.close();
            check=false;
            add=false;
            checkboxes=false;
            getSongs(playlist_name);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getSongs(playlist_name);
                }
                break;


        }
    }


    private void showForgotDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("New Playlist")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.putString(""+counter,String.valueOf(taskEditText.getText()));
                        editor.commit();
                        ++counter;

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        menu.findItem(R.id.check).setVisible(check);
        menu.findItem(R.id.addsong).setVisible(add);

        return true;

    }



}
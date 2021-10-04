  package com.example.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

  public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Map<String,String>> data=new ArrayList<Map<String, String>>();
        SimpleAdapter adapter=new SimpleAdapter(this,data, android.R.layout.simple_expandable_list_item_2,
                new String[]{"title","subtitle"},new int[]{android.R.id.text1,android.R.id.text2}
                );

        ListView listView=findViewById(R.id.list);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){


            Cursor c=getContentResolver().query(Uri.parse("content://sms/inbox"),null,null,null,null);

            c.moveToFirst();
            while(c.moveToNext()){
                Map<String,String> d=new HashMap<String, String>(2);
                d.put("title",c.getString(c.getColumnIndex("address")));
                d.put("subtitle",c.getString(c.getColumnIndex("body")));
                data.add(d);


            }
            listView.setAdapter(adapter);

        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},123);

        }


    }
}
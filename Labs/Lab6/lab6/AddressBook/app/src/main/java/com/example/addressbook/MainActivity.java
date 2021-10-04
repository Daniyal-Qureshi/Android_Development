package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db=new DBHelper(this,null,null,1);



    }
    public void save(View view){

        EditText Name=findViewById(R.id.text1);
        EditText Phone=findViewById(R.id.text2);
        EditText street=findViewById(R.id.text3);
        EditText Email=findViewById(R.id.text4);
        EditText city=findViewById(R.id.text5);
        db.insert(Name.getText().toString(),Phone.getText().toString(),
                street.getText().toString(),Email.getText().toString(),city.getText().toString());


        Intent intent=new Intent(this,database.class);
        intent.putExtra("name",Name.getText().toString());
        startActivity(intent);
    }

}
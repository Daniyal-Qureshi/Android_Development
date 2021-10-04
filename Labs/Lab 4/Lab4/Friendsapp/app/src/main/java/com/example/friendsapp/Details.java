package com.example.friendsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    String name;
    Intent intent;
    float Rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent =getIntent();
        String tag=intent.getStringExtra("tag");
        ImageView img=(ImageView) findViewById(R.id.imageView);
        TextView txt=(TextView)findViewById(R.id.txt);
        RatingBar ratingBar=(RatingBar)findViewById(R.id.rating);
        String[] str=getResources().getStringArray(R.array.friend_details);
        SharedPreferences prefs=getSharedPreferences("user",MODE_PRIVATE);
        Rating=0;

        if(tag.equals("1"))
        {

        img.setImageResource(R.drawable.chandler);
        txt.setText(str[0]);
        name="chandler";

        }

        else if(tag.equals("2"))
        {
            img.setImageResource(R.drawable.monica);
            txt.setText(str[2]);
            name="monica";
        }

        else if(tag.equals("3"))
        {
            img.setImageResource(R.drawable.rachel);
            txt.setText(str[4]);
            name="rachel";
        }

        else if(tag.equals("4"))
        {
            img.setImageResource(R.drawable.joey);
            txt.setText(str[1]);
            name="joey";
        }

        else if(tag.equals("5"))
        {
            img.setImageResource(R.drawable.phoebe);
            txt.setText(str[3]);
            name="phoebe";
        }

        else if(tag.equals("6"))
        {
            img.setImageResource(R.drawable.ross);
            txt.setText(str[5]);
            name="ross";
        }
        String d=prefs.getString(name,"none");
        if(!d.equals("none")) {
            ratingBar.setRating(Float.parseFloat(d));
            Rating=Float.parseFloat(d);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                SharedPreferences prefs=getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString(name,""+rating);
                Rating=rating;
                editor.commit();
                intent.putExtra("rate",""+Rating);

            }
        });
        intent.putExtra("rate",""+Rating);
        Log.i("value",""+Rating);
        setResult(123, intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
package com.example.friends_app_fragements;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class details extends Fragment {
    String name;
    float Rating;
    TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.fragment_details,container,false);
        String tag=getArguments().getString("tag");
        ImageView img= root.findViewById(R.id.imageView);

        txt=root.findViewById(R.id.txt);
        RatingBar ratingBar=root.findViewById(R.id.rating);
        String[] str=root.getResources().getStringArray(R.array.friend_details);
        SharedPreferences prefs=getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
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

                SharedPreferences prefs=getActivity().getSharedPreferences("user",getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString(name,""+rating);
                Rating=rating;
                editor.commit();


            }
        });

        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {

                    main main_frag=new main();
                    Bundle bundle=new Bundle();
                    bundle.putFloat("rating",Rating);
                    main_frag.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.fragement, main_frag).commit();



                    return true;
                }
                return  false;
                   }

        });



        return root;
  }

//
//    public static void onBack(Context context){
//
//        context.getFragmentManager().beginTransaction().replace(R.id.fragement, new main()).commit();
//
//    }


}
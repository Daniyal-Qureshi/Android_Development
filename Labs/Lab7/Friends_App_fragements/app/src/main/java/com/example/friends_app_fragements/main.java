package com.example.friends_app_fragements;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class main extends Fragment implements  View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    Bundle bundle=getArguments();
    if(bundle!=null) {

        Toast.makeText(getActivity(), "You have rated" + bundle.getFloat("rating"), Toast.LENGTH_LONG).show();

    }
    //Log.i("ra",bundle.getFloat("rating")+"");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton btn=getActivity().findViewById(R.id.ImageButton);
        btn.setOnClickListener(this);
        btn=getActivity().findViewById(R.id.ImageButton2);
        btn.setOnClickListener(this);
        btn=getActivity().findViewById(R.id.ImageButton3);
        btn.setOnClickListener(this);
        btn=getActivity().findViewById(R.id.ImageButton4);
        btn.setOnClickListener(this);
        btn=getActivity().findViewById(R.id.ImageButton5);
        btn.setOnClickListener(this);
        btn=getActivity().findViewById(R.id.ImageButton6);
        btn.setOnClickListener(this);









    }

    @Override
    public void onClick(View v) {
        ImageButton btn=(ImageButton)v;
        details details_frag=new details();
        Bundle bundle=new Bundle();
        bundle.putString("tag",btn.getTag().toString());
        details_frag.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragement, details_frag).commit();

    }



}
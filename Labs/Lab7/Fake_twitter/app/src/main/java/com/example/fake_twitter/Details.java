package com.example.fake_twitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Details extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view=(ViewGroup)inflater.inflate(R.layout.fragment_details,container,false);
        String name=getArguments().getString("name");
        String[] tweets;
        if(name.equals("Bill Gates"))
          tweets=view.getResources().getStringArray(R.array.billgates);

        else if(name.equals("Mark Zuckerburg"))
             tweets=view.getResources().getStringArray(R.array.Mark);



        else
            tweets=view.getResources().getStringArray(R.array.elon);



        ListView list=view.findViewById(R.id.details_list);
        ListAdapter Adapter=
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,tweets);
        list.setAdapter(Adapter);


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().beginTransaction().replace(R.id.fragement,new List()).commit();

                    return true;
                }
                return  false;
            }

        });



        return  view;
    }
}
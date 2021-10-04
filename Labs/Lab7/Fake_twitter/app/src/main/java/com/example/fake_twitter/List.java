package com.example.fake_twitter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class List extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view=(ViewGroup)inflater.inflate(R.layout.fragment_list,container,false);

        String names[]={"Bill Gates","Mark Zuckerburg", "Elon Musk"};
        ListView list=view.findViewById(R.id.list);
        ListAdapter Adapter=
      new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,names);
        list.setAdapter(Adapter);

      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Details details_frag=new Details();
              Bundle bundle=new Bundle();
              bundle.putString("name",names[position]);
              details_frag.setArguments(bundle);
              getFragmentManager().beginTransaction().replace(R.id.fragement,details_frag).commit();


          }
      });


        return view;
    }
}
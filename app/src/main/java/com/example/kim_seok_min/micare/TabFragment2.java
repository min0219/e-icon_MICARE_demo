package com.example.kim_seok_min.micare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TabFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Intent intent = new Intent(getActivity(), MainActivity01.class);
        //.startActicity(intent)
//        ContextCompat.startActivity(getContext(), intent, new Bundle());
//        startActivity(intent);
        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }
}

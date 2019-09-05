package com.example.kim_seok_min.micare.readdbfromassets.ThirdAct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kim_seok_min.micare.R;

import java.util.ArrayList;

public class ListAdapter03 extends BaseAdapter {
    ArrayList<CategoryData03> items = new ArrayList<>();
    TextView countryName;

    public ListAdapter03(ArrayList<CategoryData03> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i).getCatName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        countryName = (TextView) v.findViewById(R.id.text1);
        countryName.setText(items.get(i).getCatName());
        return v;
    }
}

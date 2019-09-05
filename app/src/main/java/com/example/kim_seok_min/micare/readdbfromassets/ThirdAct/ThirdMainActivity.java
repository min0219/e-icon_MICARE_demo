package com.example.kim_seok_min.micare.readdbfromassets.ThirdAct;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kim_seok_min.micare.R;

import com.example.kim_seok_min.micare.readdbfromassets.FourthAct.FourthMainActivity;

import java.io.IOException;
import java.util.ArrayList;

public class ThirdMainActivity extends AppCompatActivity implements OnCategoryDataRetrieved03 {

    private GetCategoryDataAsyncTask03 getCategoryDataAsyncTask03;
    ListAdapter03 listAdapter;
    private ListView listView;
    private ProgressBar mProgressBar;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
        str = getIntent().getStringExtra("Medicine");

        init();

        //Preparing DB
        DatabaseOpenHelper03 databaseOpenHelper = new DatabaseOpenHelper03(ThirdMainActivity.this, getFilesDir().getAbsolutePath());
        try {
            databaseOpenHelper.prepareDataBase();
        } catch (IOException io) {
            throw new Error("Unable to create Database");
        }
        //GET and display Category list from DB
        displayHomePage(databaseOpenHelper);
    }

    private void displayHomePage(DatabaseOpenHelper03 databaseOpenHelper) {
        //Getting and Setting DATA to Adapter from DB
        Log.e("asdasd","a"+ databaseOpenHelper.toString());
        if (databaseOpenHelper != null) {
            //TODO call asynctask to load data
            mProgressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getCategoryDataAsyncTask03 = new GetCategoryDataAsyncTask03(databaseOpenHelper, str);
            getCategoryDataAsyncTask03.onCategoryDataRetrieved = this;
            getCategoryDataAsyncTask03.execute();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getCategoryDataAsyncTask03 != null) {
            getCategoryDataAsyncTask03.cancel(true);
        }
    }

    private void init() {
        mProgressBar = findViewById(R.id.insidePB03);
        listView = findViewById(R.id.recyclerView03);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), FourthMainActivity.class);
                intent.putExtra("Name", listAdapter.items.get(i).getCatName());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDataRetrieved(ArrayList<CategoryData03> categoryDataArrayList, boolean status) {
        mProgressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        Log.e("gf",categoryDataArrayList.size()+"");
        if (status && categoryDataArrayList.size() > 0) {

            listAdapter = new ListAdapter03(categoryDataArrayList);
            listView.setAdapter(listAdapter);
        }
    }


}


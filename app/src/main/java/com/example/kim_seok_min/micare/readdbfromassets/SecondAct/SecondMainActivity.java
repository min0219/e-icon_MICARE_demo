package com.example.kim_seok_min.micare.readdbfromassets.SecondAct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kim_seok_min.micare.R;
import com.example.kim_seok_min.micare.readdbfromassets.ThirdAct.ThirdMainActivity;


import java.io.IOException;
import java.util.ArrayList;

public class SecondMainActivity extends AppCompatActivity implements OnCategoryDataRetrieved02 {

    private GetCategoryDataAsyncTask02 getCategoryDataAsyncTask;
    ListAdapter02 listAdapter;
    private ListView listView;
    private ProgressBar mProgressBar;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
        str = getIntent().getStringExtra("Field");
        init();

        //Preparing DB
        DatabaseOpenHelper02 databaseOpenHelper02 = new DatabaseOpenHelper02(SecondMainActivity.this, getFilesDir().getAbsolutePath());
        try {
            databaseOpenHelper02.prepareDataBase();
        } catch (IOException io) {
            throw new Error("Unable to create Database");
        }
        //GET and display Category list from DB
        displayHomePage(databaseOpenHelper02);
    }

    private void displayHomePage(DatabaseOpenHelper02 databaseOpenHelper02) {
        //Getting and Setting DATA to Adapter from DB
        if (databaseOpenHelper02 != null) {
            //TODO call asynctask to load data
            mProgressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getCategoryDataAsyncTask = new GetCategoryDataAsyncTask02(databaseOpenHelper02,str);
            getCategoryDataAsyncTask.onCategoryDataRetrieved = this;
            getCategoryDataAsyncTask.execute();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getCategoryDataAsyncTask != null) {
            getCategoryDataAsyncTask.cancel(true);
        }
    }

    private void init() {
        mProgressBar=findViewById(R.id.insidePB02);
        listView = findViewById(R.id.recyclerView02);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ThirdMainActivity.class);
                intent.putExtra("Medicine", listAdapter.items.get(i).getCatName());
                startActivity(intent);

            }
        });

    }

    @Override
    public void onDataRetrieved(ArrayList<CategoryData02> categoryDataArrayList, boolean status) {
        mProgressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        if (status && categoryDataArrayList.size() > 0) {

            listAdapter = new ListAdapter02(categoryDataArrayList);
            listView.setAdapter(listAdapter);
        }
    }


}

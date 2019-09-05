package com.example.kim_seok_min.micare.readdbfromassets.FourthAct;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kim_seok_min.micare.R;

import java.io.IOException;
import java.util.ArrayList;

public class FourthMainActivity extends AppCompatActivity implements OnCategoryDataRetrieved04 {

    private GetCategoryDataAsyncTask04 getCategoryDataAsyncTask04;
    ListAdapter04 listAdapter;
    private ListView listView;
    private ProgressBar mProgressBar;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main04);
        str = getIntent().getStringExtra("Name");

        init();

        //Preparing DB
        DatabaseOpenHelper04 databaseOpenHelper = new DatabaseOpenHelper04(FourthMainActivity.this, getFilesDir().getAbsolutePath());
        try {
            databaseOpenHelper.prepareDataBase();
        } catch (IOException io) {
            throw new Error("Unable to create Database");
        }
        //GET and display Category list from DB
        displayHomePage(databaseOpenHelper);
    }

    private void displayHomePage(DatabaseOpenHelper04 databaseOpenHelper) {
        //Getting and Setting DATA to Adapter from DB
        Log.e("asdasd","a"+ databaseOpenHelper.toString());
        if (databaseOpenHelper != null) {
            //TODO call asynctask to load data
            mProgressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getCategoryDataAsyncTask04 = new GetCategoryDataAsyncTask04(databaseOpenHelper, str);
            getCategoryDataAsyncTask04.onCategoryDataRetrieved = this;
            getCategoryDataAsyncTask04.execute();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getCategoryDataAsyncTask04 != null) {
            getCategoryDataAsyncTask04.cancel(true);
        }
    }

    private void init() {
        mProgressBar = findViewById(R.id.insidePB04);
        listView = findViewById(R.id.recyclerView04);

    }

    @Override
    public void onDataRetrieved(ArrayList<CategoryData04> categoryDataArrayList, boolean status) {
        mProgressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        Log.e("gf",categoryDataArrayList.size()+"");
        if (status && categoryDataArrayList.size() > 0) {

            listAdapter = new ListAdapter04(categoryDataArrayList);
            listView.setAdapter(listAdapter);
        }
    }


}


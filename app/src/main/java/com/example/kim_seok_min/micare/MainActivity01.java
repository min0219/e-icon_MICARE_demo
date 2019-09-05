package com.example.kim_seok_min.micare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kim_seok_min.micare.readdbfromassets.SecondAct.SecondMainActivity;
import com.example.kim_seok_min.micare.R;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity01 extends AppCompatActivity implements OnCategoryDataRetrieved {

    private GetCategoryDataAsyncTask getCategoryDataAsyncTask;
    ListAdapter listAdapter;
    private ListView listView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);

        init();

        //Preparing DB
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getApplicationContext(), getFilesDir().getAbsolutePath());
        try {
            databaseOpenHelper.prepareDataBase();
        } catch (IOException io) {
            throw new Error("Unable to create Database");
        }
        //GET and display Category list from DB
        displayHomePage(databaseOpenHelper);
    }

    private void displayHomePage(DatabaseOpenHelper databaseOpenHelper) {
        //Getting and Setting DATA to Adapter from DB
        if (databaseOpenHelper != null) {
            //TODO call asynctask to load data
            mProgressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            getCategoryDataAsyncTask = new GetCategoryDataAsyncTask(databaseOpenHelper);
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
        mProgressBar = findViewById(R.id.insidePB);
        listView = findViewById(R.id.recyclerView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SecondMainActivity.class);
                intent.putExtra("Field", listAdapter.items.get(i).getCatName());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onDataRetrieved(ArrayList<CategoryData> categoryDataArrayList, boolean status) {
        mProgressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        if (status && categoryDataArrayList.size() > 0) {

            listAdapter = new ListAdapter(categoryDataArrayList);
            listView.setAdapter(listAdapter);
        }
    }


}

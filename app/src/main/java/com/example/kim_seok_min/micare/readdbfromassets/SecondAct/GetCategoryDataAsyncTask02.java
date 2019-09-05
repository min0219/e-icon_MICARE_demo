package com.example.kim_seok_min.micare.readdbfromassets.SecondAct;

import android.os.AsyncTask;

import java.util.ArrayList;


public class GetCategoryDataAsyncTask02 extends AsyncTask<Void, Void, ArrayList<CategoryData02>> {

    private DatabaseOpenHelper02 databaseOpenHelper02;
    String str;
    public OnCategoryDataRetrieved02 onCategoryDataRetrieved;

    public GetCategoryDataAsyncTask02(DatabaseOpenHelper02 databaseOpenHelper02, String str) {
        this.databaseOpenHelper02 = databaseOpenHelper02;
        this.str = str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected ArrayList<CategoryData02> doInBackground(Void... params) {
        return databaseOpenHelper02.getCategoryName(str);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //@Override
    protected void onPostExecute(ArrayList<CategoryData02> employeeArrayList) {
        databaseOpenHelper02.close();
        if (employeeArrayList != null) {
            if (employeeArrayList.size() > 0) {
                onCategoryDataRetrieved.onDataRetrieved(employeeArrayList, true);
            } else {
                onCategoryDataRetrieved.onDataRetrieved(employeeArrayList, false);
            }
        } else {
            onCategoryDataRetrieved.onDataRetrieved(null, false);
        }    }
}
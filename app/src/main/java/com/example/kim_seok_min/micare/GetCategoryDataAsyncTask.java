package com.example.kim_seok_min.micare;

import android.os.AsyncTask;

import java.util.ArrayList;


public class GetCategoryDataAsyncTask extends AsyncTask<Void, Void, ArrayList<CategoryData>> {

    private DatabaseOpenHelper databaseOpenHelper;
    public OnCategoryDataRetrieved onCategoryDataRetrieved;

    public GetCategoryDataAsyncTask(DatabaseOpenHelper databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected ArrayList<CategoryData> doInBackground(Void... params) {
        return databaseOpenHelper.getCategoryName();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //@Override
    protected void onPostExecute(ArrayList<CategoryData> employeeArrayList) {
        databaseOpenHelper.close();
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
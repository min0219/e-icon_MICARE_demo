package com.example.kim_seok_min.micare.readdbfromassets.ThirdAct;

import android.os.AsyncTask;

import java.util.ArrayList;


public class GetCategoryDataAsyncTask03 extends AsyncTask<Void, Void, ArrayList<CategoryData03>> {

    private DatabaseOpenHelper03 databaseOpenHelper03;
    String str;
    public OnCategoryDataRetrieved03 onCategoryDataRetrieved;

    public GetCategoryDataAsyncTask03(DatabaseOpenHelper03 databaseOpenHelper03, String str) {
        this.databaseOpenHelper03 = databaseOpenHelper03;
        this.str = str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected ArrayList<CategoryData03> doInBackground(Void... params) {
        return databaseOpenHelper03.getCategoryName(str);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //@Override
    protected void onPostExecute(ArrayList<CategoryData03> employeeArrayList) {
        databaseOpenHelper03.close();
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
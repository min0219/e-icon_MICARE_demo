package com.example.kim_seok_min.micare.readdbfromassets.FourthAct;

import android.os.AsyncTask;

import java.util.ArrayList;


public class GetCategoryDataAsyncTask04 extends AsyncTask<Void, Void, ArrayList<CategoryData04>> {

    private DatabaseOpenHelper04 databaseOpenHelper04;
    String str;
    public OnCategoryDataRetrieved04 onCategoryDataRetrieved;

    public GetCategoryDataAsyncTask04(DatabaseOpenHelper04 databaseOpenHelper04, String str) {
        this.databaseOpenHelper04 = databaseOpenHelper04;
        this.str = str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected ArrayList<CategoryData04> doInBackground(Void... params) {
        return databaseOpenHelper04.getCategoryName(str);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //@Override
    protected void onPostExecute(ArrayList<CategoryData04> employeeArrayList) {
        databaseOpenHelper04.close();
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
package com.example.kim_seok_min.micare.readdbfromassets.ThirdAct;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

//This class used to open database and reading database table values
public class DatabaseOpenHelper03 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DISEASE01.db";
    private static final int VERSION_NAME = 1;
    private final static String TAG = "DatabaseHelper";
    private SQLiteDatabase myDataBase02;
    private String myPath;
    private Context myContext;

    public DatabaseOpenHelper03(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NAME);
    }

    public DatabaseOpenHelper03(Context context, String filePath) {
        super(context, DATABASE_NAME, null, VERSION_NAME);
        this.myContext = context;
        myPath = String.valueOf(filePath + "/" + DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        //TODO
    }

    public void prepareDataBase() throws IOException {
        //here we are checking database already on specified path or not
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database exists.");
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        } else {
            Log.d(TAG, "Database not exists.");
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    //method checks for DB exist or NOT
    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File dbFile = new File(myPath);
            checkDB = dbFile.exists();
        } catch (SQLiteException ignored) {
        }
        return checkDB;
    }

    private void copyDataBase() throws IOException {
        OutputStream myOutput = new FileOutputStream(myPath);
        InputStream myInput = myContext.getAssets().open("database/" + DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    //Opening DB and Getting Category DATA from DB
    public ArrayList<CategoryData03> getCategoryName(String str) {
        myDataBase02 = SQLiteDatabase.openDatabase(myPath,
                null, SQLiteDatabase.OPEN_READONLY);
        String query = "SELECT DISTINCT Name from Names where Medicine = " + "'" + str + "'";
        Log.e("hghgh",query);
        Cursor cursor = myDataBase02.rawQuery(query, null);
        ArrayList<CategoryData03> categoryArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            CategoryData03 category = new CategoryData03();
            category.setCatName(cursor.getString(0));
            categoryArrayList.add(category);
        }
        myDataBase02.close();
        cursor.close();
        return categoryArrayList;
    }


}

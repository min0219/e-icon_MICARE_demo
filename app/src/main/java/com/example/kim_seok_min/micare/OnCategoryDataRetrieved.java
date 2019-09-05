package com.example.kim_seok_min.micare;


import java.util.ArrayList;

/**
 * Created by dhiraj on 19/12/17.
 */

public interface OnCategoryDataRetrieved {
    void onDataRetrieved(ArrayList<CategoryData> categoryDataArrayList, boolean status);
}

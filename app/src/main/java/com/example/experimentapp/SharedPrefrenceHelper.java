package com.example.experimentapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceHelper {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefrenceHelper(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("test",Context.MODE_PRIVATE);
    }


    public void saveString(String key,String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getStringValue(String key){
        String value = sharedPreferences.getString(key,null);
        return value;
    }

}

package com.example.experimentapp;

import android.util.Log;
import android.view.View;

public abstract class DebounceClickListener implements View.OnClickListener{

    private static final long MINIMUM_INTERVAL = 15000L;
   private long lastClickTime = 0;

    @Override
    public void onClick(View view) {
        if (System.currentTimeMillis()-lastClickTime>MINIMUM_INTERVAL){
            debouncedClick();
            lastClickTime = System.currentTimeMillis();
        }
        else{
            Log.d("Farman","Debouncing click");
        }

    }

    abstract void debouncedClick();
}

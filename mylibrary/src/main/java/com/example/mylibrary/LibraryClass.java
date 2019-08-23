package com.example.mylibrary;

import android.content.Context;
import android.util.Log;

public class LibraryClass {

    public static void printString(Context context) {

        // let's confirm we have the right context:
        Log.d("TAG", "context is: " + context.getClass().getSimpleName());

        String resourceEntryName = context.getResources().getResourceEntryName(R.string.library_string);
        Log.d("TAG", "res entry name is: " + resourceEntryName);

        String string = context.getString(R.string.library_string);

        Log.d("TAG", string);
    }
}

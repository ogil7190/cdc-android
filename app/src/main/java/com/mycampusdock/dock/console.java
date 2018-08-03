package com.mycampusdock.dock;

import android.util.Log;

public class console {
    public static void log(String log) {
        Log.d("Dock", log);
    }

    public static void error(String error) {
        Log.e("Dock", error);
    }
}

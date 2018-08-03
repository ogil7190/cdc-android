package com.mycampusdock.dock;

public class Interfaces {

    public interface RequestListener {
        void onRequestStart();

        void onRequestFail(String error);

        void onRequestResponse(String response);
    }
}

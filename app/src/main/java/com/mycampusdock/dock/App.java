package com.mycampusdock.dock;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Map;

import static com.mycampusdock.dock.Config.PREF_STORE_NAME;
import static com.mycampusdock.dock.Config.REQ_TIME_OUT;

public class App extends Application {
    private RequestQueue queue;
    private SharedPreferences pref;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    public void sendNetworkRequest(String URL, int method, final Map<String, String> params, final Interfaces.RequestListener listener) {
        /*
         * 0 for GET type
         * 1 for POST type
         */
        listener.onRequestStart();
        StringRequest stringRequest = new StringRequest(method, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onRequestResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestFail("" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(REQ_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueueInstance(getApplicationContext()).add(stringRequest);
    }

    public SharedPreferences getPref() {
        if (pref == null) {
            return getSharedPreferences(PREF_STORE_NAME, MODE_PRIVATE);
        }
        return pref;
    }

    public RequestQueue getRequestQueueInstance(Context context) {
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
            return queue;
        }
        return queue;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

package com.mycampusdock.dock;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import static com.mycampusdock.dock.Config.PREF_STORE_KEY_EMAIL_EXISTS;

public class MainActivity extends AppCompatActivity {
    private App app;
    private TextView mTextMessage;
    private SharedPreferences pref;
    private Fragment fragment = HomeFragment.newInstance();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = HomeFragment.newInstance();
                    addFragmentToStack();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = ExamFragment.newInstance();
                    addFragmentToStack();
                    return true;
                case R.id.navigation_notifications:
                    fragment = ProfileFragment.newInstance();
                    addFragmentToStack();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplication();
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pref = app.getPref();
        checkLoggedIn();
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment, fragment).commit();
        }
    }

    private void checkLoggedIn() {
        if (!pref.getBoolean(PREF_STORE_KEY_EMAIL_EXISTS, false)) {
            startActivity(new Intent(getApplicationContext(), StartActivity.class));
            finish();
        }
    }

    void addFragmentToStack() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}

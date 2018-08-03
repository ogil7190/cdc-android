package com.mycampusdock.dock;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.mycampusdock.dock.Config.PREF_STORE_KEY_EMAIL;
import static com.mycampusdock.dock.Config.PREF_STORE_KEY_EMAIL_EXISTS;
import static com.mycampusdock.dock.Config.PREF_STORE_KEY_NAME;

public class StartActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button submit;
    private TextView loading;
    private ProgressBar progress;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_start);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        submit = findViewById(R.id.submit);
        loading = findViewById(R.id.loading);
        progress = findViewById(R.id.progress);
        app = (App) getApplication();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (LocalDB.auth(email.getText().toString(), pass.getText().toString())) {
                                    gotoHome();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong Passowrd or Login id", Toast.LENGTH_LONG).show();
                                }
                                progress.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }
                        });
                    }
                }, 2000);

            }
        });
    }

    private void gotoHome() {
        app.getPref().edit().putBoolean(PREF_STORE_KEY_EMAIL_EXISTS, true).apply();
        app.getPref().edit().putString(PREF_STORE_KEY_EMAIL, email.getText().toString()).apply();
        app.getPref().edit().putString(PREF_STORE_KEY_NAME, LocalDB.user(email.getText().toString())).apply();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}

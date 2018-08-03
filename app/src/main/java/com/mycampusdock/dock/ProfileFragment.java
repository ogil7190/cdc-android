package com.mycampusdock.dock;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.mycampusdock.dock.Config.PREF_STORE_KEY_EMAIL;
import static com.mycampusdock.dock.Config.PREF_STORE_KEY_NAME;
import static com.mycampusdock.dock.Config.PREF_STORE_NAME;

public class ProfileFragment extends Fragment {
    private TextView email, name;
    private Button logout;
    private App app;

    static ProfileFragment newInstance() {
        ProfileFragment f = new ProfileFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        email = v.findViewById(R.id.email);
        name = v.findViewById(R.id.name);
        logout = v.findViewById(R.id.logout);
        app = (App) getActivity().getApplication();
        String e = app.getPref().getString(PREF_STORE_KEY_EMAIL, "dummy@dummy.com");
        String n = app.getPref().getString(PREF_STORE_KEY_NAME, "Dummy Dum");
        email.setText(e);
        name.setText(n);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.getPref().edit().clear().commit();
                startActivity(new Intent(getActivity(), StartActivity.class));
                getActivity().finish();
            }
        });
        return v;
    }
}

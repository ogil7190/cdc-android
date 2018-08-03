package com.mycampusdock.dock;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView posts;
    private List<Post> postList;

    static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postList = new ArrayList<>();
        postList = LocalDB.dummydata();
        console.log("size:"+postList.size());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        posts = v.findViewById(R.id.posts);
        posts.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        posts.setAdapter(new PostAdapter(postList));
        return v;
    }
}

package com.example.travelling.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelling.HomeAdapter;
import com.example.travelling.Item;
import com.example.travelling.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class HomeFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    List<Item> items = new ArrayList<Item>();



    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.home_frag_recyclerview);

        items.add(new Item("Dhaka", "Bangladesh", R.drawable.dhaka));
        items.add(new Item("Sylhet", "Bangladesh", R.drawable.sylhet));
        items.add(new Item("Chittagong", "Bangladesh", R.drawable.chittagong));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HomeAdapter(getContext(),items));




        return view;
    }
}
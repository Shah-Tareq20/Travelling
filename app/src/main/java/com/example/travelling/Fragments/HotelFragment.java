package com.example.travelling.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelling.R;

import butterknife.ButterKnife;

public class HotelFragment extends Fragment {

    View view;
    private Activity mActivity;




    public HotelFragment() {
        // Required empty public constructor
    }

    public static HotelFragment newInstance() {
        HotelFragment fragment = new HotelFragment();

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
        view = inflater.inflate(R.layout.fragment_hotel, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }
}
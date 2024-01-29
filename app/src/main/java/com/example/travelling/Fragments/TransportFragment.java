package com.example.travelling.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelling.R;
import com.example.travelling.Services.BlogActivity;
import com.example.travelling.WebView.WebviewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class TransportFragment extends Fragment {
    View view;
    //Intent intent;
    private Activity mActivity;

    public TransportFragment() {
    }

    public static TransportFragment newInstance() {
        TransportFragment fragment = new TransportFragment();

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
        view = inflater.inflate(R.layout.fragment_transport, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.plane)
        public void OnPlaneClicked() {
            Intent intent = new Intent(getContext(),WebviewActivity.class);
            intent.putExtra("key","plane");
            startActivity(intent);

        }

    @OnClick(R.id.train)
    public void OnTrainClicked() {
       Intent intent = new Intent(getContext(),WebviewActivity.class);
        intent.putExtra("key","train");
        startActivity(intent);

    }

    @OnClick(R.id.bus)
    public void OnBusClicked() {
        Intent intent = new Intent(getContext(),WebviewActivity.class);
        intent.putExtra("key","bus");
        startActivity(intent);

    }

    @OnClick(R.id.car)
    public void OnCarClicked() {
        Intent intent = new Intent(getContext(),WebviewActivity.class);
        intent.putExtra("key","car");
        startActivity(intent);

    }

    @OnClick(R.id.ship)
    public void OnShipClicked() {
        Intent intent = new Intent(getContext(),WebviewActivity.class);
        intent.putExtra("key","ship");
        startActivity(intent);
    }

    @OnClick(R.id.more)
    public void OnMoreClicked() {
        Intent intent = new Intent(getContext(),WebviewActivity.class);
        intent.putExtra("key","more");
        startActivity(intent);

    }


    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }

}
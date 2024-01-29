package com.example.travelling.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.travelling.R;
import com.example.travelling.Services.AboutActivity;
import com.example.travelling.Services.BlogActivity;
import com.example.travelling.Services.ClassifyActivity;
import com.example.travelling.Services.MapsActivity;
import com.example.travelling.Services.PlayActivity;
import com.example.travelling.Services.QnAActivity;
import com.example.travelling.sessions.SessionManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MoreFragment extends Fragment {

    View view;
    private Activity mActivity;

    public MoreFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
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
        view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);

        return view;

    }

    @OnClick(R.id.blog)
    public void OnBlogClicked() {
        Intent intent = new Intent(getContext(),BlogActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.map)
    public void OnMapClicked() {
        startActivity( new Intent(getContext(), MapsActivity.class));
    }

    @OnClick(R.id.classify)
    public void OnClassifyClicked() {
        startActivity(new Intent(getContext(), ClassifyActivity.class));
    }

    @OnClick(R.id.play)
    public void OnPlayClicked() {
        startActivity( new Intent(getContext(), PlayActivity.class));
    }

    @OnClick(R.id.qna)
    public void OnQnAClicked() {
        startActivity( new Intent(getContext(), QnAActivity.class));
    }

    @OnClick(R.id.about_me)
    public void OnAboutClicked() {
        startActivity( new Intent(getContext(), AboutActivity.class));
    }


    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }

}
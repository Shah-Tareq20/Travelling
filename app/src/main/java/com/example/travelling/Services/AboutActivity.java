package com.example.travelling.Services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.example.travelling.Adapters.RatingAdapter;
import com.example.travelling.R;
import com.example.travelling.models.RateUsDialog;
import com.example.travelling.models.RatingModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {

    RecyclerView ratingRV;
    ArrayList<RatingModel> ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);




        // add back arrow to toolbar
        if(getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Rate This App");

            LottieAnimationView lottieAnimationView = findViewById(R.id.star);
            ratingRV = findViewById(R.id.rating_RV);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            RateUsDialog dialog = new RateUsDialog(AboutActivity.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            dialog.setCancelable(false);

            lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.show();
                }
            });

            ratings = new ArrayList<>();

            RatingAdapter ratingAdapter = new RatingAdapter(getApplicationContext(), ratings);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            ratingRV.setLayoutManager(layoutManager);
            ratingRV.addItemDecoration(new DividerItemDecoration(ratingRV.getContext(), DividerItemDecoration.VERTICAL));
            //        ratingRV.setNestedScrollingEnabled(false);
            ratingRV.setAdapter(ratingAdapter);


            database.getReference()
                    .child("ratings")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ratings.clear();
                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                RatingModel ratingModel = dataSnapshot.getValue(RatingModel.class);
                                ratings.add(ratingModel);
                            }
                            ratingAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
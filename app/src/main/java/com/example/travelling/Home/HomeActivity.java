package com.example.travelling.Home;

import static com.example.travelling.sessions.SessionManager.USER_LOGIN_SESSION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.travelling.Fragments.FoodFragment;
import com.example.travelling.Fragments.HomeFragment;
import com.example.travelling.Fragments.HotelFragment;
import com.example.travelling.Fragments.MoreFragment;
import com.example.travelling.Fragments.TransportFragment;
import com.example.travelling.HelperActivity.UserProfileActivity;
import com.example.travelling.LoginActivity;
import com.example.travelling.R;
import com.example.travelling.SignupActivity;
import com.example.travelling.models.UserModel;
import com.example.travelling.sessions.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;

    ImageView notification, signOut;
    CircleImageView userImage;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        //this.setTitle("Home");

        notification = findViewById(R.id.notification);
        signOut = findViewById(R.id.log_out);
        userImage = findViewById(R.id.user_image);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        sessionManager = new SessionManager(HomeActivity.this, USER_LOGIN_SESSION);

        signOut.setOnClickListener(this);
        userImage.setOnClickListener(this);
        //signOut.setOnClickListener(v -> signOut());

       //userImage.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),UserProfileActivity.class)));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_id, new HomeFragment()).commit();
    }

    private void signOut() {
        AlertDialog.Builder signOutAlert = new AlertDialog.Builder(HomeActivity.this);
        signOutAlert.setTitle("Log out")
                .setMessage("Are you sure to log out?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    mAuth.signOut();
                    sessionManager.logoutUserFromSession();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                })
                .setNegativeButton("No", (dialogInterface, i) -> {});

        AlertDialog dialog = signOutAlert.create();
        dialog.show();
    }

    private final  NavigationBarView.OnItemSelectedListener navListener = item -> {

        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.homeId) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.transportId) {
            selectedFragment = new TransportFragment();
        } else if (itemId == R.id.hotelId) {
            selectedFragment = new HotelFragment();
        }
        else if (itemId == R.id.foodId) {
            selectedFragment = new FoodFragment();
        }
        else if (itemId == R.id.moreId) {
            selectedFragment = new MoreFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_id, selectedFragment).commit();
        }
        return true;
    };

    @Override
    protected void onStart() {
        super.onStart();

        database.getReference().child("users").child(Objects.requireNonNull(mAuth.getUid()))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel user = snapshot.getValue(UserModel.class);
                            Picasso.get()
                                    .load(user.getProfileImage())
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .into(userImage);//Will UnComment this line Later when user image will be uploaded//

                            String _name = user.getName();
                            String _username = user.getUserName();
                            String _email = user.getEmail();
                            String _password = user.getPassword();
                            String _address = user.getAddress();
                            String _phone = user.getPhone();
                            String _birthday = user.getBirthday();
                            String _gender = user.getGender();

                            sessionManager.createLoginSession(_name, _username, _email, _password, _address, _gender, _phone, _birthday);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.log_out) {
            signOut();
        }
        if (v.getId() == R.id.user_image) {
            startActivity(new Intent(getApplicationContext(),UserProfileActivity.class));
        }
    }
}
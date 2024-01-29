package com.example.travelling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelling.Home.HomeActivity;
import com.example.travelling.sessions.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.perf.session.SessionManager;

//import com.example.color.sessions.SessionManager;


import java.util.HashMap;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText signInEmailEditText, signInPasswordEditText;
    CheckBox signInCheckbox;
    Button signInButton;
    TextView forgotPasswordTextView, signupTextView;

    SessionManager sessionManager;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInEmailEditText = findViewById(R.id.signin_email_id);
        signInPasswordEditText = findViewById(R.id.signin_password_id);
        signInCheckbox = findViewById(R.id.signin_checkbox_id);
        signInButton = findViewById(R.id.signin_button_id);
        signupTextView = findViewById(R.id.signup_text_id);
        forgotPasswordTextView = findViewById(R.id.forgot_password_id);

        sessionManager = new SessionManager(LoginActivity.this, SessionManager.REMEMBER_ME_SESSION);
        if(sessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetails = sessionManager.getRememberMeDetailsFromSession();
            signInEmailEditText.setText(rememberMeDetails.get(SessionManager.KEY_REMEMBER_ME_EMAIL));
            signInPasswordEditText.setText(rememberMeDetails.get(SessionManager.KEY_REMEMBER_ME_PASSWORD));
        }

        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signupTextView.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);
        signInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            if(v.getId() == R.id.signup_text_id) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));

            }
            if(v.getId() == R.id.forgot_password_id) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
            if(v.getId() == R.id.signin_button_id){
                userSignIn();
            }
    }

    private void userSignIn() {

        if(!isConnected(this))
            showNoInternetDialog();

        String email = signInEmailEditText.getText().toString();
        String password = signInPasswordEditText.getText().toString();

        if(signInCheckbox.isChecked()){
                sessionManager.createRememberMeSession(email,password);
        }
        else {
            sessionManager.logoutUserFromSession();
        }


        if (email.isEmpty()) {
            signInEmailEditText.setError("Email cannot be empty");
        }
        if (password.isEmpty()) {
            signInPasswordEditText.setError("Password cannot be empty");
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if(Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()){
                                    Toasty.success(getApplicationContext(), "User login successfully", Toast.LENGTH_SHORT, true).show();
                                    startActivity(new Intent(LoginActivity.this, /*Will Change later*/HomeActivity.class));
                                    //Will change later ////
                                    finish();
                                }
                                else{
                                    Toasty.warning(getApplicationContext(), "Please, verify email first!", Toast.LENGTH_SHORT, true).show();
                                }
                            } else {
                                Toasty.error(getApplicationContext(), "SignIn Failed" + task.getException(), Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    });
        }

    }

    private boolean isConnected(LoginActivity loginActivity) {

        ConnectivityManager connectivityManager = (ConnectivityManager) loginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiInfo != null && wifiInfo.isConnected()) || mobileInfo != null && mobileInfo.isConnected();

    }

    private void showNoInternetDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setMessage("Please, connect to the internet!")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null && user.isEmailVerified()){
            Intent intent = new Intent(LoginActivity.this, /*Will change later*/HomeActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
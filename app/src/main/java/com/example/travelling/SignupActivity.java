package com.example.travelling;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelling.Home.HomeActivity;
import com.example.travelling.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {

    Button signUp;
    TextView signIn;
    EditText signup_name, signup_email, signup_username, signup_password;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    boolean all_ok = true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUp = findViewById(R.id.signup_button);
        signIn = findViewById(R.id.loginRedirectText);
        signup_name = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_username = findViewById(R.id.signup_username);
        signup_password = findViewById(R.id.signup_password);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        signup_username.setOnFocusChangeListener((view, hasFocus) -> {
            onFocusChange(view, hasFocus);
        });

        signUp.setOnClickListener(v -> userRegistration());

        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, /*Will Change Later*/LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void userRegistration() {

        String name = signup_name.getText().toString();
        String email = signup_email.getText().toString();
        String userName = signup_username.getText().toString();
        String password = signup_password.getText().toString();


        boolean validUser = validateName(name) && validateUserName(userName) && validateEmail(email) && validatePassword(password) && all_ok;

        if(validUser){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                UserModel user = new UserModel(name, email, userName, password);
                                String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                reference.child(id).setValue(user);

                                mAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toasty.success(getApplicationContext(), "Please, Verify Your Email!", Toast.LENGTH_SHORT, true).show();
                                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                    finish();
                                                }
                                                else {
                                                    Toasty.error(getApplicationContext(), task.getException() + "", Toast.LENGTH_SHORT, true).show();
                                                }
                                            }
                                        });
                            } else {
                                Toasty.error(getApplicationContext(), "SignUp Failed" + task.getException(), Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    });
        }

    }

    private boolean validateName(String name){
        if(name.isEmpty()){
            signup_name.setError("Name cannot be empty!");
            return false;
        }
        else {
            signup_name.setError(null);
            return true;
        }
    }

    private boolean validateEmail(String email){
        if(email.isEmpty()){
            signup_email.setError("Email cannot be empty!");
            return false;
        }
        else {
            signup_email.setError(null);
            return true;
        }
    }

    private boolean validateUserName(String userName){
        if(userName.isEmpty()){
            signup_username.setError("UserName cannot be empty!");
            return false;
        }
        else {
            signup_username.setError(null);
            return true;
        }
    }

    private boolean validatePassword(String password){
        if(password.isEmpty()){
            signup_password.setError("Password cannot be empty!");
            return false;
        }
        else if(password.length() < 6){
            signup_password.setError("Please, provide at least 6 characters!");
            return false;
        }
        else {
            signup_password.setError(null);
            return true;
        }
    }

    private void checkUser() {
        String shortName = signup_username.getText().toString();
        if (!shortName.isEmpty()) {
            Query query = reference.orderByChild("username").equalTo(shortName);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        signup_username.setError("Username already exist");
                       //signup_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_username, 0, 0, 0);
                        all_ok = false;
                    } else {
                        signup_username.setError(null);
                        //signup_username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_username, 0, R.drawable.ic_ok, 0);
                        all_ok = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
        else {
            signup_username.setError("Username cannot be empty");
        }
    }

    private void onFocusChange(View view, boolean hasFocus) {
        if (!hasFocus) {
            checkUser();
        }
    }

}
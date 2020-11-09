package com.halo.carInfoWithAi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Activity_Splash_Login extends AppCompatActivity {

    EditText mPassword;
    EditText mEmail;
    Button login;
    TextView signUp;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    private static final String TAG = "Activity_Splash_Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        signUp = (TextView) findViewById(R.id.tv_register);
        login = findViewById(R.id.tv_login);
        mEmail = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        Log.e(TAG,"signed .....");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clickeed");
                Intent intent = new Intent(Activity_Splash_Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (email.isEmpty()) {
                    mEmail.setError("Email cant be empty");
                    return;
                }
                if (password.isEmpty()) {
                    mPassword.setError("Password cant be empty");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Activity_Splash_Login.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Activity_Splash_Login.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
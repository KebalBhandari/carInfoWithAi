package com.halo.carInfoWithAi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.halo.carInfoWithAi.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity  {

    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";
    EditText mUsername;
    EditText mPassword;
    EditText mEmail;
    EditText mPhone;
    Button mButton;
    TextView loginRedirect;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private static final String TAG = "SignUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mEmail = findViewById(R.id.email);
        mPhone=findViewById(R.id.phone);
        mButton = findViewById(R.id.userSignUp);
        loginRedirect = findViewById(R.id.tv_login);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
//        onBackPressed();

//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clicked");
//                Intent intent = new Intent(SignUp.this,l.class);
                onBackPressed();
            }
        });


        //validation
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String email = mEmail.getText().toString().trim();
               final String password = mPassword.getText().toString().trim();
               final String name = mUsername.getText().toString();
               final String phone = mPhone.getText().toString();
                if (email.isEmpty()) {
                    mEmail.setError("Email cant be empty");
                    return;
                }
                if (name.isEmpty()) {
                    mPassword.setError("Name cant be empty");
                    return;
                }
                if (phone.isEmpty()) {
                    mPassword.setError("Phone cant be empty");
                    return;
                }
                if (password.isEmpty()) {
                    mPassword.setError("Password cant be empty");
                    return;
                }
                if (password.length()  < 6 ) {
                    mPassword.setError("Password must be >= 6 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("user").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("UName", name);
                             user.put("Email", email);
                             user.put("Phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e(TAG,"data stored");
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, "Error"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}



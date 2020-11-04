package com.halo.carInfoWithAi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Splash_Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView signUp;
    private static final String TAG = "Activity_Splash_Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        signUp = (TextView) findViewById(R.id.tv_register);
        Log.e(TAG,"signed .....");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clickeed");
                Intent intent = new Intent(Activity_Splash_Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        login = (Button) findViewById(R.id.tv_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.length() < 3) {
                    Toast.makeText(Activity_Splash_Login.this, "Username must be more than 3 digits", Toast.LENGTH_SHORT).show();

                } else if (password.length() < 3) {
                    Toast.makeText(Activity_Splash_Login.this, "Password must be more than 3 digits", Toast.LENGTH_SHORT).show();

                } else {
                    Intent i = new Intent(Activity_Splash_Login.this, MainActivity.class);
//                String name = username.getText().toString();
                    i.putExtra("name", "kebal");
                    startActivity(i);
                }
            }
        });
    }
}
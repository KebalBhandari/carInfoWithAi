package com.halo.carInfoWithAi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Logout";
    Button logoutButton;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = getIntent().getExtras();
        logoutButton = findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                if(fAuth.getCurrentUser()==null){
                    Toast.makeText(MainActivity.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Error !!! Logout Not Success.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        TextView numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, LP_detect.class);
                startActivity(numbersIntent);
            }
        });

        TextView family = (TextView) findViewById(R.id.family);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this, Car_Detail.class);
                startActivity(familyIntent);
            }
        });
    }

}




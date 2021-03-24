package com.halo.carInfoWithAi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.halo.carInfoWithAi.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Logout";
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = getIntent().getExtras();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        fAuth = FirebaseAuth.getInstance();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        fAuth.signOut();
                        if(fAuth.getCurrentUser()==null){
                            Toast.makeText(MainActivity.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error !!! Logout Not Success.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.profileView:
                        Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(profileIntent);
                        break;
                }
                return false;
            }
        });

        CardView EditData = (CardView) findViewById(R.id.editUpdateData);
        EditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsIntent = new Intent(MainActivity.this, CarListActivity.class);
                startActivity(detailsIntent);
            }
        });

        CardView profileDataInfo = (CardView) findViewById(R.id.profileCard);
        profileDataInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ProfileIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(ProfileIntent);
            }
        });

        final CardView lpDetect = (CardView) findViewById(R.id.capture_image);
        lpDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LpdetectIntent = new Intent(MainActivity.this, LPDetectActivity.class);
                startActivity(LpdetectIntent);
            }
        });
    }
}




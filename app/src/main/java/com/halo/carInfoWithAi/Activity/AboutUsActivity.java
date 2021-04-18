package com.halo.carInfoWithAi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.halo.carInfoWithAi.R;

public class AboutUsActivity extends AppCompatActivity {
    private static final String TAG = "Logout";
    FirebaseAuth fAuth;
    TextView info;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Log.e(TAG, "onClick: Create");
        info = findViewById(R.id.detailsText);
        info.setText("CAR INFO WITH AI is an application developed by Kebal Psd. Bhandari, Saurav Subedi and Sameer Thapa for fulfillment of BCA 8th Semester Project on android. Basically this application use TensorFlow ML kit and vision API to identify CAR from image and display car details along with owners information. ");
        Bundle b = getIntent().getExtras();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        fAuth = FirebaseAuth.getInstance();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        fAuth.signOut();
                        if (fAuth.getCurrentUser() == null) {
                            Toast.makeText(AboutUsActivity.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(AboutUsActivity.this, "Error !!! Logout Not Success.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.profileView:
                        Intent profileIntent = new Intent(AboutUsActivity.this, ProfileActivity.class);
                        startActivity(profileIntent);
                        break;
                    case R.id.home:
                        Intent MainActivity = new Intent(AboutUsActivity.this, MainActivity.class);
                        startActivity(MainActivity);
                        break;
                }
                return false;
            }
        });
    }
}

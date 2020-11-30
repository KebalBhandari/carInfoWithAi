package com.halo.carInfoWithAi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023 ;
    TextView fullName,email,phone, UserName;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        UserName = findViewById(R.id.profileName1);
        email    = findViewById(R.id.profileEmail);
        profileImage = findViewById(R.id.profileImage);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference= fStore.collection("user").document(userId);
        documentReference.addSnapshotListener(this,new EventListener<DocumentSnapshot>(){

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
               phone.setText(documentSnapshot.getString("Phone"));
               fullName.setText(documentSnapshot.getString("UName"));
                UserName.setText(documentSnapshot.getString("UName"));
               email.setText(documentSnapshot.getString("Email"));
            }
        });

        ImageView eProfile = (ImageView) findViewById(R.id.editProfile);
        eProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(profile.this, EditProfile.class);
                startActivity(editIntent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.logout:
                        fAuth.signOut();
                        if(fAuth.getCurrentUser()==null){
                            Toast.makeText(profile.this, "Logout Successful.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                        else{
                            Toast.makeText(profile.this, "Error !!! Logout Not Success.", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                    case R.id.profileView:
                        Intent profileIntent = new Intent(profile.this, profile.class);
                        startActivity(profileIntent);
                        break;
                    case R.id.home:
                        Intent MainActivity = new Intent(profile.this, MainActivity.class);
                        startActivity(MainActivity);
                        break;
                }
                return false;
            }
        });



    }
}

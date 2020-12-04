package com.halo.carInfoWithAi;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Word extends AppCompatActivity  {

    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";
    EditText nNumberInfo;
    EditText mCompanyName;
    EditText mModelNo;
    EditText mCcData;
    EditText mColor;
    EditText mManufactureDate;
    EditText mOwnerName;
    EditText mOwnerAddress;
    EditText mOwnerContact;
    EditText mOwnerOccupation;
    Button mSaveButton;
    Button mBtnBack;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private static final String TAG = "SignUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custominput);

        nNumberInfo = findViewById(R.id.NoPlate);
        mCompanyName = findViewById(R.id.CName);
        mModelNo = findViewById(R.id.modelNumber);
        mCcData=findViewById(R.id.ccInfo);
        mManufactureDate = findViewById(R.id.ManufactureDatey);
        mOwnerName = findViewById(R.id.ownerName);
        mOwnerAddress = findViewById(R.id.ownerContactInfo);
        mOwnerContact=findViewById(R.id.ownerPhoneNo);
        mOwnerOccupation = findViewById(R.id.ownerOccupation);
        mColor = findViewById(R.id.colorInfo);
        mSaveButton = findViewById(R.id.btn_save);
        mBtnBack = findViewById(R.id.btnBack);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clicked");
                onBackPressed();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String numberPlate = nNumberInfo.getText().toString().trim();
                final String companyName = mCompanyName.getText().toString().trim();
                final String modelNo = mModelNo.getText().toString();
                final String ccData = mCcData.getText().toString();

                final String manufactureDate = mManufactureDate.getText().toString().trim();
                final String ownerName = mOwnerName.getText().toString().trim();
                final String ownerAddress = mOwnerAddress.getText().toString();
                final String ownerContact = mOwnerContact.getText().toString();
                final String ownerOccupation = mOwnerOccupation.getText().toString();
                final String carColor = mColor.getText().toString();


                if (numberPlate.isEmpty()) {
                    nNumberInfo.setError("Number Plate Data is required");
                    return;
                }
                if (companyName.isEmpty()) {
                    mCompanyName.setError("Company Name cant be empty");
                    return;
                }
                if (modelNo.isEmpty()) {
                    mModelNo.setError("Model No cant be empty");
                    return;
                }
                if (ownerName.isEmpty()) {
                    mOwnerName.setError("Owner Name cant be empty");
                    return;
                }
                if (ownerContact.length() < 6) {
                    mOwnerContact.setError("Owner Contact must be >= 6 Characters");
                    return;
                }

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("CarInfo").document(userID);
                Map<String, Object> CarInfo = new HashMap<>();
                CarInfo.put("CNumber", numberPlate);
                CarInfo.put("CName", companyName);
                CarInfo.put("CModelNumber", modelNo);
                CarInfo.put("CCC", ccData);
                CarInfo.put("CColor", carColor);
                CarInfo.put("CMDate", manufactureDate);
                CarInfo.put("COwner", ownerName);
                CarInfo.put("CAddress", ownerAddress);
                CarInfo.put("CContact", ownerContact);
                CarInfo.put("COccupation", ownerOccupation);

                documentReference.set(CarInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e(TAG,"Car Info stored");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
            }
        });
    }
}

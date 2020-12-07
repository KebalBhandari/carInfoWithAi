package com.halo.carInfoWithAi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.halo.carInfoWithAi.Models.*;

public class Word extends AppCompatActivity  {

    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";
    DatabaseReference mDatabase;
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
    RecyclerView recyclerView;
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
        mManufactureDate = findViewById(R.id.ManufactureDate);
        mOwnerName = findViewById(R.id.ownerName);
        mOwnerAddress = findViewById(R.id.ownerContactInfo);
        mOwnerContact=findViewById(R.id.ownerPhoneNo);
        mOwnerOccupation = findViewById(R.id.ownerOccupation);
        mColor = findViewById(R.id.colorInfo);
        mSaveButton = findViewById(R.id.btn_save);
        mBtnBack = findViewById(R.id.btnBack);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recycleView);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CarInfoData").child(userID);
        mDatabase.keepSynced(true);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"clicked");
                onBackPressed();
            }
        });

//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String numberPlate = nNumberInfo.getText().toString().trim();
//                final String companyName = mCompanyName.getText().toString().trim();
//                final String modelNo = mModelNo.getText().toString();
//                final String ccData = mCcData.getText().toString();
//
//                final String manufactureDate = mManufactureDate.getText().toString().trim();
//                final String ownerName = mOwnerName.getText().toString().trim();
//                final String ownerAddress = mOwnerAddress.getText().toString();
//                final String ownerContact = mOwnerContact.getText().toString();
//                final String ownerOccupation = mOwnerOccupation.getText().toString();
//                final String carColor = mColor.getText().toString();
//                if (numberPlate.isEmpty()) {
//                    nNumberInfo.setError("Number Plate Data is required");
//                    return;
//                }
//                if (companyName.isEmpty()) {
//                    mCompanyName.setError("Company Name cant be empty");
//                    return;
//                }
//                if (modelNo.isEmpty()) {
//                    mModelNo.setError("Model No cant be empty");
//                    return;
//                }
//                if (ownerName.isEmpty()) {
//                    mOwnerName.setError("Owner Name cant be empty");
//                    return;
//                }
//                if (ownerContact.length() < 6) {
//                    mOwnerContact.setError("Owner Contact must be >= 6 Characters");
//                    return;
//                }
//                String id = mdatabase.push().getKey();
//                String today_date = DateFormat.getDateInstance().format(new Date());
//                Data data = new Data(mtitle, mnote, today_date, id);
//                mdatabase.child(id).setValue(data);
//                Toast.makeText(HomeActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String numberPlate = nNumberInfo.getText().toString().trim();
                 String companyName = mCompanyName.getText().toString().trim();
                 String modelNo = mModelNo.getText().toString();
                 String ccData = mCcData.getText().toString();

                 String manufactureDate = mManufactureDate.getText().toString().trim();
                 String ownerName = mOwnerName.getText().toString().trim();
                 String ownerAddress = mOwnerAddress.getText().toString();
                 String ownerContact = mOwnerContact.getText().toString();
                 String ownerOccupation = mOwnerOccupation.getText().toString();
                 String carColor = mColor.getText().toString();


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

                String id = mDatabase.push().getKey();
                Data data = new Data(numberPlate, companyName, modelNo,ccData,manufactureDate,ownerName,ownerAddress,ownerOccupation,ownerContact,carColor,id);
                mDatabase.child(id).setValue(data);
                Toast.makeText(Word.this, "Data inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

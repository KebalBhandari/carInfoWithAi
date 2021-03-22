package com.halo.carInfoWithAi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.halo.carInfoWithAi.Models.Data;
import com.halo.carInfoWithAi.R;

import java.util.HashMap;
import java.util.Objects;

import static java.security.AccessController.getContext;

public class CarInfoDetailActivity extends AppCompatActivity {
    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";

    public  static  final  String EXTRA_POST_KEY = "POST_KEY";

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
    Button mUpdateButton;
    Button mDeleteButton;
    Button mBtnBack;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Data data;
    EditText mID;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dialog);
        mBtnBack = findViewById(R.id.activity_care_detail_back_btn);
        mUpdateButton = findViewById(R.id.btn_delete_from_update);
        mDeleteButton = findViewById(R.id.deleteData);
        nNumberInfo = findViewById(R.id.NoPlate1);
        mCompanyName =findViewById(R.id.CName1);
        mColor =findViewById(R.id.colorInfo1);
        mManufactureDate =findViewById(R.id.ManufactureDate1);
        mOwnerName =findViewById(R.id.ownerName1);
        mOwnerAddress =findViewById(R.id.ownerContactInfo1);
        mOwnerContact =findViewById(R.id.ownerPhoneNo1);
        mOwnerOccupation =findViewById(R.id.ownerOccupation1);
        mCcData =findViewById(R.id.ccInfo1);
        mModelNo =findViewById(R.id.modelNumber1);
        mID = findViewById(R.id.ID1);

        final String postKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if(postKey == null){
            onBackPressed();
            return;
        }
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        Log.e("postKey",postKey+"");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CarInfoData").child(userID).child(postKey);
        mDatabase.keepSynced(true);

        mBtnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "clicked");
                onBackPressed();
            }
        });


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             Log.e("snapshot",snapshot.getValue()+"");
             if(snapshot.getValue()!=null) {
                 HashMap hashMap = ((HashMap) snapshot.getValue());
                 Data data = new Data(
                         hashMap.get("noPlate").toString(),
                         hashMap.get("cname").toString(),
                         hashMap.get("modelNumber").toString(),
                         hashMap.get("ccInfo").toString(),
                         hashMap.get("colorInfo").toString(),
                         hashMap.get("manufactureDate").toString(),
                         hashMap.get("ownerName").toString(),
                         hashMap.get("ownerContactInfo").toString(),
                         hashMap.get("ownerOccupation").toString(),
                         hashMap.get("ownerPhoneNo").toString(),
                         hashMap.get("id").toString()
                 );
                 //Log.e("snapshot data",data.toString()+"");
                 CarInfoDetailActivity.this.data = data;
                 CarInfoDetailActivity.this.initData();
             }
             else{
                 CarInfoDetailActivity.this.data = new Data();
                 CarInfoDetailActivity.this.initData();
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("snapshot ERrr",error.toString()+"");
            }
        });


        mUpdateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberPlate = nNumberInfo.getText().toString().trim();
                String companyName = mCompanyName.getText().toString().trim();
                String modelNo = mModelNo.getText().toString();
                String ccData = mCcData.getText().toString();
                String manufactureDate = mManufactureDate.getText().toString().trim();
                String ownerName = mOwnerName.getText().toString().trim();
                String ownerAddress = mOwnerAddress.getText().toString();
                String ownerOccupation = mOwnerOccupation.getText().toString();
                String ownerContact = mOwnerContact.getText().toString();
                String carColor = mColor.getText().toString();
                String carID = mID.getText().toString();


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
                mDatabase.child(postKey).removeValue();
                String id = mDatabase.push().getKey();
                HashMap hashMap = new HashMap();
                hashMap.put("ccInfo", ccData);
                hashMap.put("cname", companyName);
                hashMap.put("colorInfo", carColor);
                hashMap.put("id", carID);
                hashMap.put("manufactureDate", manufactureDate);
                hashMap.put("modelNumber", modelNo);
                hashMap.put("noPlate", numberPlate);
                hashMap.put("ownerContactInfo", ownerAddress);
                hashMap.put("ownerName", ownerName);
                hashMap.put("ownerOccupation", ownerOccupation);
                hashMap.put("ownerPhoneNo", ownerContact);
                mDatabase.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(CarInfoDetailActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(CarInfoDetailActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Task<Void> deletedTask = mDatabase.removeValue();
                deletedTask.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CarInfoDetailActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent detailsIntent = new Intent(CarInfoDetailActivity.this, CarListActivity.class);
                            startActivity(detailsIntent);
                        } else {
                            Toast.makeText(CarInfoDetailActivity.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
        });
    }

    private void initData() {
        nNumberInfo.setText(data.getNoPlate());
        mCompanyName.setText(data.getCName());
        mModelNo.setText(data.getModelNumber());
        mCcData.setText(data.getCcInfo());
        mColor.setText(data.getColorInfo());
        mManufactureDate.setText(data.getManufactureDate());
        mOwnerName.setText(data.getOwnerName());
        mOwnerAddress.setText(data.getOwnerContactInfo());
        mOwnerOccupation.setText(data.getOwnerOccupation());
        mOwnerContact.setText(data.getOwnerPhoneNo());
        mID.setText(data.getId());
    }
}

package com.halo.carInfoWithAi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CarInfoByNumberPlateActivity extends AppCompatActivity {
    public static final String TAG1 = "TAG";
    public static final String TAG2 = "TAG";

    public  static  final  String EXTRA_POST_KEY = "POST_KEY";

    DatabaseReference mDatabase;
    TextView nNumberInfo;
    TextView mCompanyName;
    TextView mModelNo;
    TextView mCcData;
    TextView mColor;
    TextView mManufactureDate;
    TextView mOwnerName;
    TextView mOwnerAddress;
    TextView mOwnerContact;
    TextView mOwnerOccupation;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Data data;
    Button mBtnBack;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_info_details);
        nNumberInfo = findViewById(R.id.NumberPlate);
        mCompanyName =findViewById(R.id.CName1);
        mColor =findViewById(R.id.colorInfo1);
        mManufactureDate =findViewById(R.id.ManufactureDate1);
        mOwnerName =findViewById(R.id.ownerName1);
        mOwnerAddress =findViewById(R.id.ownerContactInfo1);
        mOwnerContact =findViewById(R.id.ownerPhoneNo1);
        mOwnerOccupation =findViewById(R.id.ownerOccupation1);
        mCcData =findViewById(R.id.ccInfo1);
        mModelNo =findViewById(R.id.modelNumber1);
        mBtnBack = findViewById(R.id.activity_care_detail_back_btn);

        final String postKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if(postKey == null){
            onBackPressed();
            return;
        }
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        Log.e("postKey",postKey+"");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CarInfoData").child(postKey);
        mDatabase.keepSynced(true);


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
                    CarInfoByNumberPlateActivity.this.data = data;
                }
                else{
                    CarInfoByNumberPlateActivity.this.data = new Data();
                }
                CarInfoByNumberPlateActivity.this.initData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("snapshot ERrr",error.toString()+"");
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "clicked");
                onBackPressed();
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
    }
    }

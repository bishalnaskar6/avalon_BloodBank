package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class activity_create extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView bloodType, address, phone,hospital;
    Button btnsubmit;
    ProgressDialog progressDialog;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bloodType=findViewById(R.id.editTextTextPersonName9);
        address=findViewById(R.id.editTextTextPersonName12);
        phone=findViewById(R.id.editTextTextPersonName10);
        hospital=findViewById(R.id.editTextTextPersonName11);
        btnsubmit=findViewById(R.id.button2);
        progressDialog= new ProgressDialog(this);;

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialogue();
            }
        });
    }

//    private void openDialogue() {
//
//        dialogue dialog= new dialogue();
//        dialog.show(getSupportFragmentManager(),"Data Uploaded Sucessful");
//    }

}
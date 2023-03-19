package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    TextView inputUserID,inputPassword,inputConfirmPassword,hos_name;
    Button btnRegister;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myref;
    FirebaseUser mUser;
    FirebaseAuth myAuth;
    FirebaseUser myUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inputUserID=findViewById(R.id.editTextTextPersonName2);
        inputPassword=findViewById(R.id.editTextTextPersonName4);
        inputConfirmPassword=findViewById(R.id.editTextTextPersonName5);
        hos_name=findViewById(R.id.editTextTextPersonName);
        btnRegister=findViewById(R.id.button6);
        progressDialog= new ProgressDialog(this);
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        myAuth = FirebaseAuth.getInstance();
        myUser = myAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String userID= inputUserID.getText().toString();
        String password= inputPassword.getText().toString();
        String confirmPassword= inputConfirmPassword.getText().toString();
        String hospitalName=hos_name.getText().toString();
        if (!userID.matches(emailPattern))
        {
            inputUserID.setError("Enter correct Email id");
        } else if (password.isEmpty()) {
            inputPassword.setError("Enter password");
        } else if (!password.equals(confirmPassword)) {
             inputConfirmPassword.setError("Password not match");
        }
        else {
            progressDialog.setMessage("Registration in progress");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            myAuth.createUserWithEmailAndPassword(userID,password);
            myref.setValue(userID, password).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(register.this,hos_dashboard.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); //this will stop to comeback automaticallly
        startActivity(intent);
    }
}
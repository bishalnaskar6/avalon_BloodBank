package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class register_donor extends AppCompatActivity {

    TextView inputUserID,inputPassword,inputConfirmPassword,phone,age,bloodgroup;
    Button btnRegister;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donor);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inputUserID=findViewById(R.id.editTextTextPersonName6);
        inputPassword=findViewById(R.id.editTextTextPassword4);
        inputConfirmPassword=findViewById(R.id.editTextTextPassword5);
        phone=findViewById(R.id.editTextPhone);
        age= findViewById(R.id.editTextNumber);
        bloodgroup=findViewById(R.id.editTextTextPersonName7);
        btnRegister=findViewById(R.id.button8);
        progressDialog= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

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
        String blood=bloodgroup.getText().toString();
        int phoneno= phone.getId();
        int Age=age.getId();

        if (!userID.matches(emailPattern))
        {
            inputUserID.setError("Enter correct Email id");
        } else if (password.isEmpty()) {
            inputPassword.setError("Enter password");
        } else if (!password.equals(confirmPassword)) {
            inputConfirmPassword.setError("Password not match");
        }else if (Age <= 18) {
            inputConfirmPassword.setError("You are uneligible");
        } else  {
            progressDialog.setMessage("Registration in progress");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(userID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(register_donor.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(register_donor.this,hos_dashboard.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); //this will stop to comeback automaticallly
        startActivity(intent);
    }
}

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

public class hos_login extends AppCompatActivity {

    TextView textview;
    TextView inputUserID,inputPassword;
    Button btnLogin;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inputUserID=findViewById(R.id.editTextTextPersonName);
        inputPassword=findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.button4);
        progressDialog= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        textview=(TextView) findViewById(R.id.textView4);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hos_login.this,register.class);
                startActivities(new Intent[]{intent});

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

    }

    private void performLogin() {
        String userID= inputUserID.getText().toString();
        String password= inputPassword.getText().toString();

        if (!userID.matches(emailPattern))
        {
            inputUserID.setError("Enter correct Email id");
        } else if (password.isEmpty()) {
            inputPassword.setError("Enter password");
        }
        else {
            progressDialog.setMessage("Login in progress");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(userID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(hos_login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(hos_login.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}

    private void sendUserToNextActivity() {
        Intent intent = new Intent(hos_login.this,hos_dashboard.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); //this will stop to comeback automaticallly
        startActivity(intent);
    }
    }
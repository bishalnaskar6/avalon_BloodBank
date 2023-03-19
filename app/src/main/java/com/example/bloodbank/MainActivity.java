package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button hos_button;
    Button donor_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hos_button=findViewById(R.id.button);
        donor_button=findViewById(R.id.button7);

        hos_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,hos_login.class);
            startActivities(new Intent[]{intent});
        });

        donor_button.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this,donor_login.class);
            startActivities(new Intent[]{intent1});
        });
    }
}
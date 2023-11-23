package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        Button back = findViewById(R.id.volver);

    }

    public void goBack (View view){
        Intent intent = new Intent(this, InitialActivity.class);
        startActivity(intent);
    }
}
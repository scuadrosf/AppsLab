package com.example.mindrelax;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private EditText newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton backButton = findViewById(R.id.btnBack);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        Button changePasswordButton = findViewById(R.id.changePasswordButton);

        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = preferences.getString("Username", "Usuario no encontrado");
        usernameTextView.setText(username);

        changePasswordButton.setOnClickListener(v -> {
            changePassword();
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void changePassword() {
        String newPassword = newPasswordEditText.getText().toString().trim();
        if (!newPassword.isEmpty()) {
            DatabaseHelper databaseHelper = new DatabaseHelper(Profile.this);

            SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String username = preferences.getString("Username", null);

            if (username != null) {
                databaseHelper.updateUserPassword(username, newPassword);
                Toast.makeText(Profile.this, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Profile.this, "Error al obtener el nombre de usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            newPasswordEditText.setError("La contraseña no puede estar vacía.");
        }
    }
}
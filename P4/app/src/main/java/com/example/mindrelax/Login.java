package com.example.mindrelax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            if (databaseHelper.checkUser(username, password)){
                // Usuario existe y la contraseña es correcta, iniciar sesión
                Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Username", username);
                editor.apply();

                 Intent intent = new Intent(Login.this, MainActivity.class);
                 startActivity(intent);
            } else {
                // Usuario no existe o la contraseña es incorrecta
                Toast.makeText(Login.this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                if (!databaseHelper.checkUser(username, password)) {
                    // Usuario no existe, puedes registrar uno nuevo
                    databaseHelper.addUser(username, password);
                    Toast.makeText(Login.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Usuario ya existe
                    Toast.makeText(Login.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Campos de texto vacíos
                Toast.makeText(Login.this, "Por favor, ingrese todos los detalles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
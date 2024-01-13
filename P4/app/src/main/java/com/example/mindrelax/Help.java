package com.example.mindrelax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView helpTextView = findViewById(R.id.helpText);
        Button closeHelpButton = findViewById(R.id.closeHelpButton);

        String helpText = getString(R.string.contenido_help); // Define un string largo en strings.xml
        helpTextView.setTextColor(getColor(R.color.black));
        helpTextView.setTextSize(18);
        helpTextView.setText(helpText);

        closeHelpButton.setOnClickListener(v -> finish());
    }
}
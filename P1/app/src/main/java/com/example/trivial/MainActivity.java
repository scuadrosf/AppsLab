package com.example.trivial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private Button nextButton, againButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        questionTextView = findViewById(R.id.questionTextView);
        nextButton = findViewById(R.id.nextButton);
        againButton = findViewById(R.id.againButton);

        // Inicializar vistas y preguntas aquí (usando findViewById)

        // Cargar preguntas
        questions = new ArrayList<>();
        questions.add(new Question("¿Cuál es el país de origen del fútbol?", new String[]{"Inglaterra", "España", "Alemania", "Francia"}, 0));
        questions.add(new Question("¿Qué equipo ha ganado la Copa del Mundo más veces?", new String[]{"Alemania", "Argentina", "Brasil", "Italia"}, 2));
        questions.add(new Question("¿Qué Copa de Europa ganó el Real Madrid gracias a la volea de Zidane?", new String[]{"Séptima", "Novena", "Octava", "Décima"}, 1));
        questions.add(new Question("¿Quién ha ganado más Balones de Oro en toda la historia?", new String[]{"Messi", "Cristiano Ronaldo", "Ronaldo Nazario", "Maradona"}, 0));
        questions.add(new Question("¿Cuál es el equipo de fútbol más antiguo del mundo?", new String[]{"Manchester United FC", "Sheffield FC", "Sevilla FC", "CA Boca Juniors "}, 1));


        showQuestionRadioButton();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Manejar el evento de selección/deselección de los RadioButtons aquí
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    String selectedText = selectedRadioButton.getText().toString();
                    // Puedes hacer algo con el texto seleccionado si es necesario
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {

                    showQuestionRadioButton();
                } else {
                    // Juego terminado, mostrar puntuación
                    Toast.makeText(MainActivity.this, "Puntuación FINAL: " + score, Toast.LENGTH_SHORT).show();
                    // Puedes reiniciar el juego aquí si es necesario
                }
            }
        });
    }

    private void showQuestionRadioButton() {
        radioGroup.clearCheck(); // Deselecciona cualquier RadioButton previamente seleccionado
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestion());
        String[] options = question.getOptions();

        // Elimina los RadioButton existentes antes de agregar nuevos
        radioGroup.removeAllViews();

        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioGroup.addView(radioButton);
        }
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        int correctAnswerIndex = question.getCorrectAnswerIndex();
        int selectedAnswerIndex = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));

        if (selectedAnswerIndex == correctAnswerIndex) {
            // Respuesta correcta
            score += 3;
            Toast.makeText(this, "¡Correcto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
        } else {
            // Respuesta incorrecta
            score -= 2;
            againButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "¡Incorrecto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
        }
    }
}

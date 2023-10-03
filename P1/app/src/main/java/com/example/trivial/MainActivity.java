package com.example.trivial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioButton option1RadioButton, option2RadioButton, option3RadioButton, option4RadioButton;
    private Button nextButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        nextButton = findViewById(R.id.nextButton);

        // Inicializar vistas y preguntas aquí (usando findViewById)

        // Cargar preguntas
        questions = new ArrayList<>();
        questions.add(new Question("¿Ejemplo de pregunta?", new String[]{"Opción 1", "Opción 2", "Opción 3", "Opción 4"}, 0));
        questions.add(new Question("¿Pregunta 2?", new String[]{"Adrian", "Marica", "Si", "Tambien"}, 2));

        // Agregar más preguntas según sea necesario

        showQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    // Juego terminado, mostrar puntuación
                    Toast.makeText(MainActivity.this, "Puntuación FINAL: " + score, Toast.LENGTH_SHORT).show();
                    // Puedes reiniciar el juego aquí si es necesario
                }
            }
        });
    }

    private void showQuestion() {
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestion());
        String[] options = question.getOptions();
        option1RadioButton.setText(options[0]);
        option2RadioButton.setText(options[1]);
        option3RadioButton.setText(options[2]);
        option4RadioButton.setText(options[3]);
    }

    private void checkAnswer() {
        Question question = questions.get(currentQuestionIndex);
        int correctAnswerIndex = question.getCorrectAnswerIndex();
        int selectedAnswerIndex = getCheckedRadioButtonIndex();

        if (selectedAnswerIndex == correctAnswerIndex) {
            // Respuesta correcta
            score += 3;
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
        } else {
            // Respuesta incorrecta
            score -= 2;
            Toast.makeText(this, "Incorrecto. La respuesta correcta es: " + question.getOptions()[correctAnswerIndex], Toast.LENGTH_SHORT).show();
        }
    }

    private int getCheckedRadioButtonIndex() {
        if (option1RadioButton.isChecked()) {
            return 0;
        } else if (option2RadioButton.isChecked()) {
            return 1;
        } else if (option3RadioButton.isChecked()) {
            return 2;
        } else if (option4RadioButton.isChecked()) {
            return 3;
        }
        return -1; // Ninguna opción seleccionada
    }




}

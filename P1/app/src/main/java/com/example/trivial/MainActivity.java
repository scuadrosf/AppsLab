package com.example.trivial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    public int getScore(){
        return score;
    }

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
        questions.add(new Question("¿Cuál es el país de origen del fútbol?", new String[]{"Inglaterra", "España", "Alemania", "Francia"}, 0,false));
        questions.add(new Question("¿Qué equipo ha ganado la Copa del Mundo más veces?", new Integer[]{R.drawable.alemania, R.drawable.argentina, R.drawable.brasil, R.drawable.italia}, 2, true));
        questions.add(new Question("¿Qué Copa de Europa ganó el Real Madrid gracias a la volea de Zidane?", new String[]{"Séptima", "Novena", "Octava", "Décima"}, 1, false));
        questions.add(new Question("¿Quién ha ganado más Balones de Oro en toda la historia?", new String[]{"Messi", "Cristiano Ronaldo", "Ronaldo Nazario", "Maradona"}, 0, false));
        questions.add(new Question("¿Cuál es el equipo de fútbol más antiguo del mundo?", new String[]{"Manchester United FC", "Sheffield FC", "Sevilla FC", "CA Boca Juniors "}, 1, false));


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
                    if(currentQuestionIndex == 1){
                        showQuestionImage();
                    }else{
                        showQuestionRadioButton();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Puntuación FINAL: " + score, Toast.LENGTH_SHORT).show();
                    startFinal(view);
                }
            }
        });
    }

    private void showQuestionImage(){
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestion());
        Integer[] options = (Integer[]) question.getOptions();

        // Elimina las vistas existentes antes de agregar nuevas
        radioGroup.removeAllViews();

        for (int i = 0; i < options.length; i++) {
            ImageButton imageButton = new ImageButton(this);
            imageButton.findViewById(R.id.imageButton2);
            imageButton.setImageResource(options[i]);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER); // Ajusta la imagen al tamaño del ImageButton
            //imageButton.setBackgroundResource(android.R.drawable.btn_border); // Establece un borde para el ImageButton
            int finalI = i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleImageOptionClick(finalI);
                }
            });
            radioGroup.addView(imageButton);
        }
    }

    private void handleImageOptionClick(int optionIndex) {
        Question question = questions.get(currentQuestionIndex);
       // boolean[] isOptionImage = question.getIsOptionImage();

        if (optionIndex == question.getCorrectAnswerIndex()) {
            score += 3;
            // La opción seleccionada es una imagen y es la respuesta correcta
            // Implementa la lógica para una respuesta correcta
            // Por ejemplo, mostrar un mensaje indicando que la respuesta es correcta
            Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
        } else {
            score -= 2;
            againButton.setVisibility(View.VISIBLE);
            // La opción seleccionada es una imagen pero no es la respuesta correcta
            // Implementa la lógica para una respuesta incorrecta
            // Por ejemplo, mostrar un mensaje indicando que la respuesta es incorrecta
            Toast.makeText(this, "Respuesta incorrecta. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showQuestionRadioButton() {
        radioGroup.clearCheck(); // Deselecciona cualquier RadioButton previamente seleccionado
        Question question = questions.get(currentQuestionIndex);
        questionTextView.setText(question.getQuestion());
        String[] options = (String[]) question.getOptions();

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

    private void startFinal(View view){
        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("SCORE_FINAL", score);
        startActivity(intent);
    }


}

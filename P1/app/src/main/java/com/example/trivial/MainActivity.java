package com.example.trivial;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private MediaPlayer mediaPlayer;
    private Button nextButton, againButton;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private DbQuestions dbQuestions; // Add this line


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!checkDataBase("/data/data/com.example.trivial/databases/trivial.db")){
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null){
                Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
            }
        }
        dbQuestions = new DbQuestions(MainActivity.this); // Initialize DbQuestions


        // Obtener la instancia de la barra de herramientas
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el icono en el Toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        radioGroup = findViewById(R.id.radioGroup);
        questionTextView = findViewById(R.id.questionTextView);
        nextButton = findViewById(R.id.nextButton);
        againButton = findViewById(R.id.againButton);



//        playBackMusic();
//        loadData(); // Load questions from the database
//        showQuestionRadioButton();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                if (selectedRadioButton != null) {
                    selectedRadioButton.getText();
                }
            }
        });

//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(currentQuestionIndex != 1)
//                    checkAnswer();
//                currentQuestionIndex++;
//                if (currentQuestionIndex < questions.size()) {
//                    if(currentQuestionIndex == 1){
//                        showQuestionImage();
//                    }else{
//                        showQuestionRadioButton();
//                    }
//                } else {
//                    startFinal(view);
//                }
//            }
//        });

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Cierra todas las actividades anteriores
                score = 0;
                startActivity(intent);

            }
        });
    }

    private boolean checkDataBase(String Database_path) {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(Database_path, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "NO EXISTE LA BASE DE DATOS", Toast.LENGTH_LONG).show();
        }
        return checkDB != null;
    }

    private void loadData() {
        questions = new ArrayList<>(dbQuestions.getQuestionsList());
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        showQuestionRadioButton();
//    }
//
//
//
//    private void showQuestionImage() {
//        Question question = questions.get(currentQuestionIndex);
//        questionTextView.setText(question.getQuestion());
//
//        String fullOptionsImage = question.getOptionsImage();
//        String[] options = fullOptionsImage.split(",");
//
//        radioGroup.removeAllViews();
//
//        for (int i = 0; i < options.length; i++) {
//            if (options[i] instanceof String) {
//                // Si la opción es una imagen
//                ImageButton imageButton = new ImageButton(this);
//                imageButton.setImageResource(Integer.parseInt(options[i]));
//                imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                int finalI = i;
//                imageButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        handleImageOptionClick(finalI);
//                    }
//                });
//                radioGroup.addView(imageButton);
//            } else if (options[i] instanceof String) {
//                // Si la opción es texto
//                RadioButton radioButton = new RadioButton(this);
//                radioButton.setText((String) options[i]);
//                radioGroup.addView(radioButton);
//            }
//        }
//    }
////
//    private void handleImageOptionClick(int optionIndex) {
//        Question question = questions.get(currentQuestionIndex);
//
//        if (optionIndex == question.getCorrectAnswerIndex()) {
//            score += 3;
//            playCorrectSound();
//            Toast.makeText(this, "¡Correcto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
//        } else {
//            score -= 2;
//            againButton.setVisibility(View.VISIBLE);
//            playErrorSound();
//            Toast.makeText(this, "¡Incorrecto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
//        }
//    }
////
//    private void showQuestionRadioButton() {
//        radioGroup.clearCheck(); // Deselecciona cualquier RadioButton previamente seleccionado
//        Question question = questions.get(currentQuestionIndex);
//
//        questionTextView.setText(question.getQuestion());
//
//        String fullOptions = question.getOptionsText();
//        String[] options = fullOptions.split(",");
//
//
//        radioGroup.removeAllViews();
//
//        // Obtiene los radiobuttons del xml del activity
//        List<RadioButton> radioButtons = new ArrayList<>();
//        for (int i = 0; i < options.length; i++) {
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setTextSize(22);
//            radioButton.setId(getResources().getIdentifier("radioButton_" + i, "id", getPackageName()));
//            radioButtons.add(radioButton);
//        }
//
//        // Itera sobre los radio buttons y los crea añadiéndolos al RadioGroup
//        for (RadioButton radioButton : radioButtons) {
//            radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
//            radioButton.setPadding(16, 16, 16, 16);
//            radioButton.setText(options[radioButtons.indexOf(radioButton)]);
//            radioGroup.addView(radioButton);
//        }
//    }
////
//    private void checkAnswer() {
//        Question question = questions.get(currentQuestionIndex);
//        int correctAnswerIndex = question.getCorrectAnswerIndex();
//        int selectedAnswerIndex = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
//
//        if (selectedAnswerIndex == correctAnswerIndex) {
//            // Respuesta correcta
//            playCorrectSound();
//            score += 3;
//            Toast.makeText(this, "¡Correcto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
//        } else {
//            // Respuesta incorrecta
//            score -= 2;
//            playErrorSound();
//            againButton.setVisibility(View.VISIBLE);
//            Toast.makeText(this, "¡Incorrecto! Puntuación: " + score, Toast.LENGTH_SHORT).show();
//        }
//    }
////
//    private void startFinal(View view){
//        Intent intent = new Intent(this, FinalActivity.class);
//        intent.putExtra("SCORE_FINAL", score);
//        stopBackMusic();
//        startActivity(intent);
//    }
////
//    private void playCorrectSound() {
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.correct);
//        mediaPlayer.start();
//        // Liberar recursos después de que se complete la reproducción
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                mediaPlayer.release();
//            }
//        });
//    }
////
//    private void playErrorSound() {
//        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.error);
//        mediaPlayer.start();
//        // Liberar recursos después de que se complete la reproducción
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                mediaPlayer.release();
//            }
//        });
//    }
////
//    private void playBackMusic() {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
//        }
//        mediaPlayer.start();
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                // Reiniciar la reproducción desde el principio
//                mediaPlayer.seekTo(0);
//                mediaPlayer.start();
//            }
//        });
//    }
////
//    private void stopBackMusic() {
//        if (mediaPlayer != null) {
//            mediaPlayer.release();
//            mediaPlayer = null;
//        }
//    }

}

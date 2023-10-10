package com.example.trivial;

public class Question {
    private String question;
    private Object[] options; // Array de objetos que pueden ser tanto texto como imágenes
    private int correctAnswerIndex;
    private boolean isImageQuestion; // Indica si la pregunta tiene opciones de imagen

    public Question(String question, Object[] options, int correctAnswerIndex, boolean isImageQuestion) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.isImageQuestion = isImageQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public Object[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isImageQuestion() {
        return isImageQuestion;
    }
}

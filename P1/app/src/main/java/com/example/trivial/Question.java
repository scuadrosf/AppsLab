package com.example.trivial;

public class Question {
    private final String question;
    private final Object[] options; // Array de objetos que pueden ser tanto texto como imágenes
    private final int correctAnswerIndex;

    public Question(String question, Object[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
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

}

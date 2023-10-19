package com.example.trivial;

public class Question {
    private String question;
    private Object[] options; // Array de objetos que pueden ser tanto texto como imágenes
    private int correctAnswerIndex;

    public Question(String question, Object[] options, int correctAnswerIndex, boolean isImageQuestion) {
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

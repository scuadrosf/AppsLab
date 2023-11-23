package com.example.trivial;

public class Question {
    private String question;

    private String optionsText;

    private String optionsImage;

   // private final Object[] options; // Array de objetos que pueden ser tanto texto como imágenes
    private int correctAnswerIndex;

    public Question(String question, String optionsText, String optionsImage, int correctAnswerIndex) {
        this.question = question;
        this.optionsText = optionsText;
        this.optionsImage = optionsImage;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionsText() {
        return optionsText;
    }

    public String getOptionsImage() {
        return optionsImage;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionsText(String optionsText) {
        this.optionsText = optionsText;
    }

    public void setOptionsImage(String optionsImage) {
        this.optionsImage = optionsImage;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

package com.example.trivial;

public class Question {
    private String question;

    private String optionsText;

    private String optionsImage;

    private int correctAnswerIndex;

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

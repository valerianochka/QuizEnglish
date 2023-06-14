package com.example.quizenglish.models;

public class VocabularyModel {
    private String vocabularyId,vocabularyTitle,vocabularyImg,vocabularyWords,vocabularyLevel;

    public VocabularyModel(String vocabularyId, String vocabularyTitle, String vocabularyImg, String vocabularyWords, String vocabularyLevel) {
        this.vocabularyId = vocabularyId;
        this.vocabularyTitle = vocabularyTitle;
        this.vocabularyImg = vocabularyImg;
        this.vocabularyWords = vocabularyWords;
        this.vocabularyLevel = vocabularyLevel;
    }

    public VocabularyModel(){}

    public String getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(String vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public String getVocabularyTitle() {
        return vocabularyTitle;
    }

    public void setVocabularyTitle(String vocabularyTitle) {
        this.vocabularyTitle = vocabularyTitle;
    }

    public String getVocabularyImg() {
        return vocabularyImg;
    }

    public void setVocabularyImg(String vocabularyImg) {
        this.vocabularyImg = vocabularyImg;
    }

    public String getVocabularyWords() {
        return vocabularyWords;
    }

    public void setVocabularyWords(String vocabularyWords) {
        this.vocabularyWords = vocabularyWords;
    }

    public String getVocabularyLevel() {
        return vocabularyLevel;
    }

    public void setVocabularyLevel(String vocabularyLevel) {
        this.vocabularyLevel = vocabularyLevel;
    }
}
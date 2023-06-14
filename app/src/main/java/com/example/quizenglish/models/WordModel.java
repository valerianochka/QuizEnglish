package com.example.quizenglish.models;

public class WordModel {
    String wordId, wordSound, wordImg, wordEn, wordRu, wordType, wordDescriptionEn, wordDescriptionRu, wordExampleEn, wordExampleRu;

    public WordModel(String wordId, String wordSound, String wordImg, String wordEn, String wordRu, String wordType, String wordDescriptionEn, String wordDescriptionRu, String wordExampleEn, String wordExampleRu) {
        this.wordId = wordId;
        this.wordSound = wordSound;
        this.wordImg = wordImg;
        this.wordEn = wordEn;
        this.wordRu = wordRu;
        this.wordType = wordType;
        this.wordDescriptionEn = wordDescriptionEn;
        this.wordDescriptionRu = wordDescriptionRu;
        this.wordExampleEn = wordExampleEn;
        this.wordExampleRu = wordExampleRu;
    }

    public WordModel(){}

    public String getWordImg() {
        return wordImg;
    }

    public void setWordImg(String wordImg) {
        this.wordImg = wordImg;
    }

    public String getWordSound() {
        return wordSound;
    }

    public void setWordSound(String wordSound) {
        this.wordSound = wordSound;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }

    public String getWordRu() {
        return wordRu;
    }

    public void setWordRu(String wordRu) {
        this.wordRu = wordRu;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getWordDescriptionEn() {
        return wordDescriptionEn;
    }

    public void setWordDescriptionEn(String wordDescriptionEn) {
        this.wordDescriptionEn = wordDescriptionEn;
    }

    public String getWordDescriptionRu() {
        return wordDescriptionRu;
    }

    public void setWordDescriptionRu(String wordDescriptionRu) {
        this.wordDescriptionRu = wordDescriptionRu;
    }

    public String getWordExampleEn() {
        return wordExampleEn;
    }

    public void setWordExampleEn(String wordExampleEn) {
        this.wordExampleEn = wordExampleEn;
    }

    public String getWordExampleRu() {
        return wordExampleRu;
    }

    public void setWordExampleRu(String wordExampleRu) {
        this.wordExampleRu = wordExampleRu;
    }
}
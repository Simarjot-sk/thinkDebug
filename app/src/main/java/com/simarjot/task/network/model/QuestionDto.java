package com.simarjot.task.network.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionDto {
    @SerializedName("qq_id")
    private int questionId;

    @SerializedName("qs_name")
    private String questionTitle;

    @SerializedName("qs_image")
    private String questionImageUrl;

    @SerializedName("option")
    private List<Option> options;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionImageUrl() {
        return questionImageUrl;
    }

    public void setQuestionImageUrl(String questionImageUrl) {
        this.questionImageUrl = questionImageUrl;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public QuestionDto(){ }

    public QuestionDto(int questionId, String questionTitle, String questionImageUrl, List<Option> options) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionImageUrl = questionImageUrl;
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionImageUrl='" + questionImageUrl + '\'' +
                ", options=" + options +
                '}';
    }
}

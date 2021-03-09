package com.simarjot.task.network.model;

import com.google.gson.annotations.SerializedName;

public class Option {
    @SerializedName("op_id")
    private int optionId;

    @SerializedName("qs_id")
    private int questionId;

    @SerializedName("opn_name")
    private String optionName;

    @SerializedName("opn_image")
    private String optionImage;

    @SerializedName("ans_status")
    private int answerStatus;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionImage() {
        return optionImage;
    }

    public void setOptionImage(String optionImage) {
        this.optionImage = optionImage;
    }

    public int getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(int answerStatus) {
        this.answerStatus = answerStatus;
    }

    public Option(int optionId, int questionId, String optionName, String optionImage, int answerStatus) {
        this.optionId = optionId;
        this.questionId = questionId;
        this.optionName = optionName;
        this.optionImage = optionImage;
        this.answerStatus = answerStatus;
    }

    public Option() {
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", questionId=" + questionId +
                ", optionName='" + optionName + '\'' +
                ", optionImage='" + optionImage + '\'' +
                ", answerStatus=" + answerStatus +
                '}';
    }
}

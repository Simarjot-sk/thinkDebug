package com.simarjot.task.ui.model;

public class SelectedOption {
    public int questionId;
    public int optionId;

    public SelectedOption(int questionId){
        this.questionId = questionId;
        optionId = -1;//when no option is selected, option id will be -1
    }
}

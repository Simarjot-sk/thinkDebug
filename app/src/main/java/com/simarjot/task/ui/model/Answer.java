package com.simarjot.task.ui.model;

import com.simarjot.task.network.model.Option;

public class Answer {
    public int questionId;
    public Option option;

    public Answer(int questionId){
        this.questionId = questionId;
        option = null;//when no option is selected, option will be null
    }
}

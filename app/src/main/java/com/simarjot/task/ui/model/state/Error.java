package com.simarjot.task.ui.model.state;

public class Error<T> extends State<T> {
    public String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

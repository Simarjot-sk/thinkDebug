package com.simarjot.task.ui.state;

public class DataFetched<T> extends State<T>{
    public T data;

    public DataFetched(T data) {
        this.data = data;
    }
}

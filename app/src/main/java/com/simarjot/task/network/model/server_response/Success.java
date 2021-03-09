package com.simarjot.task.network.model.server_response;

public class Success<T> extends ServerResponse<T> {
    private T data;

    public Success(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

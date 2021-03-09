package com.simarjot.task.network.model.server_response;

public class Failure<T> extends ServerResponse<T> {
    private String reason;

    public Failure(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

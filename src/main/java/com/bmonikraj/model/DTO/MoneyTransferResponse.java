package com.bmonikraj.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyTransferResponse {
    @JsonProperty
    private String status;

    @JsonProperty
    private String message;

    @JsonProperty
    private String referenceId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}

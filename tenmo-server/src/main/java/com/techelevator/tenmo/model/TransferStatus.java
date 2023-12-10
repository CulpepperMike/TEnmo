package com.techelevator.tenmo.model;

public class TransferStatus {
    private int id;
    private String description;
    private boolean isApproved;

    public TransferStatus(int transferStatusID, String transferStatusDesc) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

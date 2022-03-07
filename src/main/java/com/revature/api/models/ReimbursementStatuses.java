package com.revature.api.models;

import java.util.Objects;

public class ReimbursementStatuses {


    private String statusId;
    private String status;

    public ReimbursementStatuses() {
        super();
    }

    public ReimbursementStatuses(String statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementStatuses reimbursementStatuses = (ReimbursementStatuses) o;
        return Objects.equals(statusId, reimbursementStatuses.statusId) && Objects.equals(status, reimbursementStatuses.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }


    @Override
    public String toString() {
        return "reimbursementStatuses{" +
                "statusId='" + statusId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
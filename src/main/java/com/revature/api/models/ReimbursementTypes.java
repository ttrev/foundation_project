package com.revature.api.models;

import java.util.Objects;

public class ReimbursementTypes {

    private String typeId;
    private String type;

    public ReimbursementTypes() {
        super();
    }

    public ReimbursementTypes(String typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbursementTypes reimbursement = (ReimbursementTypes) o;
        return Objects.equals(typeId, reimbursement.typeId) && Objects.equals(type, reimbursement.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }

    @Override
    public String toString() {
        return "ReimbursementTypes{" +
                "typeId='" + typeId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
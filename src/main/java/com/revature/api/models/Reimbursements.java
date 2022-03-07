package com.revature.api.models;

import java.sql.Timestamp;

import java.util.Objects;

public class Reimbursements {

        private String reimbId;
        private int amount;
        private Timestamp submitted;
        private Timestamp resolved;
        private String description;
        private byte receipt;
        private String paymentId;
        private String authorId;
        private String resolverId;
        private String statusId;
        private String typeId;

        public Reimbursements() {
            super();
        }

        public Reimbursements(String reimbId, int amount, Timestamp submitted, Timestamp resolved, String description, byte receipt, String paymentId, String authorId, String resolverId, String statusId, String typeId) {
            this.reimbId = reimbId;
            this.amount = amount;
            this.submitted = submitted;
            this.resolved = resolved;
            this.description = description;
            this.receipt = receipt;
            this.paymentId = paymentId;
            this.authorId = authorId;
            this.resolverId = resolverId;
            this.statusId = statusId;
            this.typeId = typeId;
        }

        public String getReimbId() {
            return reimbId;
        }

        public void setReimbId(String reimbId) {
            this.reimbId = reimbId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Timestamp getSubmitted() {
            return submitted;
        }

        public void setSubmitted(Timestamp submitted) {
            this.submitted = submitted;
        }

        public Timestamp getResolved() {
            return resolved;
        }

        public void setResolved(Timestamp resolved) {
            this.resolved = resolved;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public byte getReceipt() {
            return receipt;
        }

        public void setReceipt(byte receipt) {
            this.receipt = receipt;
        }

        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getAuthorId() {
            return authorId;
        }

        public void setAuthorId(String authorId) {
            this.authorId = authorId;
        }

        public String getResolverId() {
            return resolverId;
        }

        public void setResolverId(String resolverId) {
            this.resolverId = resolverId;
        }

        public String getStatusId() {
            return statusId;
        }

        public void setStatusId(String statusId) {
            this.statusId = statusId;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursements reimbursements = (Reimbursements) o;
        return amount == reimbursements.amount && receipt == reimbursements.receipt && Objects.equals(reimbId, reimbursements.reimbId) && Objects.equals(submitted, reimbursements.submitted) && Objects.equals(resolved, reimbursements.resolved) && Objects.equals(description, reimbursements.description) && Objects.equals(paymentId, reimbursements.paymentId) && Objects.equals(authorId, reimbursements.authorId) && Objects.equals(resolverId, reimbursements.resolverId) && Objects.equals(statusId, reimbursements.statusId) && Objects.equals(typeId, reimbursements.typeId);

    }


    @Override
    public int hashCode(){
            return Objects.hash(reimbId, amount, submitted, resolved, description, receipt, paymentId, authorId, resolverId, statusId, typeId);
    }

    @Override
        public String toString() {
            return "Reimbursements{" +
                    "reimbId='" + reimbId + '\'' +
                    ", amount=" + amount +
                    ", submitted=" + submitted +
                    ", resolved=" + resolved +
                    ", description='" + description + '\'' +
                    ", receipt=" + receipt +
                    ", paymentId='" + paymentId + '\'' +
                    ", authorId='" + authorId + '\'' +
                    ", resolverId='" + resolverId + '\'' +
                    ", statusId='" + statusId + '\'' +
                    ", typeId='" + typeId + '\'' +
                    '}';
        }
    }



package com.br.martins.learningspringboot.dto;

import com.br.martins.learningspringboot.models.User;

public class CreateTransactionDto {

    private long payer_id;

    private long payee_id;

    private float transaction_value;

    public long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(long payer_id) {
        this.payer_id = payer_id;
    }

    public long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(long payee_id) {
        this.payee_id = payee_id;
    }

    public float getTransaction_value() {
        return transaction_value;
    }

    public void setTransaction_value(float transaction_value) {
        this.transaction_value = transaction_value;
    }
}

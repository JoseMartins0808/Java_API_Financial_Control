package com.br.martins.learningspringboot.dto;

import com.br.martins.learningspringboot.models.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreateTransactionDto {

    @NotNull(message = "Payer_id cannot be null")
    private long payer_id;

    @NotNull(message = "Payee_id cannot be null")
    private long payee_id;

    @NotNull(message = "Transaction value cannot be null")
    @DecimalMin(value = "0.01", message = "Transaction value must be higher than 0.01")
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

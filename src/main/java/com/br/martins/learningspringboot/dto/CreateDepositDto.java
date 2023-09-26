package com.br.martins.learningspringboot.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreateDepositDto {

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.01", message = "Deposit value must be higher than 0.01")
    private float depositValue;

    public float getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(float depositValue) {
        this.depositValue = depositValue;
    }
}

package com.br.martins.learningspringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Name is required")
    @Size(max= 92, message = "Name must be lower than 92 characters long")
    private String name;

    @NotEmpty(message = "Cpf is required")
    @Size(max= 11, message = "Cpf must be lower than 11 characters long")
    private String cpf;

    @NotEmpty(message = "Email is required")
    @Size(max= 62, message = "Email must be lower than 62 characters long")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(max= 120, message = "Password must be lower than 120 characters long")
    private String password;

    @NotEmpty(message = "Type is required")
    @Pattern(regexp = "(COMMON|SELLER)", message = "Type must be COMMON or SELLER")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

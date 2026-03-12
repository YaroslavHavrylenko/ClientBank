package org.example.clientbank.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyUpdateCustomer {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private int age;
}
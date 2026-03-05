package org.example.clientbank.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.clientbank.model.Currency;

@Getter
@Setter
public class RequestBodyCreateAccount {
    private Currency currency;
}

package org.example.clientbank.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyRechargeAccount {
    private String number;
    private Double amount;
}

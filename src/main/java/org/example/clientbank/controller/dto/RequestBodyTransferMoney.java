package org.example.clientbank.controller.dto;

import lombok.Getter;

@Getter
public class RequestBodyTransferMoney {
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;
}

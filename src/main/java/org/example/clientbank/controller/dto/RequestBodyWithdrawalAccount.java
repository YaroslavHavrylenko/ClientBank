package org.example.clientbank.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyWithdrawalAccount {
    String number;
    Double amount;
}

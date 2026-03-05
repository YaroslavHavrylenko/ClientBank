package org.example.clientbank.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clientbank.controller.dto.RequestBodyRechargeAccount;
import org.example.clientbank.controller.dto.RequestBodyTransferMoney;
import org.example.clientbank.controller.dto.RequestBodyWithdrawalAccount;
import org.example.clientbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accoutnts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/rechargeAccount")
    public ResponseEntity<Boolean> rechargeAccount(@Valid @RequestBody RequestBodyRechargeAccount body) {
        boolean isRecharged = accountService.rechargeAccount(body.getNumber(), body.getAmount());
        if (isRecharged) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Boolean> withdrawal(@Valid @RequestBody RequestBodyWithdrawalAccount body) {
        boolean isWithdrawal = accountService.withdrawal(body.getNumber(), body.getAmount());
        if (isWithdrawal) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/transferMoney")
    public ResponseEntity<Void> transferMoney(@Valid @RequestBody RequestBodyTransferMoney body) {
        boolean isTransfer = accountService.transferMoney(body.getFromAccountNumber(), body.getToAccountNumber(), body.getAmount());
        if (isTransfer) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }
}

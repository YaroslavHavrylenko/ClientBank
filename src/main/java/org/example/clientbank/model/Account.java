package org.example.clientbank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String number = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    @Positive
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.currency = currency;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getNumber(), account.getNumber()) && getCurrency() == account.getCurrency() && Objects.equals(getCustomer(), account.getCustomer());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", customer=" + customer.getName() +
                ", balance=" + balance +
                " " + currency.name() + '}';
    }
}
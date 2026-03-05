package org.example.clientbank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Table(name = "customer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Customer {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private Integer age;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void updateAccount(Account account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setCustomer(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) && Objects.equals(getEmail(), customer.getEmail()) && Objects.equals(getAge(), customer.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getAge());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name = " + name +
                ", email = " + email +
                ", age = " + getAge() +
                " Bank's account = " + accounts.stream()
                .map(account -> account.getId() + " = "
                        + account.getBalance() + " "
                        + account.getCurrency())
                .collect(Collectors.joining(", ")) + "}";
    }
}

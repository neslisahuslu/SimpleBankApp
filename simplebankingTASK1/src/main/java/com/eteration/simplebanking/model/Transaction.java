package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transaction")
public abstract class Transaction implements Serializable {
    public Transaction(double amount) {
        setAmount(amount);
    }

    @Id
    @Column(name = "approval_code",  columnDefinition = "uuid")
    private UUID approvalCode;

    @Column(name = "type")
    private String type;

    @CreatedDate
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Instant date = Instant.now();

    @Column(name = "amount")
    private Double amount;

    @JsonIgnore
    @Column(name = "account_number")
    @JoinColumn(name="account_number",referencedColumnName="account_number", insertable=false, updatable=false)
    private String accountNumber;

    @Override
    public String toString() {
        return "Transaction{" +
                "approvalCode=" + approvalCode +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    public abstract void process(Account account);


}

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
@Table(name="transaction")
@Entity
public  abstract class Transaction implements Serializable {

    public Transaction(double amount) {
        this.amount = amount;
    }

    @Id
    @Column(name = "approval_code",  columnDefinition = "uuid")
    private UUID approvalCode = UUID.randomUUID();

    @CreatedDate
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Instant date = Instant.now();

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @Column(name = "account_number")
    @JoinColumn(name="account_number",referencedColumnName="account_number",
            insertable=false, updatable=false)
    private String accountNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_number", referencedColumnName = "account_number",insertable=false, updatable=false)
    private Account account;


}

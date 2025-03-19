package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name="account_number")
    private String accountNumber;

    @Column(name="owner")
    private String owner;

    @Column(name="balance")
    private double balance = 0.0;

    @CreatedDate
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createDate = new Date();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();



    public Account(String owner, String accountNumber){
        setOwner(owner);
        setAccountNumber(accountNumber);
    }
}

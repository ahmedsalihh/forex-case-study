package com.ahmedsalihh.forexcasestudy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "conversion")
public class Conversion {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double totalCalculatedAmount;
    private Date transactionDate;
}

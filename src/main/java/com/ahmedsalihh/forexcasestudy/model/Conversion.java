package com.ahmedsalihh.forexcasestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "conversion")
public class Conversion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @JsonIgnore
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double totalCalculatedAmount;
    private LocalDate transactionDate;
}

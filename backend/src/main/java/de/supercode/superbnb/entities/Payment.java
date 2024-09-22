package de.supercode.superbnb.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.YearMonth;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private String cvv;
    private YearMonth expirationDate;

    public Payment() {
    }

    public Payment(String cardNumber, String cvv, YearMonth expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
}

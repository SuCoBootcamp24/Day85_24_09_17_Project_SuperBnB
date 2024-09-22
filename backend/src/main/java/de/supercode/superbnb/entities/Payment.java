package de.supercode.superbnb.entities;

import de.supercode.superbnb.entities.person.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private String cvv;
    private String expirationDate;

    @OneToOne
    private User user;
}

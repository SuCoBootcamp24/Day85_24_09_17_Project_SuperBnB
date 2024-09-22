package de.supercode.superbnb.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int beds;
    private int bathrooms;
    private float rating;
    private BigDecimal priceAtNight;
    private boolean available;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}

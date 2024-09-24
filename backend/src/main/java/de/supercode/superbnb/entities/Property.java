package de.supercode.superbnb.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int guestsCapacity;
    private int rooms;
    private int beds;

    private int bathrooms;
    private float rating;
    private BigDecimal priceAtNight;

    private boolean available = false;


    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookingList;

}

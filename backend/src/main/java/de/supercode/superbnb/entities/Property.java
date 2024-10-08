package de.supercode.superbnb.entities;

import de.supercode.superbnb.entities.person.Booking;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Property name must not be null and not empty")
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

    @OneToMany
    private List<Booking> bookingList;

    @OneToMany
    private List<ImagesProperty> images;

}

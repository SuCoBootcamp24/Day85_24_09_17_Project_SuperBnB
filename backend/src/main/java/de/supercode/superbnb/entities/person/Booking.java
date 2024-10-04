package de.supercode.superbnb.entities.person;

import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int guests;


    @Future(message = "The Check-in date must in the future")
    @Temporal(TemporalType.DATE)
    private LocalDate checkInDate;


    @Future(message = "The Check-out date must in the future")
    @Temporal(TemporalType.DATE)
    private LocalDate checkOutDate;

    @CreationTimestamp
    private LocalDateTime bookingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;






}

package de.supercode.superbnb.entities.person;

import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    private Set<Property> favorites = new HashSet<>();
}

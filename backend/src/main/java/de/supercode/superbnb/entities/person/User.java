package de.supercode.superbnb.entities.person;

import de.supercode.superbnb.entities.Address;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Role role;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}

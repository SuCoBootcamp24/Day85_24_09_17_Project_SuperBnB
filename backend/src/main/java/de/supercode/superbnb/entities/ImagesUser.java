package de.supercode.superbnb.entities;

import de.supercode.superbnb.entities.person.User;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ImagesUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ImageName;

    @Column(length = 500)
    private String imageURL;

    @Column(length = 500)
    private String deleteURL;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

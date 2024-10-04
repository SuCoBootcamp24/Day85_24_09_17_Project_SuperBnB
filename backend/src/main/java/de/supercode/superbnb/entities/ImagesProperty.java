package de.supercode.superbnb.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ImagesProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ImageName;

    @Column(length = 500)
    private String imageURL;

    @Column(length = 500)
    private String deleteURL;

    private boolean thumbnailI = false;

    @Column(nullable = false)
    private long propertyId;
}

package de.supercode.superbnb.dtos.images.properties;

public record ImagesPropertyResponseDTO(
        long id,

        String imageName,

        String imageURL,

        String deleteURL

) {
}

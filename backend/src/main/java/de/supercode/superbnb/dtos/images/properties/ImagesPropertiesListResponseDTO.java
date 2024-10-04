package de.supercode.superbnb.dtos.images.properties;

import java.util.List;

public record ImagesPropertiesListResponseDTO(
        long propertyId,

        ImagesPropertyResponseDTO thumbnail,

        List<ImagesPropertyResponseDTO> images
) {
}

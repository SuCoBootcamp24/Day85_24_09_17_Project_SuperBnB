package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.images.properties.ImagesPropertiesListResponseDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertyResponseDTO;
import de.supercode.superbnb.entities.ImagesProperty;
import de.supercode.superbnb.entities.Property;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImagesMapper {

    public ImagesPropertiesListResponseDTO toImagesPropertiesListResponseDTO(Property property) {
        Optional<ImagesProperty> thumbnailImage = property.getImages().stream()
                .filter(ImagesProperty::isThumbnailI)
                .findFirst();

        ImagesPropertyResponseDTO thumbnail = thumbnailImage
                .map(this::toImagesPropertyResponseDTO)
                .orElse(null);

        List<ImagesPropertyResponseDTO> imagesList = property.getImages().stream()
                .map(this::toImagesPropertyResponseDTO)
                .collect(Collectors.toList());

        return new ImagesPropertiesListResponseDTO(
                property.getId(),
                thumbnail,
                imagesList);
    }


    public ImagesPropertyResponseDTO toImagesPropertyResponseDTO(ImagesProperty imagesProperty) {
        return Optional.ofNullable(imagesProperty)
                .map(imgProp -> new ImagesPropertyResponseDTO(
                        imgProp.getId(),
                        imgProp.getImageName(),
                        imgProp.getImageURL(),
                        imgProp.getDeleteURL()
                ))
                .orElse(null);
    }


}

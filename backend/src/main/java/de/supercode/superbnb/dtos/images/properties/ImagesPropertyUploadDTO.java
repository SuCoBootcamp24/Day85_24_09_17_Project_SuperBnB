package de.supercode.superbnb.dtos.images.properties;

import org.springframework.web.multipart.MultipartFile;

public record ImagesPropertyUploadDTO(

        long propertyId,
        String imageName,

        boolean thumbnail,

        MultipartFile file
) {
}

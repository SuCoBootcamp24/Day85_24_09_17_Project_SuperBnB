package de.supercode.superbnb.dtos.images.users;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ImagesUserProfileUploadDTO(

        @NotBlank
        Long userId,

        @NotBlank
        MultipartFile file
) {
}

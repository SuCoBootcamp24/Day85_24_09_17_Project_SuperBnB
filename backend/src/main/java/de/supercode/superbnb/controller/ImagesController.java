package de.supercode.superbnb.controller;


import de.supercode.superbnb.dtos.images.properties.ImagesPropertiesListResponseDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertyUploadDTO;
import de.supercode.superbnb.services.ImagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImagesController {

ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    //----- Properties
    @GetMapping("/properties/{id}")
    public ResponseEntity<ImagesPropertiesListResponseDTO> getPropertiesImages(@PathVariable long id) {
        return ResponseEntity.ok(imagesService.getPropertiesImages(id));
    }

    @PostMapping("/properties")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> addNewPropertyImages(@ModelAttribute ImagesPropertyUploadDTO dto) throws Exception {
        imagesService.addNewPropertyImages(dto);
        return ResponseEntity.ok().build();
    }
}

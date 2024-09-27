package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.images.ImgbbDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertiesListResponseDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertyUploadDTO;
import de.supercode.superbnb.entities.ImagesProperty;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.mappers.ImagesMapper;
import de.supercode.superbnb.repositorys.ImagesPropertyRepository;
import de.supercode.superbnb.repositorys.ImagesUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


@Service
public class ImagesService {

    @Value("${imgbb.key}")
    private String IMGBB_KEY;

    @Value("${imgbb.url}")
    private String IMGBB_URL;


    ImagesPropertyRepository imagesPropertyRepository;

    ImagesUserRepository imagesUserRepository;

    ImagesMapper imagesMapper;

    PropertyService propertyService;

    public ImagesService(ImagesPropertyRepository imagesPropertyRepository, ImagesUserRepository imagesUserRepository, ImagesMapper imagesMapper, PropertyService propertyService) {
        this.imagesPropertyRepository = imagesPropertyRepository;
        this.imagesUserRepository = imagesUserRepository;
        this.imagesMapper = imagesMapper;
        this.propertyService = propertyService;
    }

    public ImagesPropertiesListResponseDTO getPropertiesImages(long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);

        return imagesMapper.toImagesPropertiesListResponseDTO(property);

    }

    @Transactional
    public void addNewPropertyImages(ImagesPropertyUploadDTO dto) {
        Property property = propertyService.getPropertyById(dto.propertyId());

        RestClient restClient = RestClient.create(IMGBB_URL);

        MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
        body.add("image", dto.file().getResource());

        ResponseEntity<ImgbbDTO> response = restClient.post()
                .uri("?key=" + IMGBB_KEY)
                .body(body)
                .retrieve()
                .toEntity(ImgbbDTO.class);

        System.out.println(response.getBody().data().url());

        ImagesProperty img = new ImagesProperty();
        img.setImageName(dto.imageName());
        img.setImageURL(response.getBody().data().url());
        img.setDeleteURL(response.getBody().deleteUrl());
        img.setThumbnailI(dto.thumbnail());
        img.setPropertyId(property.getId());
        imagesPropertyRepository.save(img);

        System.out.println(img);

        propertyService.addNewImage(property.getId(),img);


    }
}

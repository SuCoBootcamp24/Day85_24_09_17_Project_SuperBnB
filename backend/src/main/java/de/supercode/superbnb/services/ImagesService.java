package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.images.ImgbbDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertiesListResponseDTO;
import de.supercode.superbnb.dtos.images.properties.ImagesPropertyUploadDTO;
import de.supercode.superbnb.dtos.images.users.ImagesUserProfileUploadDTO;
import de.supercode.superbnb.entities.ImagesProperty;
import de.supercode.superbnb.entities.ImagesUser;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.mappers.ImagesMapper;
import de.supercode.superbnb.repositorys.ImagesPropertyRepository;
import de.supercode.superbnb.repositorys.ImagesUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;


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

    UserService userService;


    public ImagesService(ImagesPropertyRepository imagesPropertyRepository, ImagesUserRepository imagesUserRepository, ImagesMapper imagesMapper, PropertyService propertyService, UserService userService) {
        this.imagesPropertyRepository = imagesPropertyRepository;
        this.imagesUserRepository = imagesUserRepository;
        this.imagesMapper = imagesMapper;
        this.propertyService = propertyService;
        this.userService = userService;
    }

    public ImagesPropertiesListResponseDTO getPropertiesImages(long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);

        return imagesMapper.toImagesPropertiesListResponseDTO(property);

    }

    @Transactional
    public void addNewPropertyImages(ImagesPropertyUploadDTO dto) {
        Property property = propertyService.getPropertyById(dto.propertyId());

        ResponseEntity<ImgbbDTO> response = uploadToImgBB(dto.file());

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



    public void addNewProfileImages(ImagesUserProfileUploadDTO dto, Authentication authentication) {
        User user = userService.getUserById(dto.userId());
        if (!user.getEmail().equals(authentication.getName())) throw new RuntimeException("You can not change other user pictures");

        ResponseEntity<ImgbbDTO> response = uploadToImgBB(dto.file());

        ImagesUser img = new ImagesUser();
        img.setImageName("user#" + user.getId());
        img.setImageURL(response.getBody().data().url());
        img.setDeleteURL(response.getBody().deleteUrl());
        img.setUserId(user.getId());
        imagesUserRepository.save(img);

        System.out.println(img);

        userService.addOrChangeProfileImages(user.getId(), img);

    }




    private ResponseEntity<ImgbbDTO> uploadToImgBB(MultipartFile file) {
        RestClient restClient = RestClient.create(IMGBB_URL);

        MultiValueMap<String, Resource> body = new LinkedMultiValueMap<>();
        body.add("image", file.getResource());

        ResponseEntity<ImgbbDTO> response = restClient.post()
                .uri("?key=" + IMGBB_KEY)
                .body(body)
                .retrieve()
                .toEntity(ImgbbDTO.class);

        System.out.println(response.getBody().data().url());
        return response;
    }

}

package de.supercode.superbnb.services;


import de.supercode.superbnb.repositorys.ImagesPropertyRepository;
import de.supercode.superbnb.repositorys.ImagesUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {

  ImagesPropertyRepository imagesPropertyRepository;

  ImagesUserRepository imagesUserRepository;

    public ImagesService(ImagesPropertyRepository imagesPropertyRepository, ImagesUserRepository imagesUserRepository) {
        this.imagesPropertyRepository = imagesPropertyRepository;
        this.imagesUserRepository = imagesUserRepository;
    }




}

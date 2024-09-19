package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.PropertyResponseDTO;
import de.supercode.superbnb.repositorys.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<PropertyResponseDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(property -> {
                    return new PropertyResponseDTO(
                            property.getId(),
                            property.getTitle(),
                            property.getDescription(),
                            property.getPriceAtNight(),
                            property.isAvailable(),
                            new AddressShortResponseDTO(
                                    property.getAddress().getStreet(),
                                    property.getAddress().getCity(),
                                    property.getAddress().getCountry()
                            )
                    );
                })
                .collect(Collectors.toList());
    }
}

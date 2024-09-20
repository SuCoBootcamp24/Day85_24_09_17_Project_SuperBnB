package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyResponseDTO;
import de.supercode.superbnb.entities.Property;
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
                            property.getName(),
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

    public Property getPropertyById(long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
    }
}

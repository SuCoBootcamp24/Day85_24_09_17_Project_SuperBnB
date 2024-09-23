package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyResponseDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.repositorys.PropertyRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    PropertyRepository propertyRepository;
    private AuthentificationService authenticationService;

    public PropertyService(PropertyRepository propertyRepository, AuthentificationService authenticationService) {
        this.propertyRepository = propertyRepository;
        this.authenticationService = authenticationService;
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


    public PropertyResponseDTO createNewProperty(PropertyRequestDTO dto, String adminEmail) {
        if (!authenticationService.hasAdminRights(adminEmail))
            throw new RuntimeException("you cannot create a new property, you dont have permissions");

        Property property = new Property();
        property.setName(dto.name());
        property.setDescription(dto.description());
        property.setPriceAtNight(dto.priceAtNight());
        property.setAvailable(true);

        property.setAddress(new Address(dto.street(), dto.houseNumber(), dto.zipCode(), dto.city(), dto.country()));

        propertyRepository.save(property);

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

    }
}

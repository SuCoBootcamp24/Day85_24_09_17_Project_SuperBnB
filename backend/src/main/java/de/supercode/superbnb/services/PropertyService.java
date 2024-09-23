package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.mappers.PropertyMapper;
import de.supercode.superbnb.repositorys.PropertyRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    PropertyRepository propertyRepository;
    private AuthentificationService authenticationService;

    PropertyMapper propertyMapper;

    AddressService addressService;


    public PropertyService(PropertyRepository propertyRepository, AuthentificationService authenticationService, PropertyMapper propertyMapper, AddressService addressService) {
        this.propertyRepository = propertyRepository;
        this.authenticationService = authenticationService;
        this.propertyMapper = propertyMapper;
        this.addressService = addressService;
    }

    public List<PropertyListResponseDTO> getAllProperties(Principal principal) {
        if (principal != null) {
            if (authenticationService.hasAdminRights(principal.getName())) {
                return propertyRepository.findAll()
                        .stream()
                        .map(property -> propertyMapper.toPropertyListResponseDTO(property))
                        .collect(Collectors.toList());
            }

        }
        return propertyRepository.findAll()
                .stream()
                .filter(property -> property.isAvailable())
                .map(property -> propertyMapper.toPropertyListResponseDTO(property))
                .collect(Collectors.toList());
    }

    public Property getPropertyById(long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
    }


    public PropertyListResponseDTO createNewProperty(PropertyRequestDTO dto, String adminEmail) {
        if (!authenticationService.hasAdminRights(adminEmail))
            throw new RuntimeException("you cannot create a new property, you dont have permissions");

        Property property = new Property();
        if (!dto.name().isBlank())  property.setName(dto.name());
        if (!dto.description().isBlank())  property.setDescription(dto.description());
        if (dto.guestsCapacity() != null) property.setGuestsCapacity(dto.guestsCapacity());
        if (dto.rooms() != null) property.setRooms(dto.rooms());
        if (dto.beds() != null) property.setBeds(dto.beds());
        if (dto.bathrooms() != null) property.setBathrooms(dto.bathrooms());
        if (dto.rating() != null) property.setRating(dto.rating());
        if (dto.priceAtNight() != null)  property.setPriceAtNight(dto.priceAtNight());
        if (dto.available() != null) property.setAvailable(dto.available());

        property.setAddress(new Address(dto.street(), dto.houseNumber(), dto.zipCode(), dto.city(), dto.country()));

        propertyRepository.save(property);

        return propertyMapper.toPropertyListResponseDTO(property);

    }

    public boolean updateProperty(long id, PropertyUpdateDTO dto, String AdminEmail) {
        if (!authenticationService.hasAdminRights(AdminEmail))
            throw new RuntimeException("you cannot update a property, you dont have permissions");

        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        Optional<Address> existAddress = addressService.getAddressById(property.getAddress().getId());

        if (!dto.name().isBlank() && !property.getName().equals(dto.name()))  property.setName(dto.name());
        if (!dto.description().isBlank() &&!property.getDescription().equals(dto.description()))  property.setDescription(dto.description());
        if (dto.guestsCapacity() != null && property.getGuestsCapacity() != property.getGuestsCapacity()) property.setGuestsCapacity(dto.guestsCapacity());
        if (dto.rooms() != null && property.getRooms() != dto.rooms()) property.setRooms(dto.rooms());
        if (dto.beds() != null && property.getBeds() != dto.beds()) property.setBeds(dto.beds());
        if (dto.bathrooms() != null && property.getBathrooms()!= dto.bathrooms()) property.setBathrooms(dto.bathrooms());
        if (dto.rating() != null && property.getRating() != dto.rating()) property.setRating(dto.rating());
        if (dto.priceAtNight() != null && !dto.priceAtNight().equals(property.getPriceAtNight()))  property.setPriceAtNight(dto.priceAtNight());
        if (dto.available() != null && property.isAvailable() != dto.available()) property.setAvailable(dto.available());

        Address updatedAddress = existAddress.orElseGet(() -> new Address());
        if (!dto.street().isBlank() && !updatedAddress.getStreet().equals(dto.street())) updatedAddress.setStreet(dto.street());
        if (!dto.houseNumber().isBlank() &&!updatedAddress.getHouseNumber().equals(dto.houseNumber())) updatedAddress.setHouseNumber(dto.houseNumber());
        if (!dto.zipCode().isBlank() &&!updatedAddress.getZipCode().equals(dto.zipCode())) updatedAddress.setZipCode(dto.zipCode());
        if (!dto.city().isBlank() &&!updatedAddress.getCity().equals(dto.city())) updatedAddress.setCity(dto.city());
        if (!dto.country().isBlank() &&!updatedAddress.getCountry().equals(dto.country())) updatedAddress.setCountry(dto.country());
        property.setAddress(updatedAddress);

        propertyRepository.save(property);
        return true;
    }
}

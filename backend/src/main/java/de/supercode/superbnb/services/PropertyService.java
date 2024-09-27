package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.favorite.FavoriteListDTO;
import de.supercode.superbnb.dtos.properties.PaginatedPropertiesDTO;
import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.ImagesProperty;
import de.supercode.superbnb.entities.person.Favorite;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.mappers.PropertyMapper;
import de.supercode.superbnb.repositorys.FavoriteRepository;
import de.supercode.superbnb.repositorys.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    PropertyRepository propertyRepository;
    private AuthentificationService authenticationService;

    FavoriteRepository favoriteRepository;

    UserService userService;

    PropertyMapper propertyMapper;

    AddressService addressService;

    public PropertyService(PropertyRepository propertyRepository, AuthentificationService authenticationService, FavoriteRepository favoriteRepository, UserService userService, PropertyMapper propertyMapper, AddressService addressService) {
        this.propertyRepository = propertyRepository;
        this.authenticationService = authenticationService;
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.propertyMapper = propertyMapper;
        this.addressService = addressService;
    }

    public List<PropertyListResponseDTO> getAllProperties(Authentication authentication) {
        if (authenticationService.hasAdminRights(authentication)) {
            return propertyRepository.findAll()
                    .stream()
                    .map(property -> propertyMapper.toPropertyListResponseDTO(property))
                    .collect(Collectors.toList());
        } else return null;
    }



    public Property getPropertyById(long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
    }


    public PropertyListResponseDTO createNewProperty(PropertyRequestDTO dto, Authentication authentication) {
        if (!authenticationService.hasAdminRights(authentication))return null;

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

       property.setAddress(new Address(dto.street(), dto.houseNumber(), dto.city(), dto.zipCode(), dto.country()));

        propertyRepository.save(property);

        return propertyMapper.toPropertyListResponseDTO(property);

    }

    public boolean updateProperty(long id, PropertyUpdateDTO dto, Authentication authentication) {
        if (!authenticationService.hasAdminRights(authentication)) return false;

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


    public List<FavoriteListDTO> getFavorites(String userEmail) {
        if (userEmail == null) throw new RuntimeException("you must Sign in or sign up");
        User user = userService.getUserByEmail(userEmail);
        Optional<Favorite> favoriteList = favoriteRepository.findByUserId(user.getId());
        return favoriteList.map(favorite -> favorite.getFavorites().stream()
                .map(property -> new FavoriteListDTO(
                        property.getId(),
                        property.getName(),
                        property.getDescription(),
                        property.getPriceAtNight()
                )).collect(Collectors.toList())).orElse(null);


    }

    public void addToFavorites(long id, String userEmail) {
        if (userEmail == null) throw new RuntimeException("you must Sign in or sign up");

        User user = userService.getUserByEmail(userEmail);
        Optional<Favorite> favoriteList = favoriteRepository.findByUserId(user.getId());

        if (!favoriteList.isPresent()) {
            favoriteList = Optional.of(new Favorite());
            favoriteList.get().setUser(user);
            favoriteRepository.save(favoriteList.get());
        }
        Property property = getPropertyById(id);
        if (property.isAvailable()) {
            favoriteList.get().getFavorites().add(property);
            favoriteRepository.save(favoriteList.get());
        }
    }

    public void removeFromFavorites(long id, String userEmail) {
        if (userEmail == null) throw new RuntimeException("you must Sign in or sign up");

        User user = userService.getUserByEmail(userEmail);
        Optional<Favorite> favoriteList = favoriteRepository.findByUserId(user.getId());
        if (favoriteList.isPresent()) {
            Property property = getPropertyById(id);
            favoriteList.get().getFavorites().remove(property);
            favoriteRepository.save(favoriteList.get());
        }
    }



    public PaginatedPropertiesDTO searchPropertiesByAddress(LocalDate checkIn, LocalDate checkOut, Integer guests, String city, String country, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Property> pagedProperties = propertyRepository.findAvailableProperties(city, country, checkIn, checkOut, guests, pageable);

        return propertyMapper.toPaginatedPropertiesDTO(pagedProperties);
    }

    public boolean deleteProperty(long id,Authentication authentication) {
        if (!authenticationService.hasAdminRights(authentication)) return false;

        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));

        propertyRepository.delete(property);
        return true;
    }

    public void addNewImage(Long id, ImagesProperty img) {
        Property property = getPropertyById(id);
        property.getImages().add(img);
        propertyRepository.save(property);
    }
}

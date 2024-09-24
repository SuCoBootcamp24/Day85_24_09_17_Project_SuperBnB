package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.entities.Property;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public PropertyUpdateDTO toPropertyUpdateDTO(Property property) {
        return new PropertyUpdateDTO(
                property.getName(),
                property.getDescription(),
                property.getGuestsCapacity(),
                property.getRooms(),
                property.getBeds(),
                property.getBathrooms(),
                property.getRating(),
                property.getPriceAtNight(),
                property.isAvailable(),
                property.getAddress().getStreet(),
                property.getAddress().getHouseNumber(),
                property.getAddress().getZipCode(),
                property.getAddress().getCity(),
                property.getAddress().getCountry()
        );
    }


    public PropertyListResponseDTO toPropertyListResponseDTO(Property property) {
        return new PropertyListResponseDTO(
                property.getId(),
                property.getName(),
                property.getDescription(),
                property.getPriceAtNight(),
                property.isAvailable(),
                new AddressShortResponseDTO(
                        property.getAddress().getCity(),
                        property.getAddress().getZipCode(),
                        property.getAddress().getCountry()
                )
        );
    }

}

package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.address.AddressShortResponseDTO;
import de.supercode.superbnb.dtos.properties.PaginatedPropertiesDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PaginatedPropertiesDTO toPaginatedPropertiesDTO(Page<Property> pagedProperties) {
        List<PropertyListResponseDTO> propertyDTOs = pagedProperties
                .map(this::toPropertyListResponseDTO) // Mapping von Property zu DTO
                .getContent(); // Holen der DTO-Liste

        int activePage = pagedProperties.getNumber() +1;

        return new PaginatedPropertiesDTO(
                propertyDTOs,                        // Liste der Immobilien
                activePage,                          // Aktuelle Seite
                pagedProperties.getTotalPages(),     // Gesamtanzahl der Seiten
                pagedProperties.getTotalElements()   // Gesamtanzahl der Einträge
        );
    }


    public PropertyListResponseDTO toPropertyListResponseDTO(Property property) {
        return new PropertyListResponseDTO(
                property.getId(),
                property.getName(),
                property.getDescription(),
                property.getGuestsCapacity(),
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

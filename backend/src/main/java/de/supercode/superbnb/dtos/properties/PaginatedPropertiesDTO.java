package de.supercode.superbnb.dtos.properties;

import java.util.List;

public record PaginatedPropertiesDTO(
        List<PropertyListResponseDTO> properties,
        int currentPage,
        int totalPages,
        long totalElements
) {
}

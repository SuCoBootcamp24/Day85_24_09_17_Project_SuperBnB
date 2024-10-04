package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.properties.PaginatedPropertiesDTO;
import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }





    //GET /api/properties: Liste aller verfügbaren Ferienwohnungen anzeigen =========(public)
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<PropertyListResponseDTO>> getAllProperties(Authentication authentication) {
        return ResponseEntity.ok(propertyService.getAllProperties(authentication));
    }

    //GET /api/v1/properties/search: Param verschiedene sucherfiter ========== (public)
    @GetMapping("/search")
    public ResponseEntity<PaginatedPropertiesDTO> searchProperties(
            @RequestParam() LocalDate checkIn,
            @RequestParam() LocalDate checkOut,
            @RequestParam() Integer guests,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "1") int page,    // Standardseite: 0 (erste Seite)
            @RequestParam(defaultValue = "10") int size) { // Standardgröße: 10 (10 Ergebnisse pro Seite)

        PaginatedPropertiesDTO responseDTO = propertyService.searchPropertiesByAddress(
                checkIn, checkOut, guests, city, country, page -1, size);

        return ResponseEntity.ok(responseDTO);
    }



    //POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<PropertyListResponseDTO> creatNewProperty(@RequestBody @Valid PropertyRequestDTO dto, Authentication authentication) {
        return ResponseEntity.ok(propertyService.createNewProperty(dto, authentication));
    }

    //PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> updateProperty(@PathVariable long id, @RequestBody PropertyUpdateDTO dto, Authentication authentication) {
        if (propertyService.updateProperty(id, dto, authentication)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    //DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> deleteProperty(@PathVariable long id, Authentication authentication) {
        if (propertyService.deleteProperty(id, authentication)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}

package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.properties.PaginatedPropertiesDTO;
import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyListResponseDTO;
import de.supercode.superbnb.dtos.properties.PropertyUpdateDTO;
import de.supercode.superbnb.services.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PropertyListResponseDTO>> getAllProperties(Principal principal) {
        return ResponseEntity.ok(propertyService.getAllProperties(principal));
    }

    //GET /api/v1/properties/search: Param verschiedene sucherfiter ========== (public)
    @GetMapping("/search")
    public ResponseEntity<PaginatedPropertiesDTO> searchProperties(
            @RequestParam(required = true) LocalDate checkIn,
            @RequestParam(required = true) LocalDate checkOut,
            @RequestParam(required = true) Integer guests,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "0") int page,    // Standardseite: 0 (erste Seite)
            @RequestParam(defaultValue = "10") int size) { // Standardgröße: 10 (10 Ergebnisse pro Seite)

        PaginatedPropertiesDTO responseDTO = propertyService.searchPropertiesByAddress(
                checkIn, checkOut, guests, city, country, page, size);

        return ResponseEntity.ok(responseDTO);
    }



    //POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyListResponseDTO> creatNewProperty(@RequestBody PropertyRequestDTO dto, Principal adminDetails) {
        System.out.println(adminDetails.getName());
        return ResponseEntity.ok(propertyService.createNewProperty(dto, adminDetails.getName()));
    }

    //PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateProperty(@PathVariable long id, @RequestBody PropertyUpdateDTO dto, Principal adminDetails) {
        if (propertyService.updateProperty(id, dto, adminDetails.getName())) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    //DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)
    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProperty(@PathVariable long id, Principal adminDetails) {
        if (propertyService.deleteProperty(id, adminDetails.getName())) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}

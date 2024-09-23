package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.properties.PropertyRequestDTO;
import de.supercode.superbnb.dtos.properties.PropertyResponseDTO;
import de.supercode.superbnb.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties(Principal principal) {
        return ResponseEntity.ok(propertyService.getAllProperties(principal));
    }

    //GET /api/v1/properties/search: Param verschiedene sucherfiter ========== (public)


    //POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)
    @PostMapping
    public ResponseEntity<PropertyResponseDTO> creatNewProperty(@RequestBody PropertyRequestDTO dto, Principal adminDetails) {
        System.out.println(adminDetails.getName());
        return ResponseEntity.ok(propertyService.createNewProperty(dto, adminDetails.getName()));
    }

    //PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)

    //DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)

}

package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.properties.PropertyResponseDTO;
import de.supercode.superbnb.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/API/v1/Properties")
public class PropertyController {

    PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //GET /api/properties: Liste aller verfügbaren Ferienwohnungen anzeigen
    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    //POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)

    //PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)

    //DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)

}
